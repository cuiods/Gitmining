package edu.nju.model.service;

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
}
