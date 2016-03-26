package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserFollowerChartVO extends MyChartVO{
    public UserFollowerChartVO() {
        super();
        super.fields = new String[6];
        fields[0] = "0~50";
        fields[1] = "50~100";
        fields[2] = "100~150";
        fields[3] = "150~200";
        fields[4] = "200~250";
        fields[5] = ">= 250";

        super.values = new int[6];
        for (int i=0;i<6;i++) {
            values[i] = 0;
        }
    }

    public void increase (int follower) {
        follower /= 50;
        if (follower >5) {
            follower =5;
        }
        values[follower] ++;
    }
}
