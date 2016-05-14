package edu.nju.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
            "?client_id=6fbaa89b6cc2e1adca05&client_secret=a6b23034611542b74dd013e1dcddc12b605cbbe6"};

    private static  final String githubUrlHeader = "https://api.github.com/repos/";

    private static final String commitByContributorsUrl = "/stats/contributors";

    private static final String pubchCardUrl = "/stats/punch_card";

    private static final String codeFrequencyUrl = "/stats/code_frequency";

    private static int authCount = 0;

    private ObjectMapper objectMapper;

    public JsonNodeReader() {
        this.objectMapper = new ObjectMapper();
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
        try {
            node = objectMapper.readTree(new URL(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }
}
