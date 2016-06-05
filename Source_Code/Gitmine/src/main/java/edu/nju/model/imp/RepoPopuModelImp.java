package edu.nju.model.imp;

import edu.nju.dao.service.RepoPopuService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoPopuModelService;
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
}
