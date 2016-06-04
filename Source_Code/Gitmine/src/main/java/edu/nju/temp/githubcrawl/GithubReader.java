package edu.nju.temp.githubcrawl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Harry on 2016/5/21.
 * read the data from github
 */
@Service
public class GithubReader {
    private static final String[] auth =  {
            "?client_id=c0ac13f51484e1dcdad6&client_secret=73c20d744d1fce8be5ce5d2a791774f79b7f648a",
            "?client_id=664f63808c14fd653d40&client_secret=d3f2b14218fbf526beca11f608d2cc1c7027c6d3",
            "?client_id=6fbaa89b6cc2e1adca05&client_secret=a6b23034611542b74dd013e1dcddc12b605cbbe6",
            "?client_id=ed1216fef5af28831e2d&client_secret=0c9c1e892653787fde9d1cce7a1f58365ba4599a",
            "?client_id=a933b93042f032033396&client_secret=39ce7e6532fed6803bcb4ca56b4b66779a89c8b7",
            "?client_id=7520cbd5813c2b44d00b&client_secret=93f6479c9f159ba0aeae5667f1fa20ea5c3f95b5"};

    private static int auth_count = 0;

    private final String userHeader = "https://api.github.com/users";

    private final String searchHeader = "https://api.github.com/search/repositories";

    private ObjectMapper objectMapper;

    public GithubReader() {
        this.objectMapper = new ObjectMapper();
    }

    public JsonNode getSearchArray(String url, int page){
        JsonNode node = null;
        try {
            node = objectMapper.readTree(new URL(url+getAuth().replace("?client_id","&client_id")+"&page="+page));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public JsonNode traverseUsers(long since){
        JsonNode node = null;
        String url = userHeader+getAuth()+"&since"+since;
        try {
            node = objectMapper.readTree(new URL(url));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public JsonNode getArrayNode(String url, int page){
        JsonNode node = null;
        try {
            node = objectMapper.readTree(new URL(url+getAuth()+"&page="+page));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public JsonNode getSimpleNode(String url){
        JsonNode node = null;
        try{
            node = objectMapper.readTree(new URL(url+getAuth()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public JsonNode getMostStarRepos(String language, int page, int per_page) throws IOException {
        JsonNode node = null;
        String url = searchHeader+getAuth()+"q=language:"+language+"&sort=stars&order=desc&per_page="+per_page+"&page="+page;

        node = objectMapper.readTree(new URL(url));

        return node;
    }

    private String getAuth(){
        return auth[(auth_count++)%auth.length];
    }

}
