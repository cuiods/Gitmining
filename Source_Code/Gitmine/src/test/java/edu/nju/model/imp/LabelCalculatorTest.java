package edu.nju.model.imp;

import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.entity.SecRepoLabelEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class LabelCalculatorTest {

    @Resource
    private LabelCalculator calculator;

    @Test
    public void starRepoCompute() throws Exception {
        SecRegisterLabelEntity entity = new SecRegisterLabelEntity();
        entity.setApp(1.0);
        SecRepoLabelEntity labelEntity = new SecRepoLabelEntity();
        labelEntity.setApp(1);
        calculator.starRepoCompute(entity,labelEntity);
    }

    @Test
    public void unstarRepoCompute() throws Exception {
        SecRegisterLabelEntity entity = new SecRegisterLabelEntity();
        entity.setApp(1.0);
        SecRepoLabelEntity labelEntity = new SecRepoLabelEntity();
        labelEntity.setApp(1);
        calculator.unstarRepoCompute(entity,labelEntity);
    }

}