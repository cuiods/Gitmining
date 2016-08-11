package edu.nju.serviceImpl;

import edu.nju.service.MeaningService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2016/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class MeaningServiceImplTest {

    @Resource
    private MeaningService meaningService;

    @Test
    public void keywordsOfNews() throws Exception {
        System.out.println(meaningService.keywordsOfNews("mojombo","grit"));
    }

    @Test
    public void positiveNews() throws Exception {
        System.out.println(meaningService.positiveNews("mojombo","grit"));
    }

    @Test
    public void commonComments() throws Exception {

    }

    @Test
    public void positiveComments() throws Exception {

    }

}