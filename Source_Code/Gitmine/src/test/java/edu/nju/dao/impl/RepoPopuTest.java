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
 * popularity stat test
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RepoPopuTest {

    @Resource
    private RepoPopuDaoImp repoPopuImp;

    @Test
    public void RepoPopuStatTest() {
        Map<String,List> maps = repoPopuImp.statPopuLanguage();
        List<String> languages = maps.get("language");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println(languages.get(i)+"---"+maps.get("lan"+i));
        }
    }

    @Test
    public void RepoStarRelation() {
        Map<String,List> map = repoPopuImp.statStarRelation(100);
        System.out.println(map.get("fork"));
        System.out.println(map.get("watcher"));
    }
}
