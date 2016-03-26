package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoForkChartVO extends MyChartVO {
    public RepoForkChartVO() {
        super();
        super.fields = new String[7];
        fields[0] = "0~100";
        fields[1] = "100~200";
        fields[2] = "200~300";
        fields[3] = "300~400";
        fields[4] = "400~500";
        fields[5] = "500~600";
        fields[6] = ">= 600";

        super.values = new int[7];
        for (int i=0;i<7;i++) {
            values[i] = 0;
        }
    }

    public void increase (int forkCount) {
        forkCount /= 10;
        if (forkCount > 6) {
            forkCount =6;
        }
        values[forkCount] ++;
    }
}
