package edu.nju.model.imp;

import edu.nju.model.statistic.DataPoint;
import edu.nju.model.statistic.RegressionLine;
import edu.nju.model.statistic.StarRegressionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * repo popular statistic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class StatisticTest {

    @Resource
    private StarRegressionService regressionService;

    @Test
    public void regressionLineTest() {
        System.out.println(regressionService.getForkRegression());
        System.out.println(regressionService.getWatcherRegression());
//        RegressionLine line = new RegressionLine();
//
//        line.addDataPoint(new DataPoint(123, 136));
//        line.addDataPoint(new DataPoint(2, 143));
//        line.addDataPoint(new DataPoint(3, 132));
//        line.addDataPoint(new DataPoint(4, 142));
//        line.addDataPoint(new DataPoint(5, 147));
//        line.addDataPoint(new DataPoint(5, 147));
//
//        System.out.println("\n回归线公式:  y = " + line.getA1() + "x + "
//                + line.getA0());
//        System.out.println("误差：     R^2 = " + line.getR());
    }
}
