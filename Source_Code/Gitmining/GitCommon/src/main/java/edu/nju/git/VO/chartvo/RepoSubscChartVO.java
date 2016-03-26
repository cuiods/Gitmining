package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class RepoSubscChartVO extends MyChartVO{
    public RepoSubscChartVO() {
        super();
        super.fields = new String[6];
        fields[0] = "0~100";
        fields[1] = "100~200";
        fields[2] = "200~300";
        fields[3] = "300~400";
        fields[4] = "400~500";
        fields[5] = ">= 500";

        super.values = new int[6];
        for (int i=0;i<6;i++) {
            values[i] = 0;
        }
    }

    public void increase (int subscrCount) {
        subscrCount /= 10;
        if (subscrCount > 5) {
            subscrCount =5;
        }
        values[subscrCount] ++;
    }
}
