package edu.nju.serviceImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.nju.dao.InfoDao;
import edu.nju.entity.CommentsEntity;
import edu.nju.entity.NewsEntity;
import edu.nju.service.MeaningService;
import edu.nju.service.TokenService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public Map<String, Long> commonComments(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        List<CommentsEntity> commentsEntities = infoDao.getCommentsByName(owner, name, 100, 1);
        return null;
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
}
