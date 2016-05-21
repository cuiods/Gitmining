package edu.nju.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RepoLabelUpdaterTest {

    @Resource
    private RepoLabelUpdater updater;

    @Test
    public void saveOrUpdateLabel() throws Exception {
        updater.saveOrUpdateLabel("aasm","aasm","web and interface and LINUX hhhh");
    }

}