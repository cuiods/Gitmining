package edu.nju.git.VO.chartvo;

/**
 * Created by Harry on 2016/3/26.
 */
public class RepoLanguChartVO extends MyChartVO {
    public RepoLanguChartVO() {
        super();
        super.fields = new String[11];
        fields[0] = "Java";
        fields[1] = "C";
        fields[2] = "C++";
        fields[3] = "PHP";
        fields[4] = "JavaScript";
        fields[5] = "Perl";
        fields[6] = "Haskell";
        fields[7] = "HTML";
        fields[8] = "Ruby";
        fields[9] = "Python";
        fields[10] = "Other";

        super.values = new int[11];
        for (int i=0;i<11;i++) {
            values[i] = 0;
        }
    }

    public void increase (String language) {
        for (int i=0;i<10;i++) {
            if (language.equals(fields[i])) {
                values[i] ++;
                return;
            }
        }
        values [10] ++;
    }
}
