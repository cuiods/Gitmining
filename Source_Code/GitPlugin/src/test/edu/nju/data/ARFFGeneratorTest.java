package edu.nju.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weka.core.Instances;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2016/9/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class ARFFGeneratorTest {

    @Resource
    private ARFFGenerator arffGenerator;

    @Test
    public void generatePopularInstanceFromDB() throws Exception {
        Instances instances = arffGenerator.generatePopularInstanceFromDB(0,10);
        System.out.println("+++++++size+++++++++"+instances.toString());
    }

    @Test
    public void generateArffFile() throws Exception {
        Instances instances = arffGenerator.generatePopularInstanceFromDB(0,10);
        arffGenerator.generateArffFile(instances);
    }

}