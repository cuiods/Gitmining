package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class RepoContriChartVO extends MyChartVO{
    public RepoContriChartVO() {
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

    public void increase(int contriCount) {
        contriCount /=10;
        if (contriCount >5) {
            contriCount = 5;
        }
        values [contriCount] ++;
    }
}
