package edu.nju.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2016/8/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class DataLogicTest {

    @Resource
    private DataLogic dataLogic;

    @Test
    public void startLogic() throws Exception {
        dataLogic.startLogic(150,500);
    }

}