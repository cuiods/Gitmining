package edu.nju.dao.impl;

import edu.nju.entity.UserCountryEntity;
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
    public void saveCountry() throws Exception {
//        List<UserCountryEntity> list = new ArrayList<>();
//        UserCountryEntity entity = new UserCountryEntity();
//        entity.setCountry("USA");
//        entity.setNumber(100);
//        list.add(entity);
//        UserCountryEntity entity1 = new UserCountryEntity();
//        entity1.setCountry("China");
//        entity1.setNumber(999);
//        list.add(entity1);
//        locationDao.saveCountry(list);
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