package edu.nju.serviceImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import edu.nju.dao.InfoDao;
import edu.nju.entity.CommentsEntity;
import edu.nju.entity.NewsEntity;
import edu.nju.service.MeaningService;
import edu.nju.service.TokenService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * impl of analysis of news and comments.
 * @author cuihao
 */
@Service
public class MeaningServiceImpl implements MeaningService {

    @Resource
    private InfoDao infoDao;

    @Resource
    private TokenService tokenService;

    @Value("#{configProperties['api_keyword']}")
    private String KEYWORD_URL;

    @Value("#{configProperties['api_sentiment_news']}")
    private String SENTIMENT_NEWS_URL;

    @Value("#{configProperties['api_sentiment']}")
    private String SENTIMENT_GENERAL_URL;

    @Value("#{configProperties['api_comment_push']}")
    private String COMMENT_PUSH_URL;

    @Value("#{configProperties['api_comment_analysis']}")
    private String COMMENT_ANALYSIS_URL;

    @Value("#{configProperties['api_comment_status']}")
    private String COMMENT_STATUS_URL;

    @Value("#{configProperties['api_comment_result']}")
    private String COMMENT_RESULT_URL;

    @Value("#{configProperties['api_comment_clear']}")
    private String COMMENT_CLEAR_URL;

    @Override
    public List<String> keywordsOfNews(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        List<NewsEntity> newsEntities = infoDao.getNewsByName(owner,name,100,1);
        List<String> result = new ArrayList<String>();
        for (NewsEntity newsEntity : newsEntities) {
            String body = newsEntity.getSummary();
            HttpResponse<JsonNode> jsonResponse = Unirest.post(KEYWORD_URL)
                    .header("Content-Type","application/json")
                    .header("Accept", "application/json")
                    .header("X-Token", tokenService.getApiToken())
                    .body("\""+body+"\"")
                    .asJson();
            JSONArray jsonArray = jsonResponse.getBody().getArray();
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONArray temp = jsonArray.getJSONArray(i);
                if ((Double)temp.get(0)>0.3) {
                    result.add(temp.getString(1));
                }
            }
        }
        Unirest.shutdown();
        return result;
    }

    @Override
    public double positiveNews(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        List<NewsEntity> newsEntities = infoDao.getNewsByName(owner, name, 100, 1);
        List<String> strText = new ArrayList<String>(newsEntities.size());
        double sum = 0;
        for (NewsEntity newsEntity: newsEntities) {
            strText.add(newsEntity.getSummary());
        }
        String body = new JSONArray(strText.toArray()).toString();
        HttpResponse<JsonNode> jsonResponse = Unirest.post(SENTIMENT_NEWS_URL)
                .header("Accept", "application/json")
                .header("X-Token", tokenService.getApiToken())
                .body(body)
                .asJson();
        JSONArray array = jsonResponse.getBody().getArray();
        for (int i = 0; i < array.length(); i++) {
            JSONArray temp = array.getJSONArray(i);
            sum += temp.getDouble(0);
        }
        Unirest.shutdown();
        return sum/array.length();
    }

    @Override
    public Map<String, List> commonComments(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        List<CommentsEntity> commentsEntities = infoDao.getCommentsByName(owner, name, 100, 1);
        if (commentsEntities.size()<=1) {
            return new HashMap<String, List>();
        }
        return new ClusterTask(tokenService.getApiToken(),commentsEntities).commonLogic();
    }

    @Override
    public double positiveComments(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        List<CommentsEntity> commentsEntities = infoDao.getCommentsByName(owner, name, 100, 1);
        List<String> strText = new ArrayList<String>(commentsEntities.size());
        double sum = 0;
        for (CommentsEntity commentsEntity: commentsEntities) {
            strText.add(commentsEntity.getComment());
        }
        String body = new JSONArray(strText.toArray()).toString();
        HttpResponse<JsonNode> jsonResponse = Unirest.post(SENTIMENT_GENERAL_URL)
                .header("Accept", "application/json")
                .header("X-Token", tokenService.getApiToken())
                .body(body)
                .asJson();
        JSONArray array = jsonResponse.getBody().getArray();
        for (int i = 0; i < array.length(); i++) {
            JSONArray temp = array.getJSONArray(i);
            sum += temp.getDouble(0);
        }
        Unirest.shutdown();
        return sum/array.length();
    }

    /**
     * a task training clusters of comments using boson api
     * @author cuihao
     */
    private class ClusterTask {

        private String token;

        private List<CommentsEntity> entities;

        private long task_id;

        private String PUSH_URL;

        private String ANALYSIS_URL;

        private String STATUS_URL;

        private String RESULT_URL;

        private String CLEAR_URL;

        ClusterTask(String token, List<CommentsEntity> entities) {
            this.token = token;
            this.entities = entities;
            task_id = entities.get(0).getRepoId();
            PUSH_URL = COMMENT_PUSH_URL + task_id;
            ANALYSIS_URL = COMMENT_ANALYSIS_URL + task_id;
            STATUS_URL = COMMENT_STATUS_URL + task_id;
            RESULT_URL = COMMENT_RESULT_URL + task_id;
            CLEAR_URL = COMMENT_CLEAR_URL + task_id;

        }

        Map<String, List> commonLogic() throws JSONException, UnirestException,
                java.io.IOException{
            boolean flag = true;
            JSONArray result = null;
            AnalyseStatus status = null;
            while (flag) {
                switch (status = getStatus()){
                    case NOT_FOUND:uploadData(entities);
                    case RECEIVED:startAnalyse();
                    case RUNNING:
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case DONE:
                        result = getResult();
                        flag = false;
                        break;
                    case ERROR:
                        System.out.println(status);
                        return null;
                }
            }
            Map<String,List> resultMap = new HashMap<>();
            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonObj = ((JSONObject) result.opt(i));
                int num = jsonObj.getInt("num");
                int id = jsonObj.getInt("_id");
                String op = jsonObj.getString("opinion");
//                String list = jsonObj.getString("list");
                List list = new ArrayList(2);
                list.add(num);
                list.add(id);
                resultMap.put(op,list);
            }
            return resultMap;
        }

        private boolean uploadData(List<CommentsEntity> entities) throws UnirestException {
            JSONArray jsonArray = new JSONArray();
            for(CommentsEntity entity: entities) {
                JSONObject object = new JSONObject();
                object.put("_id", entity.getId());
                object.put("text", entity.getComment());
                jsonArray.put(object);
            }
            Unirest.post(PUSH_URL)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("X-Token", token)
                    .body(jsonArray.toString())
                    .asJson();
            return true;
        }

        private int startAnalyse() throws UnirestException{
            HttpResponse<String> commentAnalysisResponse = Unirest
                    .get(ANALYSIS_URL)
                    .header("Accept", "application/json")
                    .header("X-Token", token)
                    .header("beta","0.1")
                    .asString();
            return commentAnalysisResponse.getStatus();
        }

        private AnalyseStatus getStatus() throws UnirestException{
            HttpResponse<String> commentStatusResponse = Unirest
                    .get(STATUS_URL)
                    .header("Accept", "application/json")
                    .header("X-Token", token)
                    .asString();
            JSONObject jsonObj = new JSONObject(commentStatusResponse.getBody());
            System.out.println(jsonObj);
            if (jsonObj.has("message")&&jsonObj.getInt("status")==404) {
                return AnalyseStatus.NOT_FOUND;
            }
            String status = (String) jsonObj.get("status");
            switch (status) {
                case "RECEIVED":return AnalyseStatus.RECEIVED;
                case "DONE":return AnalyseStatus.DONE;
                case "ERROR":return AnalyseStatus.ERROR;
                default:return AnalyseStatus.RUNNING;
            }
        }

        private JSONArray getResult() throws UnirestException,JSONException{
            HttpResponse<JsonNode> commentResultResponse = Unirest
                    .get(RESULT_URL)
                    .header("Accept", "application/json")
                    .header("X-Token", token)
                    .asJson();
            JsonNode result = commentResultResponse.getBody();
            return result.getArray();
        }

        private boolean clear() throws UnirestException {
            HttpResponse<String> commentClearResponse = Unirest
                    .get(CLEAR_URL)
                    .header("Accept", "application/json")
                    .header("X-Token", token)
                    .asString();
            return commentClearResponse.getBody().contains("Cleared");
        }
    }

    enum AnalyseStatus {
        NOT_FOUND, RECEIVED, RUNNING, ERROR, DONE
    }
}
