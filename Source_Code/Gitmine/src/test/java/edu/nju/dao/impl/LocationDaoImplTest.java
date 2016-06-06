package edu.nju.dao.impl;

import edu.nju.entity.UserCountryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Harry on 2016/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class LocationDaoImplTest {

    @Resource
    private LocationDaoImpl locationDao;

    @Test
    public void getStatCountry() throws Exception {
        List<UserCountryEntity> list = locationDao.getStatCountry();
        for (UserCountryEntity entity:list){
            System.out.println(entity.getCountry()+"--->"+entity.getNumber());
        }
    }

    @Test
    public void saveOrUpdateCountry() throws Exception {
//        UserCountryEntity entity = new UserCountryEntity();
//        entity.setCountry("USA");
//        entity.setNumber(100);
//        locationDao.saveCountry(entity);
//        entity.setCountry("China");
//        entity.setNumber(999);
//        locationDao.saveCountry(entity);
    }

    @Test
    public void deleteCountry() throws Exception {
        //locationDao.deleteCountry("China");
    }

    @Test
    public void deleteAllCountries() throws Exception {
        //locationDao.deleteAllCountries();
    }

}