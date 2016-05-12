package edu.nju.dao.service;

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

/**
 * user dao test
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class UserTest {
    @Resource
    UserDaoService userDaoService;
    @Test
    public void testFindUser(){
        TblUser user = userDaoService.findUserByLoginName("rubinius");
        Assert.assertTrue(!(user==null));
    }

    @Test
    public void testSearchUserKeyword(){
        List<TblUser> tblUsers = userDaoService.searchUserByLoginName("0x");
        Assert.assertTrue(tblUsers.size()>1);
    }

    @Test
    public void testTotalCount(){
        long test = userDaoService.getUserTotalCount();
        Assert.assertTrue(test>60000);
    }

    @Test
    public void testGetList() {
//        List<TblUser> users = userDaoService.getUsers(SortType.User_Follored);
//        Assert.assertTrue(users.size()>60000);
        List<TblUser> users = userDaoService.getUsers(10,10);
        Assert.assertTrue(users.size()==10);
        List<TblUser> users1 = userDaoService.getUsers(SortType.User_Follored,0,10);
        Assert.assertTrue(users1.get(0).getFollower()>10000);
    }
}
