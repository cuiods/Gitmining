package edu.nju.serviceImpl;

import edu.nju.dao.CompareDao;
import edu.nju.service.CompareService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * compare service test case
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class CompareServiceImplTest {

    @Resource
    private CompareService compareService;

    @Test
    public void compareWatch() throws Exception {
        System.out.println("==watch=="+compareService.compareWatch(60));
    }

    @Test
    public void compareStar() throws Exception {
        System.out.println("==star=="+compareService.compareStar(60));
    }

    @Test
    public void compareFork() throws Exception {
        System.out.println("==fork=="+compareService.compareFork(60));
    }

    @Test
    public void comparePeople() throws Exception {

    }

}