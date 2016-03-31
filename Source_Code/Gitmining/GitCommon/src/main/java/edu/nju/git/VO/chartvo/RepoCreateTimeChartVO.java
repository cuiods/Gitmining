package edu.nju.git.VO.chartvo;

/**
 * Created by harry on 16-3-26.
 */
public class RepoCreateTimeChartVO extends MyChartVO {
    public RepoCreateTimeChartVO() {
        super();
        super.fields = new String[8];
        fields[0] = "before 2008";
        fields[1] = "2008";
        fields[2] = "2009";
        fields[3] = "2010";
        fields[4] = "2011";
        fields[5] = "2012";
        fields[6] = "2013";
        fields[7] = "after 2013";

        super.values = new int[8];
        for (int i=0; i<7; i++){
            values [i] =0;
        }
    }

    public void increase(String year) {
        for (int i=1;i<7;i++){
            if (year.equals(fields[i])) {
                values[i] ++;
                return;
            }
        }
        if (year.compareTo("2010")>0){
            values[7]++;
        }
        else {
            values[0]++;
        }
    }
}
