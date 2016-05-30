package edu.nju.model.imp;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.SortType;
import edu.nju.model.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

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
        List<RepoVO> list = repoModelImpl.getPopularRepo();
        assertNotNull(list);
        assertTrue(list.size()>0);
        for (RepoVO vo:list){
            System.out.println(vo.getOwnerName()+"   "+vo.getReponame());
        }
    }

    @Test
    public void getTotalPage() throws Exception {
        int page = repoModelImpl.getTotalPage();
        assertTrue(page>10);
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
        List<RepoVO> list = repoModelImpl.getSearchResult("a","repo_star","linux","","",1,true);
        assertNotNull(list);
        assertTrue(list.size()>0);
        System.out.println(list.get(0).getReponame()+"   "+list.get(0).getDescription());
    }

    @Test
    public void getSearchPage() throws Exception {
        //int page = repoModelImpl.getSearchPage("a","linux","java")
    }

    @Test
    public void getRepoBasicInfo() throws Exception {
        RepoVO vo = repoModelImpl.getRepoBasicInfo("klen",".vim");
        assertNotNull(vo);
    }

    @Test
    public void getRepoRadarChart() throws Exception {
        RadarChart chart = repoModelImpl.getRepoRadarChart("rubinius","rubinius");
        assertNotNull(chart);
        assertTrue(chart.getValue().length == 5);
    }

    @Test
    public void getCommitByContributor() throws Exception {
        Map<String,CommitChart> map = repoModelImpl.getCommitByContributor("defunkt","starling");
        assertNotNull(map.get("all"));
        System.out.println("all contributor's commit : "+map.get("all").getValue().length);
    }

    @Test
    public void getCodeFrequency() throws Exception {
        CodeFrequency chart  = repoModelImpl.getCodeFrequency("jquery","jquery");
        assertNotNull(chart);
        System.out.println("code frequency add for the first: "+chart.getField()[0]
                +chart.getAdd()[0]);
    }

    @Test
    public void getPunchCard() throws Exception {
        JsonNode node = repoModelImpl.getPunchCard("jquery","jquery");
        assertNotNull(node);
    }

}