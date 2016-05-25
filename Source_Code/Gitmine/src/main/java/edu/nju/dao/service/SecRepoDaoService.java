package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.SecRepoEntity;

import java.util.List;

/**
 * Created by Harry on 2016/5/24.
 * the second eition of repo dao service
 */
public interface SecRepoDaoService {

    public List<SecRepoEntity> getSearchResult(String keyword, int offset, int maxNum, SortType type,
                                               boolean isDesc, String filterType, String language,
                                               String createYear);

    public List<SecRepoEntity> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum);

    public SecRepoEntity getRepoBasicInfo(String owner, String repoName);

    public List<String> getRepoContributor(String owner, String repoName);

    public List<String> getRepoSubscriber(String owner, String repoName);

    /**
     * get the max size of all repos
     * @return
     */
    public double getMaxRepoSize();

    public double getMinRepoSize();

    /**
     * get the max fork count of all repos
     * @return
     */
    public double getMaxRepoFork();

    public double getMinRepoFork();

    /**
     * get the max count of all repos
     * @return
     */
    public double getMaxRepoStar();

    public double getMinRepoStar();

    /**
     * get the max count of contributors of all repos
     * @return
     */
    public double getMaxRepoContriCount();

    public double getMinRepoContriCount();

    /**
     * get the max count of commits of all repos
     * @return
     */
    public double getMaxCommitCount();

    public double getMinCommitCount();

    /**
     * get the repo complex value
     * @param ownername
     * @param reponame
     * @return
     */
    public double getRepoContriCount(String ownername, String reponame);

    /**
     * get the repo avtive value
     * @param ownername
     * @param reponame
     * @return
     */
    public double getRepoCommitCount(String ownername, String reponame);

    /**
     * get the count of repos created in each the year
     * @return
     */
    public long getStatsCreateTime();

    /**
     * get the count of repos has fork count between min and max
     * @param min
     * @param max
     * @return
     */
    public long getStatsFork(int min, int max);

    /**
     * get the count of repos has star between min and max
     * @param min
     * @param max
     * @return
     */
    public long getStatsStar(int min, int max);

    /**
     * statistic for repo language
     * @param maxResults
     * @return
     */
    public List getStatsLanguage(int maxResults);

    /**
     * statistic for repo size
     * @param min
     * @param max
     * @return
     */
    public long getStatsSize(int min, int max);

}
