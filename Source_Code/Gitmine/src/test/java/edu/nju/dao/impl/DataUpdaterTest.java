package edu.nju.dao.impl;

import edu.nju.entity.SecUserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/5/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class DataUpdaterTest {

    @Resource
    private DataUpdater updater;

    @Test
    public void saveEntity() throws Exception {
        SecUserEntity userEntity = new SecUserEntity();
        userEntity.setLogin("dbctest");
        userEntity.setId(Long.MAX_VALUE);
        userEntity.setEmail("dbc1994@126.com");
        userEntity.setType("User");
        userEntity.setCreateAt(new Timestamp(1293843661));
        updater.saveEntity(userEntity);
    }

}