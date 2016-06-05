package edu.nju.dao.service;

import java.util.List;
import java.util.Map;

/**
 * dao service used to stat repository popularity
 * @author cuihao
 */
public interface RepoPopuService {
    /**
     * stat popular repositories' star number group by language
     * @return
     *  <li>language list</li>
     *  <li>star list with different language</li>
     */
    Map<String,List> statPopuLanguage();

    /**
     * stat star number's relationship with fork, contributor, collaborator, commit
     * @return
     * <li>with fork</li>
     * <li>with contributor</li>
     * <li>with collaborator</li>
     * <li>with commit</li>
     */
    Map<String,List> statStarRelation(int max);

    /**
     * stat rate of each language of repositories whose star number > 500
     * @return
     *  <li>lan: list of languages</li>
     *  <li>rate: rate of each language</li>
     */
    List<List> statLanguageRate();

    /**
     * stat rate of each language of repositories created each year
     * @return
     */
    List<List> statLanguageYearRate();

    /**
     * stat rate of each language of repositories created each year
     * @return
     */
    List<List> statLanguageRateYear();

    /**
     * stat distribution of follows of popular repositories
     * @return
     */
    List statSpecialFollower();

    /**
     * stat repository distribution of different fields
     * @return
     *      list of repository number of different fields
     */
    List statFields();

    /**
     * popular repositories of different fields
     * @return
     */
    List<List> statFieldBox();

    /**
     * field create time statistic.
     * @return
     */
    List<List> statFieldCreateTime();

    /**
     * stat user follower of popular repository
     * @return
     */
    List<List> statFollowerRate();

    List<Object[]> statFollowerSuper(int min, int max);
}
