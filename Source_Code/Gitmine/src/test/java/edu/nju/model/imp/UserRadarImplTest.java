package edu.nju.model.imp;

import edu.nju.model.pojo.RadarChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/17.
 * test for user radar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class UserRadarImplTest {

    @Resource
    private UserRadarImpl userRadarImpl;

    @Test
    public void getUserRadar() throws Exception {
        RadarChart chart = userRadarImpl.getUserRadar("mojombo");
        assertNotNull(chart);
        String [] field = chart.getField();
        double [] value = chart.getValue();
        for (String f:field){
            System.out.print(f+"  ");
        }
        System.out.println();
        for (double v:value){
            System.out.print(v+"  ");
        }
    }

}