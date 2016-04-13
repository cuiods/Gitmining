package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class RepoSubscChartVO extends MyChartVO{
    public RepoSubscChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "0~20";
        fields[1] = "20~40";
        fields[2] = "40~60";
        fields[3] = "60~80";
        fields[4] = "80~100";
        fields[5] = "100~120";
        fields[6] = "120~140";
        fields[7] = "140~160";
        fields[8] = "160~180";
        fields[9] = "180~200";
        fields[10] = ">=200";

        super.values = new int[fields.length];
        for (int i=0;i<values.length;i++) {
            values[i] = 0;
        }
    }

    public void increase (int subscrCount) {
        subscrCount /= 20;
        if (subscrCount > 10) {
            subscrCount =10;
        }
        values[subscrCount] ++;
    }
}
