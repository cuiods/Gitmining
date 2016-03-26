package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoStarChartVO extends MyChartVO {
    public RepoStarChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "0~1000";
        fields[1] = "1000~2000";
        fields[2] = "2000~3000";
        fields[3] = "3000~4000";
        fields[4] = "4000~5000";
        fields[5] = "5000~6000";
        fields[6] = "6000~7000";
        fields[7] = "7000~8000";
        fields[8] = "8000~9000";
        fields[9] = "9000~10000";
        fields[10] = ">= 10000";

        super.values = new int[11];
        for (int i=0;i<11;i++) {
            values[i] = 0;
        }
    }

    public void increase(int size) {
        size /= 1000;
        if (size >10) {
            size = 10;
        }
        values[size] ++;
    }
}
