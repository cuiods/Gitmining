package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoSizeChartVO extends MyChartVO {
    public RepoSizeChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "0~2000";
        fields[1] = "2000~4000";
        fields[2] = "4000~6000";
        fields[3] = "6000~8000";
        fields[4] = "8000~10000";
        fields[5] = "10000~12000";
        fields[6] = "12000~14000";
        fields[7] = "14000~16000";
        fields[8] = "16000~18000";
        fields[9] = "18000~20000";
        fields[10] = ">=20000";

        super.values = new int[11];
        for (int i=0;i<11;i++) {
            values [i] =0;
        }
    }

    public void increase(int size) {
        size /= 2000;
        if (size >10) {
            size = 10;
        }
        values[size] ++;
    }
}
