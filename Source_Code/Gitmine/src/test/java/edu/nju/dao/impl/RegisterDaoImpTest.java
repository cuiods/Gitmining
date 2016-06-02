package edu.nju.dao.impl;

import edu.nju.entity.SecRepoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RegisterDaoImpTest {


    @Resource
    private RegisterDaoImp registerDaoImp;

    @Test
    public void existName() throws Exception {
        assertTrue(registerDaoImp.existName("harry"));
    }

    @Test
    public void existUser() throws Exception {
        assertTrue(registerDaoImp.existUser("haha","harry@163.com"));
    }

    @Test
    public void login() throws Exception {
        assertTrue(registerDaoImp.login("harry","12345"));
        assertFalse(registerDaoImp.login("harry","123456"));
    }

    @Test
    public void starRepo() throws Exception {
        assertTrue(registerDaoImp.starRepo("harry","defunkt","ambition"));
    }

    @Test
    public void unstarRepo() throws Exception {
        assertTrue(registerDaoImp.unStarRepo("harry","mojombo","grit"));
    }

    @Test
    public void getStaredRepos() throws Exception {
        List<SecRepoEntity> list = registerDaoImp.getStaredRepos("harry",0,10);
        assertTrue(list.size()>0);
        SecRepoEntity entity = list.get(0);
        System.out.println(entity.getOwner()+"-->"+entity.getName()+"-->"+entity.getLanguage()+"===");
    }

    @Test
    public void register() throws Exception {

    }

    @Test
    public void getRegisterInterest() throws Exception {

    }

    @Test
    public void saveOrUpdateRegisterInterest() throws Exception {

    }

    @Test
    public void unStarRepo() throws Exception {

    }

    @Test
    public void starUser() throws Exception {
        boolean re = registerDaoImp.starUser("mojombo","harry");
    }

    @Test
    public void unStarUser() throws Exception {

    }

    @Test
    public void getStaredUsers() throws Exception {
        registerDaoImp.getStaredUsers("harry",0,10);
    }

    @Test
    public void getStaredUserCount() throws Exception {

    }

    @Test
    public void getStaredRepoCount() throws Exception {

    }
}