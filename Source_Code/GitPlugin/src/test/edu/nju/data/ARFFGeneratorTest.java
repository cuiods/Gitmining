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
        Instances instances = arffGenerator.generatePunchInstanceFromDB(0,100000);
        arffGenerator.generateArffFile(instances,"/wekaFile/repo_punch.arff");
    }

    @Test
    public void genetateActivityInstanceFromDB() throws Exception {
        Instances instances = arffGenerator.genetateActivityInstanceFromDB(0,10);
        System.out.println("+++++activity+++++\n"+instances.toString());
    }

    @Test
    public void generatePunchInstanceFromDB() throws Exception {
        Instances instances = arffGenerator.generatePunchInstanceFromDB(0,110);
        System.out.println("++++++punch+++++\n"+instances.toString());
    }

    @Test
    public void generateOwnerInstanceFromDB() throws Exception {
        Instances instances = arffGenerator.generateOwnerInstanceFromDB(0,10);
        System.out.println("++++++owner+++++\n"+instances.toString());
    }

}