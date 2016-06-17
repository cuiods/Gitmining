package edu.nju.model.imp;

import edu.nju.dao.service.RepoPopuService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoPopuModelService;
import edu.nju.model.statistic.OneWayANOVA;
import edu.nju.model.statistic.RegressionLine;
import edu.nju.model.statistic.StarRegressionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * repository star stat
 * @author cuihao
 */
@Service
public class RepoPopuModelImp implements RepoPopuModelService {
    @Resource
    private RepoPopuService repoPopuImp;
    @Resource
    private StarRegressionService starRegressionService;
    @Resource
    private SecUserDaoService secUserDaoService;

    @Override
    public Map<String, List> statLanguagePopularity() {
        return repoPopuImp.statPopuLanguage();
    }

    @Override
    public Map<String, List> statStarRelation() {
        return statStarRelation(200);
    }

    @Override
    public Map<String, List> statStarRelation(int max) {
        Map<String, List> star = repoPopuImp.statStarRelation(max);
        List list = new ArrayList<>();
        RegressionLine line = starRegressionService.getForkRegression();
        list.add("y = "+line.getA1()+"x + "+line.getA0());
        list.add((int) line.getA0());
        list.add((int) (line.getA1()*1000+line.getA0()));
        line = starRegressionService.getWatcherRegression();
        list.add("y = "+line.getA1()+"x + "+line.getA0());
        list.add((int) line.getA0());
        list.add((int) (line.getA1()*1000+line.getA0()));
        star.put("stat",list);
        return star;
    }

    @Override
    public Map<String, List> statLanguageRate() {
        List<List> lists = repoPopuImp.statLanguageRate();
        List<String> language = new ArrayList<>(lists.size());
        List<Long> count = new ArrayList<>(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            List list = lists.get(i);
            language.add((String) list.get(0));
            count.add((Long) list.get(1));
        }
        Map<String,List> map = new HashMap<>();
        map.put("language",language);
        map.put("count",count);
        return map;
    }

    @Override
    public List<List> statLanguageYearRate() {
        return repoPopuImp.statLanguageYearRate();
    }

    @Override
    public List<List> statLanguageRateYear() {
        return repoPopuImp.statLanguageRateYear();
    }

    @Override
    public List statFieldNumber() {
        return repoPopuImp.statFields();
    }

    @Override
    public List<List> statFieldNumberBox() {
        return repoPopuImp.statFieldBox();
    }

    @Override
    public List<List> statFieldCreateAt() {
        List<List> lists = repoPopuImp.statFieldCreateTime();
        for (List list:lists) {
            while (list.size() < 9) list.add(0,0);
        }
        return lists;
    }

    @Override
    public List statSpecialFollower() {
        List list = new ArrayList<>(11);
        List data = repoPopuImp.statSpecialFollower();
        for (int i = 1; i <= 10; i++) {
            Object[] objects = (Object[]) data.get(i);
            list.add(((BigInteger)objects[1]).intValue());
        }
        int count = 0;
        for (int i = 11; i < data.size(); i++) {
            count += ((BigInteger)((Object[])data.get(i))[1]).intValue();
        }
        list.add(count);
        return list;
    }

    @Override
    public List statsCompareFollower() {
        List<Long> list = new ArrayList<>(11);
        for (int i = 0; i < 10; i++) {
            list.add(secUserDaoService.getStatsUserFollower(i*10,(i+1)*10));
        }
        long count = secUserDaoService.getStatsUserFollower(100, Integer.MAX_VALUE);
        list.add(count);
        return list;
    }

    @Override
    public List<List> statSpecialFollowRate() {
        return repoPopuImp.statFollowerRate();
    }

    @Override
    public Map<String, List> statFollowerSuper() {
        Map<String,List> map = new HashMap<>();
        List<Object[]> bigInteger1 = repoPopuImp.statFollowerSuper(0,10);
        List<Object[]> bigInteger2 = repoPopuImp.statFollowerSuper(10,100);
        List<Object[]> bigInteger3 = repoPopuImp.statFollowerSuper(100,Integer.MAX_VALUE);
        List<List> lists1 = new ArrayList<>(9);
        List<List> lists2 = new ArrayList<>(9);
        List<List> lists3 = new ArrayList<>(9);
        for (int i = 0; i < bigInteger1.size(); i++) {
            Object[] objects1 = bigInteger1.get(i);
            Object[] objects2 = bigInteger2.get(i);
            Object[] objects3 = bigInteger3.get(i);
            List l1 = new ArrayList<>(6);
            List l2 = new ArrayList<>(6);
            List l3 = new ArrayList<>(6);
            for (int j = 0; j < objects1.length; j++) {
                l1.add(((BigInteger)objects1[j]).intValue());
                l2.add(((BigInteger)objects2[j]).intValue());
                l3.add(((BigInteger)objects3[j]).intValue());
            }
            lists1.add(l1);
            lists2.add(l2);
            lists3.add(l3);
        }
        map.put("little",lists1);
        map.put("middle",lists2);
        map.put("large",lists3);
        return map;
    }

    @Override
    public List vaLanguage() {
        List list = new ArrayList<>();
        //list.add(new OneWayANOVA(repoPopuImp.variableLanguage()).doAnalysis());
        list.add(repoPopuImp.getViaLanguage());
        return list;
    }

    @Override
    public List<List<Integer>> vaLanguageData() {
        return repoPopuImp.variableLanguage();
    }

    @Override
    public List vaField() {
        List list = new ArrayList<>();
        //list.add(new OneWayANOVA(repoPopuImp.variableFields()).doAnalysis());
        list.add(repoPopuImp.getViaField());
        return list;
    }

    @Override
    public List<List<Integer>> vaFieldData() {
        return repoPopuImp.variableFields();
    }

    @Override
    public List vaPerson() {
        List list = new ArrayList<>();
        //list.add(new OneWayANOVA(repoPopuImp.variablePerson()).doAnalysis());
        list.add(repoPopuImp.getViaPerson());
        return list;
    }

    @Override
    public List<List<Integer>> vaPersonData() {
        return repoPopuImp.variablePerson();
    }

    @Override
    public List<Object[]> regressionFollower() {
        List<Object[]> list = repoPopuImp.followerRegression(1000);
        return list;
    }

    @Override
    public double popularRate(String repoOwner, String repoName) {
        double[] theta = repoPopuImp.getClassification();
        double[] X = repoPopuImp.getClassificationXs(repoOwner,repoName);
        System.out.println(-(theta[0]+theta[1]*X[0]+theta[2]*X[1]));
        double result = 1.0 / (1.0 + Math.exp(-(theta[0]+theta[1]*X[0]+theta[2]*X[1])));
        return result;
    }
}
