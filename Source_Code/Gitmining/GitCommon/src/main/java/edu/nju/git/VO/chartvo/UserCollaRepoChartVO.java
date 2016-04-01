package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserCollaRepoChartVO extends MyChartVO {
    public UserCollaRepoChartVO() {
        super();
        super.fields = new String[7];
        fields[0] = "0~5";
        fields[1] = "5~10";
        fields[2] = "10~15";
        fields[3] = "15~20";
        fields[4] = "20~25";
        fields[5] = "25~30";
        fields[6] = ">=30";

        super.values = new int[7];
        for (int i=0;i<7;i++) {
            values[i] = 0;
        }
    }

    public void increase (int repoCount) {
        repoCount /= 5;
        if (repoCount >6) {
            repoCount =6;
        }
        values[repoCount] ++;
    }
}
