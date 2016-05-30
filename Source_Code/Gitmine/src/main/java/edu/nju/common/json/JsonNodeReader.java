package edu.nju.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Harry on 2016/5/14.
 * this class read json node from github about graphs
 */
@Service
public class JsonNodeReader {

    private static final String[] auth =  {
            "?client_id=c0ac13f51484e1dcdad6&client_secret=73c20d744d1fce8be5ce5d2a791774f79b7f648a",
            "?client_id=664f63808c14fd653d40&client_secret=d3f2b14218fbf526beca11f608d2cc1c7027c6d3",
            "?client_id=6fbaa89b6cc2e1adca05&client_secret=a6b23034611542b74dd013e1dcddc12b605cbbe6",
            "?client_id=ed1216fef5af28831e2d&client_secret=0c9c1e892653787fde9d1cce7a1f58365ba4599a",
            "?client_id=a933b93042f032033396&client_secret=39ce7e6532fed6803bcb4ca56b4b66779a89c8b7",
            "?client_id=7520cbd5813c2b44d00b&client_secret=93f6479c9f159ba0aeae5667f1fa20ea5c3f95b5"};

    private static  final String githubUrlHeader = "https://api.github.com/repos/";

    private static final String commitByContributorsUrl = "/stats/contributors";

    private static final String pubchCardUrl = "/stats/punch_card";

    private static final String codeFrequencyUrl = "/stats/code_frequency";

    private static int authCount = 0;

    private ObjectMapper objectMapper;

    private OkHttpClient client;

    public JsonNodeReader() {
        this.objectMapper = new ObjectMapper();
        this.client = new OkHttpClient.Builder()
                .addInterceptor(new GithubStatsInterceptor())
                .build();
    }

    public JsonNode getCommitByContributors(String ownername, String reponame){
        StringBuilder builder = getRepoUrl(ownername, reponame);
        builder.append(commitByContributorsUrl);
        builder.append(nextHeader());
        return getNode(builder.toString());
    }

    public JsonNode getCodeFrequency(String ownername, String reponame){
        StringBuilder builder = getRepoUrl(ownername, reponame);
        builder.append(codeFrequencyUrl);
        builder.append(nextHeader());
        return getNode(builder.toString());
    }

    public JsonNode getPunchCard(String ownername, String reponame){
        StringBuilder builder = getRepoUrl(ownername, reponame);
        builder.append(pubchCardUrl);
        builder.append(nextHeader());
        return getNode(builder.toString());
    }

    private String nextHeader(){
        String au = auth[authCount];
        authCount = (++authCount)%(auth.length);
        return au;
    }

    private StringBuilder getRepoUrl(String ownername, String reponame){
        StringBuilder builder = new StringBuilder();
        builder.append(githubUrlHeader);
        builder.append(ownername);
        builder.append("/");
        builder.append(reponame);
        return builder;
    }

    private JsonNode getNode(String url){
        JsonNode node = null;
        Response response = null;
        InputStream stream = null;
        try {
            Request request = new Request.Builder()
                    .url(url).build();
            response = client.newCall(request).execute();
            if (!response.isSuccessful()){
                throw new IOException("OkHttp exception"+response);
            }
            stream = response.body().byteStream();

            node = objectMapper.readTree(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io exception when read json from github");
        } finally {
            if (response != null){
                response.close();
            }
        }
        return node;
    }
}
