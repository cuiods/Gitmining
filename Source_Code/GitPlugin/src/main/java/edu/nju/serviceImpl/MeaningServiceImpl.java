package edu.nju.serviceImpl;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.nju.dao.InfoDao;
import edu.nju.entity.NewsEntity;
import edu.nju.service.MeaningService;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
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

    @Value("#{configProperties['api_keyword']}")
    private String KEYWORD_URL;

    @Value("#{configProperties['token1']}")
    private String token1;

    @Value("#{configProperties['token2']}")
    private String token2;

    @Override
    public List<String> keywordsOfNews(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        List<NewsEntity> newsEntities = infoDao.getNewsByName(owner,name,100,1);
        List<String> result = new ArrayList<String>();
        System.out.println("====="+KEYWORD_URL);
        System.out.println("====="+token1);
        for (NewsEntity newsEntity : newsEntities) {
            String body = newsEntity.getSummary();
            HttpResponse<JsonNode> jsonResponse = Unirest.post(KEYWORD_URL)
                    .header("Content-Type","application/json")
                    .header("Accept", "application/json")
                    .header("X-Token", "5eAu0EgH.8707.udaT47PYgvMq")
                    .header("top_k","10")
                    .body("\""+body+"\"")
                    .asJson();
            result.add(jsonResponse.getBody().toString());
        }
        return result;
    }

    @Override
    public double positiveNews(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        return 0;
    }

    @Override
    public Map<String, Long> commonComments(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        return null;
    }

    @Override
    public double positiveComments(String owner, String name) throws JSONException, UnirestException,
            java.io.IOException{
        return 0;
    }
}

class SentimentApiExample  {
    public static final String SENTIMENT_URL =
            "http://api.bosonnlp.com/sentiment/analysis";

    public static void main(String[] args) throws JSONException, UnirestException,
            java.io.IOException
    {
        String body = new JSONArray(new String[]{"他是个傻逼", "美好的世界"}).toString();
        HttpResponse<JsonNode> jsonResponse = Unirest.post(SENTIMENT_URL)
                .header("Accept", "application/json")
                .header("X-Token", "5eAu0EgH.8707.udaT47PYgvMq")
                .body(body)
                .asJson();

        System.out.println(jsonResponse.getBody());

        // Unirest starts a background event loop and your Java
        // application won't be able to exit until you manually
        // shutdown all the threads
        Unirest.shutdown();
    }
}
