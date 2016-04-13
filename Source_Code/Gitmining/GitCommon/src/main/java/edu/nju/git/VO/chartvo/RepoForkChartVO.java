package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoForkChartVO extends MyChartVO {
    public RepoForkChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "0~50";
        fields[1] = "50~100";
        fields[2] = "100~150";
        fields[3] = "150~200";
        fields[4] = "200~250";
        fields[5] = "250~300";
        fields[6] = "300~350";
        fields[7] = "350~400";
        fields[8] = "400~450";
        fields[9] = "450~500";
        fields[10] = ">=500";

        super.values = new int[fields.length];
        for (int i=0;i<values.length;i++) {
            values[i] = 0;
        }
    }

    public void increase (int forkCount) {
        forkCount /= 50;
        if (forkCount > 10) {
            forkCount =10;
        }
        values[forkCount] ++;
    }
}
