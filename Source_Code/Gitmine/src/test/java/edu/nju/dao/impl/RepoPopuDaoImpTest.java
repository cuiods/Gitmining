package edu.nju.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * popularity stat test
 * @author cuihao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/META-INF/applicationContext.xml")
@Transactional
public class RepoPopuDaoImpTest {

    @Resource
    private RepoPopuDaoImp repoPopuImp;
    @Test
    public void statPopuLanguage() throws Exception {
        Map<String,List> maps = repoPopuImp.statPopuLanguage();
        List<String> languages = maps.get("language");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println(languages.get(i)+"---"+maps.get("lan"+i));
        }
    }

    @Test
    public void statStarRelation() throws Exception {
        Map<String,List> map = repoPopuImp.statStarRelation(100);
        System.out.println(map.get("fork"));
        System.out.println(map.get("watcher"));
    }

    @Test
    public void statLanguageRate() throws Exception {
        List<List> lists = repoPopuImp.statLanguageRate();
        for (List list: lists) {
            System.out.println(list.get(0)+"----"+list.get(1));
        }
    }

    @Test
    public void statLanguageYearRate() throws Exception {
        List<List> lists = repoPopuImp.statLanguageYearRate();
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("----"+lists.get(i));
        }
    }

    @Test
    public void statLanguageRateYear() throws Exception {
        List<List> lists = repoPopuImp.statLanguageRateYear();
        for (int i = 0; i < lists.size(); i++) {
            System.out.println("----"+lists.get(i));
        }
    }

    @Test
    public void statSpecialFollower() throws Exception {
        List list = repoPopuImp.statSpecialFollower();
        for (int i = 0; i < list.size(); i++) {
            Object[] objects = (Object[]) list.get(i);
            System.out.println("fork:"+objects[0] + "  number:"+objects[1]+" class: "+objects[1].getClass().getName());
        }
    }

    @Test
    public void statFields() throws Exception {
        List list = repoPopuImp.statFields();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+"---"+list.get(i));
        }
    }

    @Test
    public void statFieldBox() throws Exception {
        List<List> lists = repoPopuImp.statFieldBox();
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }

    @Test
    public void statFieldCreateTime() throws Exception {
        List<List> lists = repoPopuImp.statFieldCreateTime();
        for (int i = 0; i < lists.size(); i++) {
            System.out.println(lists.get(i));
        }
    }

    @Test
    public void statFollowerRate() throws Exception {
        List<List> lists = repoPopuImp.statFollowerRate();
        for (List list:lists) {
            System.out.println(list);
        }
    }

    @Test
    public void statFollowerSuper() throws Exception {
        List<Object[]> list = repoPopuImp.statFollowerSuper(10,100);
        for (Object[] objects: list) {
            for (int i = 0; i < objects.length; i++) {
                System.out.print(objects[i]+",");
            }
            System.out.println();
        }
    }

    @Test
    public void refactorTest() throws Exception {
        List<Object[]> list = repoPopuImp.refactorTest();
        for (Object[] objects: list) {
            for (int i = 0; i < objects.length; i++) {
                System.out.print(objects[i]+",");
            }
            System.out.println();
        }
    }

    @Test
    public void variableLanguage() throws Exception {
        List<List> lists = repoPopuImp.variableLanguage();
        for (List list:lists) {
            System.out.println(list+"-------"+list.size());
        }
    }

    @Test
    public void variableFields() throws Exception {
        List<List> lists = repoPopuImp.variableFields();
        for (List list:lists) {
            System.out.println(list+"-------"+list.size());
        }
    }

    @Test
    public void variablePerson() throws Exception {
        List<List> lists = repoPopuImp.variablePerson();
        for (List list:lists) {
            System.out.println(list.size()+"-------"+list);
        }
    }
}