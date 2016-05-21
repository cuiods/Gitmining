package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.entity.TblRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Calendar;
import java.util.List;

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(2008,1,1);
        List<TblRepo> repoList = repoDaoImp.getSearchResult("rai",0,20, SortType.Repo_Name,true,"tool","Ruby",calendar);
        assertTrue(repoList.size()>0);
        TblRepo repo = repoList.get(0);
        System.out.println("name-->"+repo.getName()+"  owner-->"+repo.getOwnerName());
    }

    @Test
    public void getSearchResult1() throws Exception {
        List<TblRepo> list = repoDaoImp.getSearchResult("rai");
        assertTrue(list.size()>0);
        TblRepo repo = list.get(0);
        System.out.println("name-->"+repo.getName()+"  owner-->"+repo.getOwnerName());
    }

    @Test
    public void getTotalCount() throws Exception {
        long num = repoDaoImp.getTotalCount();
        assertTrue(num>3000);
    }

    @Test
    public void getRepos() throws Exception {
        List<TblRepo> list = repoDaoImp.getRepos(SortType.Repo_Star);
        assertTrue(list.size()>2000);
    }

    @Test
    public void getRepos1() throws Exception {
        List<TblRepo> repos = repoDaoImp.getRepos(0,20);
        assertTrue(repos.size()==20);
    }

    @Test
    public void getRepos2() throws Exception {
        List<TblRepo> repos = repoDaoImp.getRepos(SortType.Repo_Star,true,0,20);
        Assert.assertTrue(repos.get(0).getNumStar()>36000);
    }

    @Test
    public void getRepoBasicInfo() throws Exception {
        TblRepo repo = repoDaoImp.getRepoBasicInfo("klen",".vim");
        assertNotNull(repo);
        System.out.println(repo.getName()+repo.getCreateAt());
    }

    @Test
    public void getRepoContributor() throws Exception {
        List<String> contributors = repoDaoImp.getRepoContributor("jashkenas","docco");
        Assert.assertTrue(contributors.size()>0);
        System.out.println(contributors.get(0));
    }

    @Test
    public void getRepoCollaborator() throws Exception {
        List<String> list = repoDaoImp.getRepoCollaborator("jquery","jquery");
        Assert.assertTrue(list.size()>0);
        System.out.println("first colla: "+list.get(0));
        System.out.println("total colla: "+list.size());

        List<String> noresult = repoDaoImp.getRepoCollaborator("Harry1001", "hhahah");
        assertTrue(noresult.size() == 0);
    }

    @Test
    public void getRepoSubscriber() throws Exception {
        List<String> list = repoDaoImp.getRepoSubscriber("jquery","jquery");
        Assert.assertTrue(list.size()>0);
        System.out.println("first sub: "+list.get(0));
        System.out.println("total sub: "+list.size());

        List<String> noresult = repoDaoImp.getRepoSubscriber("Harry1001", "hhahah");
        assertTrue(noresult.size() == 0);
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

    @Test
    public void getRepoComplex() throws Exception {
        double complex = repoDaoImp.getRepoComplex("rubinius", "rubinius");
        assertTrue(complex>0);
        System.out.println("rubinius complex: "+complex);

        double hh = repoDaoImp.getRepoComplex("hhh", "hhh");
        System.out.println("hhh complex is : "+hh);
        assertTrue(hh>=0);
    }

    @Test
    public void getRepoActive() throws Exception {
        double avtive = repoDaoImp.getRepoActive("jquery","jquery");
        System.out.print(avtive);
        assertTrue(avtive>0);
    }

    @Test
    public void getStatsCreateTime() throws Exception {
        Calendar cFrom = Calendar.getInstance();
        Calendar cTo = Calendar.getInstance();
        cFrom.set(2010,Calendar.JANUARY, 1, 0, 0, 0);
        cTo.set(2010,Calendar.DECEMBER, 31, 23, 59, 59);
        long people  = repoDaoImp.getStatsCreateTime(cFrom, cTo);
        assertTrue(people>0);
        System.out.println("2010 create repo count: "+people);
    }

    @Test
    public void getStatsFork() throws Exception {
        long forkCount = repoDaoImp.getStatsFork(10, 20);
        assertTrue(forkCount>=0);
        System.out.println("fork between 10 and 20 is: "+forkCount);
    }

    @Test
    public void getStatsStar() throws Exception {
        long starCount = repoDaoImp.getStatsStar(10,20);
        assertTrue(starCount>=0);
        System.out.println("star between 10 and 20 is: "+starCount);
    }

    @Test
    public void getStatsLanguage() throws Exception {
        List list = repoDaoImp.getStatsLanguage(10);
        assertNotNull(list);
        assertTrue(list.size()>0);
        System.out.println("item's class is: "+list.get(0).getClass());
        Object[] item = (Object[]) list.get(0);
        System.out.println("item class: 1-->" + item[0].getClass() + "  2-->"+item[1].getClass());
        System.out.println("in the item: 1-->" + item[0] + "  2-->"+item[1]);
    }

    @Test
    public void getStatsSize() throws Exception {
        long count = repoDaoImp.getStatsSize(50, 100);
        assertTrue(count>0);
        System.out.println("repos size between 50 and 100 is: " + count);
    }
/*
    @Test
    public void updateCommit() throws Exception{
        try {
            repoDaoImp.updateCommit();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("update commit fail");
            throw e;
        }
    }
*/
}