package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class RepoContriChartVO extends MyChartVO{
    public RepoContriChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "0~10";
        fields[1] = "10~20";
        fields[2] = "20~30";
        fields[3] = "30~40";
        fields[4] = "40~50";
        fields[5] = "50~60";
        fields[6] = "60~70";
        fields[7] = "70~80";
        fields[8] = "80~90";
        fields[9] = "90~100";
        fields[10] = ">=100";

        super.values = new int[fields.length];
        for (int i=0;i<values.length;i++) {
            values[i] = 0;
        }

    }

    public void increase(int contriCount) {
        contriCount /=10;
        if (contriCount >10) {
            contriCount = 10;
        }
        values [contriCount] ++;
    }
}
