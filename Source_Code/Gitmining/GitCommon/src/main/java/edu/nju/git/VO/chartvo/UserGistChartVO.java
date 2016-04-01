package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class UserGistChartVO extends MyChartVO {
    public UserGistChartVO() {
        super();
        super.fields = new String[10];
        fields[0] = "0~10";
        fields[1] = "10~20";
        fields[2] = "20~30";
        fields[3] = "30~40";
        fields[4] = "40~50";
        fields[5] = "50~60";
        fields[6] = "60~70";
        fields[7] = "70~80";
        fields[8] = "80~90";
        fields[9] = ">=90";

        super.values = new int[10];
        for (int i=0;i<10;i++) {
            values[i] = 0;
        }
    }

    public void increase (int repoCount) {
        repoCount /= 10;
        if (repoCount >9) {
            repoCount =9;
        }
        values[repoCount] ++;
    }
}
