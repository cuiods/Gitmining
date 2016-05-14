package edu.nju.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.TimeTranslator;
import edu.nju.model.pojo.CodeFrequency;
import edu.nju.model.pojo.CommitChart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Harry on 2016/5/14.
 * this class is designed to delete useless information from json node about graphs read from <br/>
 * github and parse <tt>JsonNode</tt> to a <tt>Map</tt>
 */
@Service
public class JsonNodeParser {

    @Resource
    private JsonNodeReader jsonNodeReader;

    @Resource
    private TimeTranslator timeTranslator;

    public JsonNodeParser() {
    }

    public Map<String, CommitChart> getCommitByContributors(String ownername, String reponame){
        Map<String, CommitChart> resultMap = new HashMap<>();
//        List<Object>
        CommitChart allCommit;
        long allTotal = 0;
        ArrayList<String> allField = new ArrayList<>();
        ArrayList<Integer> allValue = new ArrayList<>();

        JsonNode node = jsonNodeReader.getCommitByContributors(ownername, reponame);
        if (node != null){
            for (JsonNode child: node){     //for each contributor

                String contributorName = child.findValue("author").findValue("login").asText();
                String avatarUrl = child.findValue("author").findValue("avatar_url").asText();
                long commitCount = child.findValue("totals").asLong();
                allTotal ++ ;

                ArrayList<String> field = new ArrayList<>();
                ArrayList<Integer> value = new ArrayList<>();
                JsonNode weeks = child.findValue("weeks");
                for (JsonNode week: weeks){
                    long unixTime = week.findValue("w").asLong();
                    int commitPerWeek = week.findValue("c").asInt();
                    String time = timeTranslator.transUnixTime(unixTime);
                    field.add(time);
                    value.add(commitPerWeek);
                    if (allField.size()<field.size()){  //a new week
                        allField.add(time);
                        allValue.add(commitPerWeek);
                    }
                    else {
                        allValue.set(value.size()-1, allValue.get(value.size()-1)+commitPerWeek);
                    }
                }

                CommitChart commitChart = new CommitChart(contributorName,avatarUrl,commitCount,
                        (String[])field.toArray(), (Integer[])value.toArray());

                resultMap.put(commitChart.getContributorName(), commitChart);
            }
            //compute the total commit data of all contributors
            allCommit = new CommitChart("all", "", allTotal, (String[])allField.toArray(),
                    (Integer[])allValue.toArray());
            resultMap.put("all", allCommit);
        }

        else {  //no such repository

        }
        return resultMap;
    }

    public CodeFrequency getCodeFrequency(String ownername, String reponame){
        JsonNode node = jsonNodeReader.getCodeFrequency(ownername, reponame);
        ArrayList<String> field = new ArrayList<>();
        ArrayList<String> add = new ArrayList<>();
        ArrayList<String> delete = new ArrayList<>();

        return null;
    }
}
