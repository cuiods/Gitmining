package edu.nju.dao.impl;

import edu.nju.dao.service.LongTailDaoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * long tail test
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class LongTailDaoImplTest {

    @Resource
    private LongTailDaoService longTailDaoService;
    @Test
    public void twentyEightyRate() throws Exception {
        long[] results = longTailDaoService.twentyEightyRate();
        System.out.println(results[0]+" ,"+results[1]);
        System.out.println("rate:"+results[0]*1.0/(results[0]+results[1]));
    }

    @Test
    public void nintyEightRate() throws Exception {
        long[] results = longTailDaoService.nintyEightRate();
        System.out.println("rate:"+results[0]*1.0/results[1]);
    }

    @Test
    public void followerDistribution() throws Exception {
        List<Integer> integers = longTailDaoService.followerDistribution();
        Assert.assertTrue(integers.size()>10000);
    }

}