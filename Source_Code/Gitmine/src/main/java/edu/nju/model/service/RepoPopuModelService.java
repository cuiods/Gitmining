package edu.nju.model.service;

import edu.nju.model.pojo.SimpleChart;

import java.util.List;
import java.util.Map;

/**
 * repository popularity model
 */
public interface RepoPopuModelService {
    Map<String, List> statLanguagePopularity();
    Map<String, List> statStarRelation();
    Map<String, List> statStarRelation(int max);
    Map<String, List> statLanguageRate();
    List<List> statLanguageYearRate();
    List<List> statLanguageRateYear();
    List statFieldNumber();
    List<List> statFieldNumberBox();
    List<List> statFieldCreateAt();
    List statSpecialFollower();
    List statsCompareFollower();
    List<List> statSpecialFollowRate();
    Map<String,List> statFollowerSuper();
    List vaLanguage();
    List<List<Integer>> vaLanguageData();
    List vaField();
    List<List<Integer>> vaFieldData();
    List vaPerson();
    List<List<Integer>> vaPersonData();
    List<Object[]> regressionFollower();
}
