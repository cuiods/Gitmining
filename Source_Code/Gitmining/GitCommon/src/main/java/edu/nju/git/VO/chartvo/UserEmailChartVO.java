package edu.nju.git.VO.chartvo;

import java.util.*;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserEmailChartVO extends MyChartVO{

    private HashMap<String, Integer> emailCount;

    public UserEmailChartVO() {
        emailCount = new HashMap<>();
    }

    public void increase (String email) {
        int domainIndex = email.lastIndexOf("@");
        if (domainIndex<0) {
            return;
        }
        email = email.substring(domainIndex+1);
        if (emailCount.containsKey(email)) {
            emailCount.replace(email, emailCount.get(email)+1);
        }
        else {
            emailCount.put(email, 1);
        }
    }

    public void sortAndSetArray() {
        List<Map.Entry<String, Integer>> mappingList= new ArrayList<>(emailCount.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()- o1.getValue();
            }
        });
        int emailNum = (mappingList.size()>10? 10: mappingList.size());
        super.fields = new String[emailNum];
        super.values = new int[emailNum];
        for (int i=0;i<emailNum;i++) {
            fields[i] = mappingList.get(i).getKey();
            values[i] = mappingList.get(i).getValue();
        }
    }
}
