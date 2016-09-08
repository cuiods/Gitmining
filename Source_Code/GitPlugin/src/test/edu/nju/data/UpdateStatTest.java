package edu.nju.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuiods on 2016/9/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class UpdateStatTest {

    @Resource
    private UpdateStat updateStat;

    @Test
    public void updateStat() throws Exception {
        //updateStat.updateStat(80000,20000);
    }

}