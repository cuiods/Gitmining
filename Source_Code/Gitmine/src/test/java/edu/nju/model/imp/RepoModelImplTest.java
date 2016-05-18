package edu.nju.model.imp;

import edu.nju.common.SortType;
import edu.nju.model.pojo.RepoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/17.
 * test for repo model
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class RepoModelImplTest {

    @Resource
    private RepoModelImpl repoModelImpl;

    @Test
    public void getRecommendRepo() throws Exception {

    }

    @Test
    public void getPopularRepo() throws Exception {

    }

    @Test
    public void getTotalPage() throws Exception {

    }

    @Test
    public void getRelatedRepo() throws Exception {

    }

    @Test
    public void getRepos() throws Exception {
        List<RepoVO> list = repoModelImpl.getRepos(SortType.Repo_Name, true, 0, 10);
        assertNotNull(list);
        assertTrue(list.size()>0);
        for (RepoVO vo: list){
            System.out.println(vo.getOwnerName()+"   "+vo.getReponame());
        }
    }

    @Test
    public void getSearchResult() throws Exception {

    }

    @Test
    public void getSearchPage() throws Exception {

    }

    @Test
    public void getRepoBasicInfo() throws Exception {

    }

    @Test
    public void getRepoRadarChart() throws Exception {

    }

    @Test
    public void getCommitByContributor() throws Exception {

    }

    @Test
    public void getCodeFrequency() throws Exception {

    }

    @Test
    public void getPunchCard() throws Exception {

    }

}