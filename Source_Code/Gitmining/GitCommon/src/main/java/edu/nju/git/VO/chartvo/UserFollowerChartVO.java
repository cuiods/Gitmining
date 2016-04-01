package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserFollowerChartVO extends MyChartVO{
    public UserFollowerChartVO() {
        super();
        super.fields = new String[10];
        fields[0] = "0~10";
        fields[1] = "10~20";
        fields[2] = "20~30";
        fields[3] = "30~40";
        fields[4] = "40~50";
        fields[6] = "50~60";
        fields[7] = "60~70";
        fields[8] = "70~80";
        fields[9] = ">= 80";

        super.values = new int[10];
        for (int i=0;i<10;i++) {
            values[i] = 0;
        }
    }

    public void increase (int follower) {
        follower /= 10;
        if (follower >9) {
            follower =9;
        }
        values[follower] ++;
    }
}
