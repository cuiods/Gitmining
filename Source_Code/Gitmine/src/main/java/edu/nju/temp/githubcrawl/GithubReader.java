package edu.nju.temp.githubcrawl;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by Harry on 2016/5/21.
 * read the data from github
 */
public class GithubReader {
    private static final String[] auth =  {
            "?client_id=c0ac13f51484e1dcdad6&client_secret=73c20d744d1fce8be5ce5d2a791774f79b7f648a",
            "?client_id=664f63808c14fd653d40&client_secret=d3f2b14218fbf526beca11f608d2cc1c7027c6d3",
            "?client_id=6fbaa89b6cc2e1adca05&client_secret=a6b23034611542b74dd013e1dcddc12b605cbbe6",
            "?client_id=ed1216fef5af28831e2d&client_secret=0c9c1e892653787fde9d1cce7a1f58365ba4599a"};

    private static final String userHeader = "https://api.github.com/users";

    public JsonNode traverseUsers(){

        return null;
    }
}
