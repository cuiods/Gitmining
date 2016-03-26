package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoCreateTimeChartVO extends MyChartVO {
    public RepoCreateTimeChartVO() {
        super();
        super.fields = new String[8];
        fields[0] = "before 2010";
        fields[1] = "2010";
        fields[2] = "2011";
        fields[3] = "2012";
        fields[4] = "2013";
        fields[5] = "2014";
        fields[6] = "2015";
        fields[7] = "2016";

        super.values = new int[8];
        for (int i=0; i<8; i++){
            values [i] =0;
        }
    }

    public void increase(String year) {
        for (int i=1;i<8;i++){
            if (year.equals(fields[i])) {
                values[i] ++;
                return;
            }
        }

        values[0]++;
    }
}
