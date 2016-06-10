package edu.nju.dao.impl;

import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;
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
        assertTrue(registerDaoImp.existName("harry14"));
    }

    @Test
    public void existUser() throws Exception {
        assertTrue(registerDaoImp.existUser("haha","hhahah@gmail.com"));
    }

    @Test
    public void login() throws Exception {
        assertTrue(registerDaoImp.login("harry14","12345"));
        assertFalse(registerDaoImp.login("harry","123456"));
    }

    @Test
    public void starRepo() throws Exception {
        //assertTrue(registerDaoImp.starRepo("harry","mojombo","grit"));
    }

    @Test
    public void unstarRepo() throws Exception {
        //assertTrue(registerDaoImp.unStarRepo("harry","mojombo","grit"));
    }

    @Test
    public void getStaredRepos() throws Exception {
        List<SecRepoEntity> list = registerDaoImp.getStaredRepos("harry14",16,16);
        assertTrue(list.size()>0);
        for (SecRepoEntity entity:list)
            System.out.println(entity.getOwner()+"-->"+entity.getName()+"-->"+entity.getLanguage()+"===");
    }

    @Test
    public void register() throws Exception {

    }

    @Test
    public void getRegisterInterest() throws Exception {
        registerDaoImp.getRegisterInterest("hhhhhh");
    }

    @Test
    public void saveOrUpdateRegisterInterest() throws Exception {
        SecRegisterLabelEntity entity = new SecRegisterLabelEntity();
        entity.setTemplate(2.0);
        registerDaoImp.saveOrUpdateRegisterInterest(entity);
    }

    @Test
    public void unStarRepo() throws Exception {
        assertTrue(registerDaoImp.unStarRepo("harry","defunkt","ambition"));
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
        List<SecUserEntity> list = registerDaoImp.getStaredUsers("hhhhhh",0,10);
        for (SecUserEntity entity:list){
            System.out.println(entity.getName()+"------------------");
        }
    }

    @Test
    public void getStaredUserCount() throws Exception {
        System.out.println(registerDaoImp.getStaredUserCount("harry"));
    }

    @Test
    public void getStaredRepoCount() throws Exception {
        System.out.println(registerDaoImp.getStaredRepoCount("harry"));
    }

    @Test
    public void getStaredReponame() throws Exception {
        List<Object[]> list = registerDaoImp.getStaredReponame("harry14");
        for (Object[] item:list){
            System.out.println(item[0]+"---"+item[1]);
        }
    }

    @Test
    public void getStaredUsername() throws Exception {
        List<String> list = registerDaoImp.getStaredUsername("harry14");
        for (String s:list){
            System.out.println(s);
        }
    }
}