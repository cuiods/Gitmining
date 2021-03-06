package edu.nju.model.imp;

import edu.nju.common.SortType;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleRepoVO;
import edu.nju.model.pojo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/19.
 * test for user model
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class UserModelImplTest {

    @Resource
    private UserModelImpl userModel;

    @Test
    public void getRecommendUser() throws Exception {
        List<UserVO> list = userModel.getRecommendUser("harry14",0,10);
        for (UserVO vo:list){
            System.out.println(vo.getLogin());
        }
    }

    @Test
    public void getRelatedUser() throws Exception {
        userModel.getRelatedUser("mojombo",5);
    }

    @Test
    public void getPopularUser() throws Exception {
        List<UserVO> list  = userModel.getPopularUser(0,10);
        assertNotNull(list);
        assertTrue(list.size()>0);
    }

    @Test
    public void getContributeRepo() throws Exception {
        List<SimpleRepoVO> repos = userModel.getContributeRepo("mojombo",5);
        assertTrue(repos.size() == 5);
    }

    @Test
    public void getSubscribeRepo() throws Exception {
        List<SimpleRepoVO> repos = userModel.getSubscribeRepo("mojombo",5);
        assertTrue(repos.size() <= 5);
    }

    @Test
    public void getTotalPage() throws Exception {
        int page = userModel.getTotalPage();
        assertTrue(page>10);
    }

    @Test
    public void getUsers() throws Exception {
        List<UserVO> list = userModel.getUsers(SortType.User_Follored,true,0,10);
        assertNotNull(list);
        assertTrue(list.size() == 10);
    }

    @Test
    public void getSearchResult() throws Exception {
        List<UserVO> list = userModel.getSearchResult("aa","hahhah",1,true);
        assertNotNull(list);
    }

    @Test
    public void getUserBasicInfo() throws Exception {
        UserVO vo = userModel.getUserBasicInfo("mojombo");
        assertNotNull(vo);
        assertTrue(vo.getLogin().equals("mojombo"));
    }

    @Test
    public void getUserRadarChart() throws Exception {
        RadarChart chart = userModel.getUserRadarChart("mojombo");
        assertNotNull(chart);
    }

    @Test
    public void getSearchPage() throws Exception {
        assertTrue(userModel.getSearchPage("mo") > 0);
    }

    @Test
    public void getUserLanguage() throws Exception {
        userModel.getUserLanguage("mojombo");
    }

}