package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoStarChartVO extends MyChartVO {
    public RepoStarChartVO() {
        super();
        super.fields = new String[9];
        fields[0] = "0~500";
        fields[1] = "500~1000";
        fields[2] = "1000~1500";
        fields[3] = "1500~2000";
        fields[4] = "2000~2500";
        fields[5] = "2500~3000";
        fields[6] = "3000~3500";
        fields[7] = "3500~4000";
        fields[8] = ">= 4000";

        super.values = new int[9];
        for (int i=0;i<9;i++) {
            values[i] = 0;
        }
    }

    public void increase(int size) {
        size /= 500;
        if (size >8) {
            size = 8;
        }
        values[size] ++;
    }
}
