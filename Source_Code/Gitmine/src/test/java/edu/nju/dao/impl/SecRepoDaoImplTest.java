package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.entity.SecRepoEntity;
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
public class SecRepoDaoImplTest {


    @Resource
    private SecRepoDaoImpl repoDao;

    @Test
    public void getSearchResult() throws Exception {
        List<SecRepoEntity> list = repoDao.getSearchResult("a",0,100, SortType.Repo_Star,true,"","Javascript","");
        assertNotNull(list);
        assertTrue(list.size()>0);
        int cnt = 0;
        for(SecRepoEntity entity:list){
            System.out.println(cnt++ + entity.getName());
        }
    }

    @Test
    public void getRepos() throws Exception {
        List<SecRepoEntity> list = repoDao.getRepos(SortType.Repo_Star,true,0,10);
        assertNotNull(list);
        assertTrue(list.size()>0);
        System.out.println("==========getRepos============>>>"+list.get(0).getName());
    }

    @Test
    public void getRepoBasicInfo() throws Exception {
        SecRepoEntity entity = repoDao.getRepoBasicInfo("jpoler","-");
        assertNotNull(entity);
        System.out.println("=================basic info==============>>>"+entity.getHtmlUrl());
    }

    @Test
    public void getRepoContributor() throws Exception {
        List<String> list = repoDao.getRepoContributor("mojombo","grit");
        assertTrue(list.size()>0);
    }

    @Test
    public void getRepoSubscriber() throws Exception {
        List<String> list = repoDao.getRepoSubscriber("mojombo","grit");
        assertTrue(list.size()>0);
    }

    @Test
    public void getMaxRepoSize() throws Exception {
        double max = repoDao.getMaxRepoSize();
        assertTrue(max>1000);
        System.out.println("=================max size==============>>>"+max);
    }

    @Test
    public void getMinRepoSize() throws Exception {
        double min = repoDao.getMinRepoSize();
        assertTrue(min>=0);
        System.out.println("=================min size==============>>>"+min);
    }

    @Test
    public void getMaxRepoFork() throws Exception {
        double max = repoDao.getMaxRepoFork();
        assertTrue(max>100);
        System.out.println("=================max fork==============>>>"+max);
    }

    @Test
    public void getMinRepoFork() throws Exception {
        double min = repoDao.getMinRepoFork();
        assertTrue(min>=0);
        System.out.println("=================min fork==============>>>"+min);
    }

    @Test
    public void getMaxRepoStar() throws Exception {
        double max = repoDao.getMaxRepoStar();
        assertTrue(max>100);
        System.out.println("=================max star==============>>>"+max);
    }

    @Test
    public void getMinRepoStar() throws Exception {
        double min = repoDao.getMinRepoStar();
        assertTrue(min>=0);
        System.out.println("=================min star==============>>>"+min);
    }

    @Test
    public void getMaxRepoContriCount() throws Exception {
        double max = repoDao.getMaxRepoContriCount();
        assertTrue(max>100);
        System.out.println("=================max contributor count==============>>>"+max);
    }

    @Test
    public void getMinRepoContriCount() throws Exception {
        double min = repoDao.getMinRepoContriCount();
        assertTrue(min>=0);
        System.out.println("=================min contributor count==============>>>"+min);
    }

    @Test
    public void getMaxCommitCount() throws Exception {
        double max = repoDao.getMaxCommitCount();
        System.out.println("=================max commit count==============>>>"+max);
    }

    @Test
    public void getMinCommitCount() throws Exception {
        double min = repoDao.getMinCommitCount();
        System.out.println("=================min commit count==============>>>"+min);
    }

    @Test
    public void getRepoContriCount() throws Exception {
        double cnt = repoDao.getRepoContriCount("mojombo","grit");
        System.out.println("=================contri count==============>>>"+cnt);
    }

    @Test
    public void getRepoCommitCount() throws Exception {
        double cnt = repoDao.getRepoCommitCount("jpoler","-");
        System.out.println("=================commit count==============>>>"+cnt);
    }

    @Test
    public void getStatsCreateTime() throws Exception {
        List<Object[]> list = repoDao.getStatsCreateTime();
        assertNotNull(list);
    }

    @Test
    public void getStatsFork() throws Exception {
        double cnt = repoDao.getStatsFork(10,100);
        assertTrue(cnt>0);
    }

    @Test
    public void getStatsStar() throws Exception {
        double cnt = repoDao.getStatsStar(10,100);
        assertTrue(cnt>0);
    }

    @Test
    public void getStatsLanguage() throws Exception {
        List<Object[]> list = repoDao.getStatsCreateTime();
        assertNotNull(list);
    }

    @Test
    public void getStatsSize() throws Exception {
        double cnt = repoDao.getStatsSize(1000,100000);
        assertTrue(cnt>0);
    }

    @Test
    public void getSearchCount() throws Exception {
        repoDao.getSearchCount("ab","","Ruby","2015");
    }

    @Test
    public void getTotalCount() throws Exception {
        repoDao.getTotalCount();
    }

    @Test
    public void getRelatedRepo() throws Exception {
        List list = repoDao.getRelatedRepo("mrdoob","three.js");
    }

    @Test
    public void getRecommendRepo() throws Exception {
        List<SecRepoEntity> list = repoDao.getRecommendRepo("hhhhhh",0,10);
        for (SecRepoEntity entity:list){
            System.out.println(entity.getName());
        }
    }

    @Test
    public void searchWithLogin() throws Exception {
        List<SecRepoEntity> list = repoDao.searchWithLogin("hhhhhh","a",0,10,"","java","");
        for (SecRepoEntity entity:list){
            System.out.println(entity.getName());
        }
    }

    @Test
    public void getRepoLabel() throws Exception {
        repoDao.getRepoLabel("mojombo","grit");
    }

}