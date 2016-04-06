package org.GitServer.repoactivitystrategy.impl;

import edu.nju.git.VO.RepoVO;
import org.GitServer.repoactivitystrategy.service.RepoActivityCalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Harry on 2016/4/5.
 */
public class RepoActivityMonthCalculator implements RepoActivityCalculator {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

    @Override
    public RepoVO generateLineChart(RepoVO vo, List<String> commits, List<String> pulls, List<String> issues) {
        HashMap<String, ActivityVO> map = new HashMap<>();
        if (commits!=null) {
            for (String s : commits) {
                addActivity(map, s);
            }
        }
        else {
            System.out.println("missing commit list for repo: "+vo.getOwnerName()+"/"+vo.getName());
        }

        if (pulls!=null) {
            for (String s : pulls) {
                addActivity(map, s);
            }
        }
        else {
            System.out.println("missing pull list for repo: "+vo.getOwnerName()+"/"+vo.getName());
        }

        if (issues!=null) {
            for (String s : issues) {
                addActivity(map, s);
            }
        }
        else {
            System.out.println("missing issue list for repo: "+vo.getOwnerName()+"/"+vo.getName());
        }

        ArrayList<Map.Entry<String, ActivityVO>> mappingList = new ArrayList<>(map.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String, ActivityVO>>() {
            @Override
            public int compare(Map.Entry<String, ActivityVO> o1, Map.Entry<String, ActivityVO> o2) {
                return o1.getValue().getTime().compareTo(o2.getValue().getTime());
            }
        });

        ArrayList<String> fieldList = new ArrayList<>();
        ArrayList<Integer> valueList = new ArrayList<>();

        for (int i=0;i<mappingList.size();i++) {
            Map.Entry<String, ActivityVO> node = mappingList.get(i);
            fieldList.add(node.getKey());
            valueList.add(node.getValue().getFrequence());
            if (i<mappingList.size()-1) {
                String nextmonth = nextMonth(node.getKey());
                String terminalMonth = mappingList.get(i+1).getKey();
                while(!nextmonth.equals(terminalMonth)){
                    fieldList.add(nextmonth);
                    valueList.add(0);
                    nextmonth = nextMonth(nextmonth);
                }
            }
        }
        vo.setLineCharField(fieldList.toArray(new String[fieldList.size()]));
        vo.setLineChartValue(valueList.toArray(new Integer[valueList.size()]));
        return vo;
    }

    private String nextMonth(String month) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(month));
            c.add(Calendar.MONTH, 1);
            return sdf.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("parse date error!");
        }
        return null;
    }

    private void addActivity(HashMap<String, ActivityVO> map, String time) {
        String s = time.substring(1,8);   //get year and month, the first char is ", so the begin index is 1
        ActivityVO activityVO = map.get(s);
        if (activityVO!=null) {
            activityVO.addFrequence();
        }
        else {
            map.put(s,new ActivityVO(s));
        }
    }

    private class ActivityVO {
        String time;
        int frequence;

        public ActivityVO(String time) {
            this.time = time;
            this.frequence = 1;
        }

        public int getFrequence() {
            return frequence;
        }

        public String getTime() {
            return time;
        }


        public void addFrequence() {
            this.frequence++;
        }

    }
}
