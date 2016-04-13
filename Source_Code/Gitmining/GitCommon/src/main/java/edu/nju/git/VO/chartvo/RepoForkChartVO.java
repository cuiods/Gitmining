package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoForkChartVO extends MyChartVO {
    public RepoForkChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "0~100";
        fields[1] = "100~200";
        fields[2] = "200~300";
        fields[3] = "300~400";
        fields[4] = "400~500";
        fields[5] = "500~600";
        fields[6] = "600~700";
        fields[7] = "700~800";
        fields[8] = "800~900";
        fields[9] = "900~1000";
        fields[10] = ">= 1000";

        super.values = new int[11];
        for (int i=0;i<11;i++) {
            values[i] = 0;
        }
    }

    public void increase (int forkCount) {
        forkCount /= 10;
        if (forkCount > 10) {
            forkCount =10;
        }
        values[forkCount] ++;
    }
}
