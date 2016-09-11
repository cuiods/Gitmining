package edu.nju.dao.impl;

import edu.nju.dao.RepoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class RepoDaoImplTest {

    @Resource
    private RepoDao repoDao;

    @Test
    public void getRepoFollowers() throws Exception {
        System.out.println(repoDao.getRepoFollowers("firebug","firebug"));
    }

    @Test
    public void getFollowerNum() throws Exception {
        System.out.println(repoDao.getFollowerNum("cuiods"));
    }

    @Test
    public void getForkandSize() throws Exception {
        System.out.println(repoDao.getForkandSize("firebug","firebug")[0]);
    }


}