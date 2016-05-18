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
 * test for user
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class UserStatsImplTest {

    @Resource
    private UserStatsImpl userStats;

    @Test
    public void statsUserType() throws Exception {
        SimpleChart chart = userStats.statsUserType();
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
    public void statsUserRepo() throws Exception {
        SimpleChart chart = userStats.statsUserRepo();
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
    public void statsUserGist() throws Exception {
        SimpleChart chart = userStats.statsUserGist();
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
    public void statsUserFollower() throws Exception {
        SimpleChart chart = userStats.statsUserFollower();
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
    public void statsUserCreateTime() throws Exception {
        SimpleChart chart = userStats.statsUserCreateTime();
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
    public void statsUserEmail() throws Exception {
        SimpleChart chart = userStats.statsUserEmail();
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
    public void statsUserCompany() throws Exception {
        SimpleChart chart = userStats.statsUserCompany();
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

}