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

import java.util.Calendar;
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
        List<TblUser> tblUsers = userDaoImp.searchUserByLoginName("0x",SortType.Repo_Star,true,0,16);
        Assert.assertTrue(tblUsers.size()>1);
        System.out.println(tblUsers.get(0).getName());
    }

    @Test
    public void getUserTotalCount() throws Exception {
        long test = userDaoImp.getUserTotalCount();
        Assert.assertTrue(test>60000);
    }

    @Test
    public void getUsers1() throws Exception {
        List<TblUser> users = userDaoImp.getUsers(10,10);
        Assert.assertTrue(users.size()==10);
    }

    @Test
    public void getUsers2() throws Exception {
        List<TblUser> users1 = userDaoImp.getUsers(SortType.User_Follored,true, 0,10);
        Assert.assertTrue(users1.get(0).getFollower()>10000);
    }

    @Test
    public void getUserOwnRepos() throws Exception {
        List<Object[]> lists = userDaoImp.getUserOwnRepos("jquery",0,10);
        assertTrue(lists.size()>0);
    }

    @Test
    public void getUserSubscribeRepos() throws Exception {
        List<List> lists = userDaoImp.getUserSubscribeRepos("maxlapshin",0,10);
        Assert.assertTrue(lists.size()>0);
    }

    @Test
    public void getUserCollaboratorRepos() throws Exception {
        List<List> lists = userDaoImp.getUserCollaboratorRepos("norbert",0,10);
        Assert.assertTrue(lists.size()>0);
    }

    @Test
    public void getUserContriutorRepos() throws Exception {
        List<List> lists = userDaoImp.getUserContributorRepos("abraxxa",0,10);
        Assert.assertTrue(lists.size()>0);
    }

    @Test
    public void getUserInterest() throws Exception {

    }

    @Test
    public void saveUserInterest() throws Exception {

    }

    @Test
    public void getStatsUserType() throws Exception {
        long user = userDaoImp.getStatsUserType((byte) 0);
        long org = userDaoImp.getStatsUserType((byte) 1);
        assertTrue(user>0);
        assertTrue(org>0);
        System.out.println("user: "+user+"  org: "+org);
    }

    @Test
    public void getStatsUserOwnRepo() throws Exception {
        long repo = userDaoImp.getStatsUserOwnRepo(10, 100);
        assertTrue(repo>0);
        System.out.println("repos between 10 and 100: "+repo);
    }

    @Test
    public void getStatsUserGist() throws Exception {
        long gist = userDaoImp.getStatsUserGist(10, 100);
        assertTrue(gist>0);
        System.out.println("gists between 10 and 100: "+gist);
    }

    @Test
    public void getStatsUserFollower() throws Exception {
        long follower = userDaoImp.getStatsUserFollower(10, 100);
        assertTrue(follower>0);
        System.out.println("follower between 10 and 100: "+follower);
    }

    @Test
    public void getStatsCreateTime() throws Exception {
        Calendar cFrom = Calendar.getInstance();
        Calendar cTo = Calendar.getInstance();
        cFrom.set(2010,Calendar.JANUARY, 1, 0, 0, 0);
        cTo.set(2010,Calendar.DECEMBER, 31, 23, 59, 59);
        long people  = userDaoImp.getStatsCreateTime(cFrom, cTo);
        assertTrue(people>0);
        System.out.println("2010 create user count: "+people);
    }

    @Test
    public void getStatsEmail() throws Exception {
        List list = userDaoImp.getStatsEmail(10);
        assertTrue(list.size() == 10);
        Object[] item = (Object[])list.get(1);
        System.out.println(item[0]+"   "+item[1]);
    }

    @Test
    public void getStatsCompany() throws Exception {
        List list = userDaoImp.getStatsCompany(10);
        assertTrue(list.size()==10);
        Object[] item = (Object[])list.get(0);
        System.out.println(item[0] + "   "+item[1]);
    }

    @Test
    public void getMaxUserContribute() throws Exception {
        double max = userDaoImp.getMaxUserContribute();
        assertTrue(max>0);
        System.out.println("max user contribute is: "+max);
    }

    @Test
    public void getMaxUserValue() throws Exception {
        double maxValue = userDaoImp.getMaxUserValue();
        assertTrue(maxValue>0);
        System.out.println("max user value is: " +maxValue);
    }

    @Test
    public void getUserContribute() throws Exception {
        double ac = userDaoImp.getUserContribute("zertosh");
        assertTrue(ac>0);
        System.out.println(" zertosh active is: " +ac);
        double cc = userDaoImp.getUserContribute("hhh");
        assertTrue(cc==0);
    }

    @Test
    public void getUserValue() throws Exception {
        double val = userDaoImp.getUserValue("rubinius");
        assertTrue(val>0);
        System.out.println("rubinius value is: " +val);
    }

    @Test
    public void getMaxRepos() throws Exception {
        double repos = userDaoImp.getMaxRepos();
        assertTrue(repos>0);
        System.out.println("max repo count is:" +repos);
    }

    @Test
    public void getMaxGists() throws Exception {
        double gists = userDaoImp.getMaxGists();
        assertTrue(gists>0);
        System.out.println("max gist count is:" +gists);
    }

    @Test
    public void getMaxFollower() throws Exception {
        double follower = userDaoImp.getMaxFollower();
        assertTrue(follower>0);
        System.out.println("max follower count is:" +follower);
    }
}