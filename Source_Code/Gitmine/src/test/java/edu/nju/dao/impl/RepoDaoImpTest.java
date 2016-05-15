package edu.nju.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RepoDaoImpTest {

    @Resource
    private RepoDaoImp repoDaoImp;

    @Test
    public void getSearchResult() throws Exception {

    }

    @Test
    public void getSearchResult1() throws Exception {

    }

    @Test
    public void getTotalCount() throws Exception {

    }

    @Test
    public void getRepos() throws Exception {

    }

    @Test
    public void getRepos1() throws Exception {

    }

    @Test
    public void getRepos2() throws Exception {

    }

    @Test
    public void getRepoBasicInfo() throws Exception {

    }

    @Test
    public void getRepoContributor() throws Exception {

    }

    @Test
    public void getRepoCollaborator() throws Exception {

    }

    @Test
    public void getRepoSubscriber() throws Exception {

    }

    @Test
    public void getRepoInterest() throws Exception {

    }

    @Test
    public void saveRepoInterest() throws Exception {

    }

    @Test
    public void getMaxRepoSize() throws Exception {
        double maxSize = repoDaoImp.getMaxRepoSize();
        assertTrue(maxSize>0);
        System.out.println("repo max size is: "+maxSize);
    }

    @Test
    public void getMinRepoSize() throws Exception {
        double minSize = repoDaoImp.getMinRepoSize();
        assertTrue(minSize>=0);
        System.out.println("repo min size is: "+minSize);
    }

    @Test
    public void getMaxRepoFork() throws Exception {
        double maxFork = repoDaoImp.getMaxRepoFork();
        assertTrue(maxFork>=0);
        System.out.println("repo max fork is: "+maxFork);
    }

    @Test
    public void getMinRepoFork() throws Exception {
        double minFork = repoDaoImp.getMinRepoFork();
        assertTrue(minFork>=0);
        System.out.println("repo min fork is: "+minFork);
    }

    @Test
    public void getMaxRepoPopular() throws Exception {
        double maxPopular = repoDaoImp.getMaxRepoPopular();
        assertTrue(maxPopular>=0);
        System.out.println("repo max popular is: "+maxPopular);
    }

    @Test
    public void getMinRepoPopular() throws Exception {
        double minPopular = repoDaoImp.getMinRepoPopular();
        assertTrue(minPopular>=0);
        System.out.println("repo min popular is: "+minPopular);
    }

    @Test
    public void getMaxRepoComplex() throws Exception {
        double maxComplex = repoDaoImp.getMaxRepoComplex();
        assertTrue(maxComplex>=0);
        System.out.println("repo max complex is: "+maxComplex);
    }

    @Test
    public void getMinRepoComplex() throws Exception {
        double minComplex = repoDaoImp.getMinRepoComplex();
        assertTrue(minComplex>=0);
        System.out.println("repo min complex is: "+minComplex);
    }

    @Test
    public void getMaxActive() throws Exception {
        double maxActive = repoDaoImp.getMaxActive();
        assertTrue(maxActive>=0);
        System.out.println("repo max active is: "+maxActive);
    }

    @Test
    public void getMinActive() throws Exception {
        double minActive = repoDaoImp.getMinActive();
        assertTrue(minActive>=0);
        System.out.println("repo min active is: "+minActive);

    }

}