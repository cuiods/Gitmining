package edu.nju.dao.service;

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

/**
 * repo test
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RepoTest  {
    @Resource
    RepoDaoService repoDaoService;

    @Test
    public void testSearchResult() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2008,1,1);
        List<TblRepo> repoList = repoDaoService.getSearchResult("rai",0,20, SortType.Repo_Name,true,"tool","Ruby",calendar);
        Assert.assertTrue(repoList.size()>0);
    }

    @Test
    public void testTotalCount() {
        long num = repoDaoService.getTotalCount();
        Assert.assertTrue(num>3000);
    }

    @Test
    public void testGet1() {
        List<TblRepo> repos = repoDaoService.getRepos(0,20);
        Assert.assertTrue(repos.size()==20);
    }

    @Test
    public void testRepoGet() {
        List<TblRepo> repos = repoDaoService.getRepos(SortType.Repo_Star,true,0,20);
        Assert.assertTrue(repos.get(0).getNumStar()>36000);
    }

    @Test
    public void testRepoInfo() {
        TblRepo repo = repoDaoService.getRepoBasicInfo("jquery","jquery");
        Assert.assertTrue(repo.getNumStar()==36996);
    }

    @Test
    public void testRepoContributor() {
        List<String> contributors = repoDaoService.getRepoContributor("jashkenas","docco");
        Assert.assertTrue(contributors.size()>0);
    }

    
}
