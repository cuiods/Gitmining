package edu.nju.serviceImpl;

import edu.nju.service.AnalyseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class AnalyseServiceImplTest {

    @Resource
    private AnalyseService analyseService;

    @Test
    public void popularRate() throws Exception {
        System.out.println(analyseService.popularRate("firebug","firebug"));
    }

}