package edu.nju.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RepoPopuTest {

    @Resource
    private RepoPopuImp repoPopuImp;

    @Test
    public void RepoPopuStatTest() {
        Map<String,List> maps = repoPopuImp.statPopuLanguage();
        Assert.assertTrue(maps.get("Ruby").size()>maps.get("Java").size());
    }
}
