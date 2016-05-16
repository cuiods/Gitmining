package edu.nju.model.service;

import edu.nju.model.pojo.SimpleChart;

/**
 * Created by Harry on 2016/5/16.
 * statistics for repository
 */
public interface RepoStatsService {

    /**
     * statistic for create time of all repos
     * @return
     */
    public SimpleChart statsCreateTime();

    /**
     * statistic for fork count of all repos
     * @return
     */
    public SimpleChart statsForkCount();

    /**
     * statistic for star count of all repos
     * @return
     */
    public SimpleChart statsStarCount();

    /**
     * statistic for language of all repos
     * @return
     */
    public SimpleChart statsLanguage();

    /**
     * statistic for size of all repos
     * @return
     */
    public SimpleChart statsSize();
}
