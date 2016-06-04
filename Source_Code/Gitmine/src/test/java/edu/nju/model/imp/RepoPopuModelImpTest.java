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


}