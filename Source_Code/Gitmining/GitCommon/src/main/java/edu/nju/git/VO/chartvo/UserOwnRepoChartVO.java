package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserOwnRepoChartVO extends MyChartVO {
    public UserOwnRepoChartVO() {
        super();
        super.fields = new String[6];
        fields[0] = "0~20";
        fields[1] = "20~40";
        fields[2] = "40~60";
        fields[3] = "60~80";
        fields[4] = "80~100";
        fields[5] = ">= 100";

        super.values = new int[6];
        for (int i=0;i<6;i++) {
            values[i] = 0;
        }
    }

    public void increase (int repoCount) {
        repoCount /= 20;
        if (repoCount >5) {
            repoCount =5;
        }
        values[repoCount] ++;
    }
}
