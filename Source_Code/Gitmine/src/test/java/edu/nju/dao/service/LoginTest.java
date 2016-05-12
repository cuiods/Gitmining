package edu.nju.dao.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2016/5/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class LoginTest {
    @Resource
    RegisterDaoService registerDaoService;
    @Test
    public void testExistNameTrue() {
        boolean test = registerDaoService.existName("cuiods");
        Assert.assertEquals(true,test);
        boolean test2 = registerDaoService.existName("233");
        Assert.assertEquals(false,test2);
    }}
