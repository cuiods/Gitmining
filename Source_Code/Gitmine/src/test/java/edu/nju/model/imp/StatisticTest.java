package edu.nju.model.imp;

import edu.nju.model.statistic.DataPoint;
import edu.nju.model.statistic.RegressionLine;
import org.junit.Test;

/**
 * repo popular statistic
 */
public class StatisticTest {

    @Test
    public void regressionLineTest() {
        RegressionLine line = new RegressionLine();

        line.addDataPoint(new DataPoint(1, 136));
        line.addDataPoint(new DataPoint(2, 143));
        line.addDataPoint(new DataPoint(3, 132));
        line.addDataPoint(new DataPoint(4, 142));
        line.addDataPoint(new DataPoint(5, 147));

        System.out.println("\n回归线公式:  y = " + line.getA1() + "x + "
                + line.getA0());
        System.out.println("误差：     R^2 = " + line.getR());
    }
}
