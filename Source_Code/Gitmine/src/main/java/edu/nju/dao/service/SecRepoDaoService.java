package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecRepoLabelEntity;

import java.util.List;

/**
 * Created by Harry on 2016/5/24.
 * the second eition of repo dao service
 */
public interface SecRepoDaoService {

    public List<SecRepoEntity> getSearchResult(String keyword, int offset, int maxNum, SortType type,
                                               boolean isDesc, String filterType, String language,
                                               String createYear);

    /**
     * this method user contextual search to match user's hobby
     * @param webUsername
     * @param keyword
     * @param offset
     * @param maxResult
     * @param filter
     * @param language
     * @param createYear
     * @return
     */
    public List<SecRepoEntity> searchWithLogin(String webUsername,String keyword,int offset,int maxResult,String filter,
                                               String language,String createYear);

    public long getSearchCount(String keyword, String filterType, String language, String createYear);

    public List<SecRepoEntity> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum);

    public long getTotalCount();

    public SecRepoEntity getRepoBasicInfo(String owner, String repoName);

    public List<String> getRepoContributor(String owner, String repoName);

    public List<String> getRepoSubscriber(String owner, String repoName);

    /**
     * get the repos similar to the given repo
     * @param ownername
     * @param reponame
     * @return
     */
    public List<SecRepoEntity> getRelatedRepo(String ownername, String reponame);

    /**
     * get the repos which match the users hobbies best
     * @param register
     * @return
     */
    public List<SecRepoEntity> getRecommendRepo(String register,int offset, int maxResults);

    public SecRepoLabelEntity getRepoLabel(String ownername, String reponame);

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
    public List<Object[]> getStatsCreateTime();

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
    public List<Object[]> getStatsLanguage(int maxResults);

    /**
     * statistic for repo size
     * @param min
     * @param max
     * @return
     */
    public long getStatsSize(int min, int max);

}
