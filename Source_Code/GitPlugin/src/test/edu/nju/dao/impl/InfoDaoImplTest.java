package edu.nju.dao.impl;

import edu.nju.dao.InfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by cuihao on 2016/8/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/META-INF/applicationContext.xml"})
public class InfoDaoImplTest {

    @Resource
    private InfoDao infoDao;

    @Test
    public void getRepoId() throws Exception {
    }

    @Test
    public void getCommentsByName() throws Exception {
        System.out.println(infoDao.getCommentsByName("benalavi","cookbooks",10,1));
    }

    @Test
    public void getNewsByName() throws Exception {

    }

}