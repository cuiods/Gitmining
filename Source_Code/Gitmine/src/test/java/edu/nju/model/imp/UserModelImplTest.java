package edu.nju.model.imp;

import edu.nju.common.SortType;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
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
        //todo
    }

    @Test
    public void getRelatedUser() throws Exception {
        //todo
    }

    @Test
    public void getRelatedRepo() throws Exception {
        List<RepoVO> list = userModel.getRelatedRepo("jquery");
        assertNotNull(list);
        assertTrue(list.size()>0);
    }

    @Test
    public void getPopularUser() throws Exception {
        List<UserVO> list  = userModel.getPopularUser();
        assertNotNull(list);
        assertTrue(list.size()>0);
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
        UserVO vo = userModel.getUserBasicInfo("jquery");
        assertNotNull(vo);
        assertTrue(vo.getLogin().equals("jquery"));
    }

    @Test
    public void getUserRadarChart() throws Exception {
        RadarChart chart = userModel.getUserRadarChart("jquery");
        assertNotNull(chart);
    }

}