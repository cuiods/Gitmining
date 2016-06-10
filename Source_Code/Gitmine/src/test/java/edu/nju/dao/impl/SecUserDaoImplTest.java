package edu.nju.dao.impl;

import edu.nju.common.SortType;
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
 * Created by Harry on 2016/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class SecUserDaoImplTest {


    @Resource
    private SecUserDaoImpl userDao;

    @Test
    public void getRecommendUser() throws Exception {
        List<SecUserEntity> list = userDao.getRecommendUser("harry14",0,10);
        for (SecUserEntity entity:list){
            System.out.println(entity.getName());
        }
    }

    @Test
    public void getUserLanguage() throws Exception {
        userDao.getUserLanguage("harry14");
    }

    @Test
    public void getUserBasicInfo() throws Exception {
        assertNotNull(userDao.getUserBasicInfo("mojombo"));
    }

    @Test
    public void getUserAvatar() throws Exception {
        userDao.getUserAvatar("mojombo");
    }

    @Test
    public void getAllUserLocation() throws Exception {
        List<String> list = userDao.getAllUserLocation();
//        for (String s:list){
//            System.out.println(s);
//        }
    }

    @Test
    public void getSearchResult() throws Exception {
        userDao.getSearchResult("1", SortType.User_Follored,true,0,10);
    }

    @Test
    public void getUsers() throws Exception {
        userDao.getUsers(SortType.User_Update,true,0,10);
    }

    @Test
    public void getRelatedUser() throws Exception {
        List<Object[]> users = userDao.getRelatedUser("mojombo",10);
        for (Object[] item:users){
            System.out.println(item[0]+"-------->"+item[1]);
        }
    }

    @Test
    public void getTotalCount() throws Exception {
        userDao.getTotalCount();
    }

    @Test
    public void getSearchCount() throws Exception {
        userDao.getSearchCount("mm");
    }

    @Test
    public void getUserOwnRepos() throws Exception {
        userDao.getUserOwnRepos("mojombo",SortType.Repo_Star,0,10);
    }

    @Test
    public void getUserSubscribeRepos() throws Exception {
        userDao.getUserSubscribeRepos("mojombo",5);
    }

    @Test
    public void getUserContributeRepos() throws Exception {
        userDao.getUserContributeRepos("mojombo",5);
    }

    @Test
    public void getStatsUserType() throws Exception {
        userDao.getStatsUserType();
    }

    @Test
    public void getStatsUserOwnRepo() throws Exception {
        userDao.getStatsUserOwnRepo(10,100);
    }

    @Test
    public void getStatsUserGist() throws Exception {
        userDao.getStatsUserGist(10,100);
    }

    @Test
    public void getStatsUserFollower() throws Exception {
        userDao.getStatsUserFollower(10,100);
    }

    @Test
    public void getStatsCreateTime() throws Exception {
        userDao.getStatsCreateTime();
    }

    @Test
    public void getStatsEmail() throws Exception {
        userDao.getStatsEmail(10);
    }

    @Test
    public void getStatsCompany() throws Exception {
        userDao.getStatsCompany(10);
    }

    @Test
    public void getMaxRepos() throws Exception {
        userDao.getMaxRepos();
    }

    @Test
    public void getMaxGists() throws Exception {
        userDao.getMaxGists();
    }

    @Test
    public void getMaxFollower() throws Exception {
        userDao.getMaxFollower();
    }

    @Test
    public void getMaxUserContribute() throws Exception {
        userDao.getMaxUserContribute();
    }

    @Test
    public void getMaxUserFollowing() throws Exception {
        userDao.getMaxUserFollowing();
    }

    @Test
    public void getUserContribute() throws Exception {
        userDao.getUserContribute("mojombo");
    }

}