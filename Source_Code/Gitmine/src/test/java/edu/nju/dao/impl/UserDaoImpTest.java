package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.entity.TblUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/16.
 * test for user dao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class UserDaoImpTest {

    @Resource
    private UserDaoImp userDaoImp;

    @Test
    public void findUserByLoginName() throws Exception {
        TblUser user = userDaoImp.findUserByLoginName("rubinius");
        assertNotNull(user);

        TblUser noUser = userDaoImp.findUserByLoginName("hahahah");
        assertNull(noUser);
    }

    @Test
    public void searchUserByLoginName() throws Exception {
        List<TblUser> tblUsers = userDaoImp.searchUserByLoginName("0x",0,20);
        Assert.assertTrue(tblUsers.size()>1);
        System.out.println(tblUsers.get(0).getName());
    }

    @Test
    public void getUserTotalCount() throws Exception {
        long test = userDaoImp.getUserTotalCount();
        Assert.assertTrue(test>60000);
    }

    @Test
    public void getUsers() throws Exception {
        List<TblUser> users = userDaoImp.getUsers(10,10);
        Assert.assertTrue(users.size()==10);
    }

    @Test
    public void getUsers1() throws Exception {

        List<TblUser> users1 = userDaoImp.getUsers(SortType.User_Follored,true, 0,10);
        Assert.assertTrue(users1.get(0).getFollower()>10000);
    }

    @Test
    public void getUsers2() throws Exception {

    }

    @Test
    public void getUserSubscribeRepos() throws Exception {

    }

    @Test
    public void getUserCollaboratorRepos() throws Exception {

    }

    @Test
    public void getUserContriutorRepos() throws Exception {

    }

    @Test
    public void getUserInterest() throws Exception {

    }

    @Test
    public void saveUserInterest() throws Exception {

    }

    @Test
    public void getStatsUserType() throws Exception {

    }

    @Test
    public void getStatsUserOwnRepo() throws Exception {

    }

    @Test
    public void getStatsUserGist() throws Exception {

    }

    @Test
    public void getStatsUserFollower() throws Exception {

    }

    @Test
    public void getStatsCreateTime() throws Exception {

    }

    @Test
    public void getStatsEmail() throws Exception {

    }

    @Test
    public void getStatsCompany() throws Exception {

    }

}