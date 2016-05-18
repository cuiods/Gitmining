package edu.nju.model.imp;

import edu.nju.model.pojo.SimpleChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/18.
 * test for repo statistic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class RepoStatsImplTest {

    @Resource
    private RepoStatsImpl repoStats;

    @Test
    public void statsCreateTime() throws Exception {
        SimpleChart chart = repoStats.statsCreateTime();
        assertNotNull(chart);
        String [] field = chart.getField();
        long [] value = chart.getValue();
        for (String s:field){
            System.out.print(s+"  ");
        }
        System.out.print('\n');
        for (long l : value){
            System.out.print(l+"  ");
        }
        System.out.print('\n');
    }

    @Test
    public void statsForkCount() throws Exception {

    }

    @Test
    public void statsStarCount() throws Exception {

    }

    @Test
    public void statsLanguage() throws Exception {

    }

    @Test
    public void statsSize() throws Exception {

    }

}