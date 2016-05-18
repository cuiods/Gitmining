package edu.nju.common.json;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.TimeTranslator;
import edu.nju.model.pojo.CodeFrequency;
import edu.nju.model.pojo.CommitChart;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.SimpleChart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
                long commitCount = child.findValue("total").asLong();
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

                Object [] of = field.toArray();

                CommitChart commitChart = new CommitChart(contributorName,avatarUrl,commitCount,
                        Arrays.copyOf(of,of.length,String[].class), value.toArray(new Integer[value.size()]));

                resultMap.put(commitChart.getContributorName(), commitChart);
            }
            //compute the total commit data of all contributors
            Object [] oaf = allField.toArray();
            allCommit = new CommitChart("all", "", allTotal, Arrays.copyOf(oaf, oaf.length, String[].class),
                    allValue.toArray(new Integer[allValue.size()]));
            resultMap.put("all", allCommit);
        }

        else {  //no such repository

        }
        return resultMap;
    }

    public CodeFrequency getCodeFrequency(String ownername, String reponame){
        JsonNode node = jsonNodeReader.getCodeFrequency(ownername, reponame);
        ArrayList<String> field = new ArrayList<>();
        ArrayList<Integer> addition = new ArrayList<>();
        ArrayList<Integer> delete = new ArrayList<>();
        if (node != null) {
            for (JsonNode week: node){
                String time = timeTranslator.transUnixTime(week.get(0).asLong());
                field.add(time);
                addition.add(week.get(1).asInt());
                delete.add(week.get(2).asInt());
            }
        }

        Object [] of = field.toArray();
        String [] sf = Arrays.copyOf(of, of.length, String[].class);

        return new CodeFrequency(sf, addition.toArray(new Integer[addition.size()]),
                delete.toArray(new Integer[delete.size()]));
    }

    /**
     * get the commit chart for <b>commits per hour of a day</b> and <b>commits per day of a week</b>.
     * @param ownername
     * @param reponame
     * @return an array with <b>TWO</b> <tt>RadarChart</tt>.
     */
    public SimpleChart[] getPunchCard(String ownername, String reponame){
        SimpleChart[] simpleCharts = new SimpleChart[2];
        JsonNode node = jsonNodeReader.getPunchCard(ownername, reponame);

        String [] hourField = {"0","1","2","3","4","5","6","7","8","9","10","11","12",
                                "13","14","15","16","17","18","19","20","21","22","23"};
        String [] weekField = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};

        long [] hourValue = {0,0,0,0, 0,0,0,0, 0,0,0,0,  0,0,0,0, 0,0,0,0, 0,0,0,0};
        long [] weekValue = {0,0,0,0,0,0,0};

        if (node != null) {
            for (JsonNode data: node){
                int weekday = data.get(0).asInt();
                int hour = data.get(1).asInt();
                int commit = data.get(2).asInt();
                hourValue[hour] += commit;
                weekValue[weekday] += commit;
            }
        }

        simpleCharts[0] = new SimpleChart(hourField,hourValue);
        simpleCharts[1] = new SimpleChart(weekField, weekValue);

        return simpleCharts;
    }

    public JsonNodeReader getJsonNodeReader() {
        return jsonNodeReader;
    }

    public void setJsonNodeReader(JsonNodeReader jsonNodeReader) {
        this.jsonNodeReader = jsonNodeReader;
    }

    public TimeTranslator getTimeTranslator() {
        return timeTranslator;
    }

    public void setTimeTranslator(TimeTranslator timeTranslator) {
        this.timeTranslator = timeTranslator;
    }
}
