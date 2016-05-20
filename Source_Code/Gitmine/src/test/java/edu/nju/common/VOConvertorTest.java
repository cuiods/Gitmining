package edu.nju.common;

import edu.nju.dao.impl.UserDaoImp;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/19.
 * test for vo convertor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class VOConvertorTest {

    @Resource
    private VOConvertor voConvertor;

    @Resource
    private UserDaoService userDaoImp;

    @Resource
    private RepoDaoService repoDaoImpl;

    @Test
    public void convert() throws Exception {
        TblUser tblUser = userDaoImp.findUserByLoginName("jquery");
        UserVO userVO = voConvertor.convert(tblUser);
        assertNotNull(userVO);
        assertTrue(userVO.getLogin().equals(tblUser.getLoginName()));
        System.out.println("time: "+userVO.getCreateAt());
    }

    @Test
    public void convert1() throws Exception {
        TblRepo tblRepo = repoDaoImpl.getRepoBasicInfo("jquery","jquery");
        RepoVO repoVO = voConvertor.convert(tblRepo);
        assertNotNull(repoVO);
        assertFalse(repoVO.getOwnerAvatarUrl().isEmpty());
        System.out.println("time : "+repoVO.getCreateAt());
    }

}