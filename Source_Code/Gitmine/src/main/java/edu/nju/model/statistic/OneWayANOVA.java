package edu.nju.model.statistic;

import java.math.BigInteger;
import java.util.List;

/**
 * Single factor analysis of variance
 * @author cuihao
 */
public class OneWayANOVA {

    private List<List<Integer>> data;
    /**
     * correction term
     */
    private double C = 0;
    /**
     * total sum of squares
     */
    private double Qt = 0;
    /**
     * total sum of variances
     */
    private double St = 0;
    /**
     * sum of data squares of different levels
     */
    private double Qa = 0;
    /**
     * sum of data variances of different levels
     */
    private double Sa = 0;
    /**
     * sum of horizontal variances
     */
    private double Se = 0;
    /**
     * unbiased estimation Va
     */
    private double Va = 0;
    /**
     * unbiased estimation Ve
     */
    private double Ve = 0;

    public OneWayANOVA(List<List<Integer>> data) {
        this.data = data;
    }

    /**
     * core of one-way ANOVA
     * @return
     *      F value of analysis
     */
    public double doAnalysis() {
        initialize();
        if (Ve != 0) {
            return Va / Ve;
        }
        return -1;
    }

    private void initialize() {
        C = Qt = St = Qa = Sa = Se = Va = Ve = 0;
        long count = 0;
        for (List<Integer> list : data) {
            double temp = 0;
            for (Integer integer: list) {
                C = C + integer.doubleValue();
                temp = temp + integer.doubleValue();
                Qt = Qt + Math.pow(integer,2);
                count++;
            }
            Qa = Qa + Math.pow(temp, 2) / list.size();
        }
        C = Math.pow(C, 2) / count;
        St = Qt - C;
        Sa = Qa - C;
        Se = St - Sa;
        Va = Sa / (data.size() - 1);
        Ve = Se / (count - data.size());
    }
}
