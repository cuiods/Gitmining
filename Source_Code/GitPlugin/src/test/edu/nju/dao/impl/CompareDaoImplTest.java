package edu.nju.dao.impl;

import edu.nju.dao.CompareDao;
import edu.nju.service.CompareService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * test of compare
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class CompareDaoImplTest extends TestCase {

    @Resource
    private CompareDao compareDao;

    @Test
    public void testRangeOfWatch() throws Exception {
        System.out.println("========watch======="+compareDao.rangeOfWatch(100));
    }

    @Test
    public void testRangeOfStar() throws Exception {
        System.out.println("=======star========"+compareDao.rangeOfStar(100));
    }

    @Test
    public void testRangeOfFork() throws Exception {
        System.out.println("=======fork========"+compareDao.rangeOfFork(100));
    }

    @Test
    public void testSumRepo() throws Exception {
        System.out.println("=======sum========"+compareDao.sumRepo());
    }

}