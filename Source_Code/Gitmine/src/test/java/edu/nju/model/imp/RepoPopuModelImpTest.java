package edu.nju.model.imp;

import edu.nju.dao.service.RepoPopuService;
import edu.nju.model.service.RepoPopuModelService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * repo popular statistic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class RepoPopuModelImpTest {

    @Resource
    private RepoPopuModelService repoPopuModelService;

    @Test
    public void statStarRelation() throws Exception {
        repoPopuModelService.statStarRelation(200);
    }

    @Test
    public void statLanguageRate() throws Exception {
        Map<String,List> map = repoPopuModelService.statLanguageRate();
        Assert.assertTrue(map.get("language")!=null&&map.get("count")!=null
                &&map.get("language").size()==map.get("count").size());
    }

    @Test
    public void statLanguagePopularity() throws Exception {

    }

    @Test
    public void statSpecialFollower() throws Exception {
        System.out.println(repoPopuModelService.statSpecialFollower());
    }

    @Test
    public void statsCompareFollower() throws Exception {
        System.out.println(repoPopuModelService.statSpecialFollower());
        System.out.println(repoPopuModelService.statsCompareFollower());
    }

    @Test
    public void statFollowerSuper() throws Exception {
        Map<String,List> map = repoPopuModelService.statFollowerSuper();
        System.out.println(map.get("little"));
        System.out.println(map.get("middle"));
        System.out.println(map.get("large"));
    }

    @Test
    public void vaLanguage() throws Exception {
        System.out.println(repoPopuModelService.vaLanguage());
    }

    @Test
    public void vaField() throws Exception {
        System.out.println(repoPopuModelService.vaField());
    }

    @Test
    public void vaPerson() throws Exception {
        System.out.println(repoPopuModelService.vaPerson());
    }

}