package edu.nju.model.imp;

import edu.nju.dao.service.RepoPopuService;
import edu.nju.model.service.RepoPopuModelService;
import edu.nju.model.statistic.RegressionLine;
import edu.nju.model.statistic.StarRegressionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
