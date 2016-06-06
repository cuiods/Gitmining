package edu.nju.model.statistic;

import edu.nju.dao.service.RepoPopuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * one-way analysis of variables
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
public class OneWayANOVATest {
    @Resource
    private RepoPopuService repoPopuService;
    @Test
    public void doAnalysis() throws Exception {
        List<List<Integer>> list1 = repoPopuService.variablePerson();
        OneWayANOVA oneWayANOVA = new OneWayANOVA(list1);
        System.out.println("one:"+oneWayANOVA.doAnalysis());
    }

}