package edu.nju.model.statistic;

import java.math.BigDecimal;
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
    private BigDecimal C = BigDecimal.ZERO;
    /**
     * total sum of squares
     */
    private BigInteger Qt = BigInteger.ZERO;
    /**
     * total sum of variances
     */
    private BigInteger St = BigInteger.ZERO;
    /**
     * sum of data squares of different levels
     */
    private BigInteger Qa = BigInteger.ZERO;
    /**
     * sum of data variances of different levels
     */
    private BigInteger Sa = BigInteger.ZERO;
    /**
     * sum of horizontal variances
     */
    private BigInteger Se = BigInteger.ZERO;
    /**
     * unbiased estimation Va
     */
    private BigInteger Va = BigInteger.ZERO;
    /**
     * unbiased estimation Ve
     */
    private BigInteger Ve = BigInteger.ZERO;

    public OneWayANOVA(List<List<Integer>> data) {
        this.data = data;
    }

    /**
     * core of one-way ANOVA
     * @return
     *      F value of analysis
     */
    public int doAnalysis() {
        return 0;
    }

    private void initialize() {
        for (List<Integer> list : data) {
            for (Integer integer: list) {
                C.add(BigDecimal.valueOf(integer));
            }
        }
        C = C.pow(2);
    }
}
