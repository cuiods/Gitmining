package edu.nju.model.imp;

import edu.nju.entity.SecRegisterLabelEntity;
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
public class LoginModelImplTest {

    @Resource
    private LoginModelImpl loginModel;

    @Test
    public void register() throws Exception {
        loginModel.register("harry14","12345","hhahah@gmail.com");
    }

    @Test
    public void login() throws Exception {
        loginModel.login("harry14","12345");
    }

    @Test
    public void initHobby() throws Exception {
        SecRegisterLabelEntity labelEntity = new SecRegisterLabelEntity();
        labelEntity.setRegisterLogin("harry14");
        labelEntity.setTemplate(1.0);
        labelEntity.setApp(1.0);
        loginModel.initHobby(labelEntity);
    }

}