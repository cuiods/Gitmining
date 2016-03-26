package edu.nju.git.VO.chartvo;

import java.util.*;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserCompanyChartVO extends MyChartVO {

    private HashMap<String, Integer> companyCount;

    public UserCompanyChartVO() {
        super();
        companyCount = new HashMap<>();
    }

    public void increase (String company) {
        if (companyCount.containsKey(company)) {
            companyCount.replace(company, companyCount.get(company)+1);
        }
        else {
            companyCount.put(company, 1);
        }
    }

    public void sortAndSetArray() {
        List<Map.Entry<String, Integer>> mappingList= new ArrayList<>(companyCount.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()- o1.getValue();
            }
        });
        int companyNum = (mappingList.size()>10? 10: mappingList.size());
        super.fields = new String[companyNum];
        super.values = new int[companyNum];
        for (int i=0;i<companyNum;i++) {
            fields[i] = mappingList.get(i).getKey();
            values[i] = mappingList.get(i).getValue();
        }
    }
}
