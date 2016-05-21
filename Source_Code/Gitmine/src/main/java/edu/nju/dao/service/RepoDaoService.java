package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.RepoLabel;
import edu.nju.entity.TblRepo;

import java.util.Calendar;
import java.util.List;

/**
 * data access object service of repository
 * @author cuihao
 */
public interface RepoDaoService {
    /**
     * search repos by keyword.
     * @param keyword
     * @return list of repositorys
     */
    @Deprecated
    public List<TblRepo> getSearchResult(String keyword);

    public List<TblRepo> getSearchResult(String keyword,int offset,int maxNum, SortType type, boolean isDesc, String filterType,
                                         String language, Calendar createYear);

    /**
     * get total count of repository
     * @return number of repository
     */
    public long getTotalCount();

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     * @param sortType which type of list to get
     * @return the reference to the list
     * @deprecated
     */
    @Deprecated
    public List<TblRepo> getRepos(SortType sortType);

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     * @return the reference to the list
     */
    @Deprecated
    public List<TblRepo> getRepos(int offset, int maxNum);

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<TblRepo> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum);


    /**
     * Get <b>detailed</b> info of a repository.
     * @param owner
     * 			name of owner of the repository
     * @param repoName
     * 			name of the repository
     * @return {@link TblRepo}:
     * 			detailed info of a repository
     */
    public TblRepo getRepoBasicInfo(String owner, String repoName);

    /**
     * Get brief info of contributors.
     * @param owner
     * 			name of the owner of the repository
     * @param repoName
     * 			name of the repository
     * @return list of {@link String}
     * 			list of name of contributors.
     * 			The return value will be null if the is no such repository.
     */
    public List<String> getRepoContributor(String owner, String repoName);

    /**
     * Get brief info of collaborators.
     * @param owner
     * 			name of the owner of the repository
     * @param repoName
     * 			name of the repository
     * @return
     * 			list of brief info of collaborators.
     */
    public List<String> getRepoCollaborator(String owner, String repoName);

    /**
     * get all subscribers' names of the given repo
     * @param owner
     * @param repoName
     * @return
     */
    public List<String> getRepoSubscriber(String owner, String repoName);

    /**
     * get repository label
     * @param repoOwner
     * @param repoName
     * @return RepoLabel
     */
    public RepoLabel getRepoInterest(String repoOwner, String repoName);

    /**
     * save repository interest label.
     * @param repoLabel
     * @return
     *      is succeed.
     */
    public boolean saveRepoInterest(RepoLabel repoLabel);

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
     * get the max popular value of all repos, where "popular" means <tt>numStar</tt> + <tt>numSubsvriber</tt>.
     * @return
     */
    public double getMaxRepoPopular();

    public double getMinRepoPopular();

    /**
     * get the max complex value of all repos, where "complex" means <tt>numCollaborator</tt> + <tt>numContributor</tt>.
     * @return
     */
    public double getMaxRepoComplex();

    public double getMinRepoComplex();

    /**
     * get the max active value of all repos, where "active" means <tt>numCommit</tt> + <tt>numPull</tt>.
     * @return
     */
    public double getMaxActive();

    public double getMinActive();

    /**
     * get the repo complex value
     * @param ownername
     * @param reponame
     * @return
     */
    public double getRepoComplex(String ownername, String reponame);

    /**
     * get the repo avtive value
     * @param ownername
     * @param reponame
     * @return
     */
    public double getRepoActive(String ownername, String reponame);

    /**
     * get the count of repos create between fromTime and toTime
     * @param fromTime
     * @param toTime
     * @return
     */
    public long getStatsCreateTime(Calendar fromTime, Calendar toTime);

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
