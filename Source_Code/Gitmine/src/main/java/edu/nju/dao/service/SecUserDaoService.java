package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;

import java.util.List;

/**
 * Created by Harry on 2016/5/25.
 */
public interface SecUserDaoService {

    /**
     * get user info
     * @param login
     * @return
     */
    public SecUserEntity getUserBasicInfo(String login);

    /**
     * get the avatar of the specific user
     * @param login
     * @return
     */
    public String getUserAvatar(String login);

    /**
     * search users
     * @param keyword
     * @param sortType
     * @param isDesc
     * @param offset
     * @param maxNum
     * @return
     */
    public List<SecUserEntity> getSearchResult(String keyword, SortType sortType, boolean isDesc, int offset, int maxNum);

    public List<SecUserEntity> getUsers(SortType sortType, boolean isDesc, int offset, int maxNum);

    /**
     * get Total count of user.
     * @return number of user
     */
    public long getTotalCount();

    public long getSearchCount(String keyword);

    /**
     * get the repos owned by the user
     * @param login
     * @return
     */
    public List<SecRepoEntity> getUserOwnRepos(String login, SortType sortType, int offset, int maxNum);

    /**
     *
     * get related repositories
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<SecRepoEntity> getUserSubscribeRepos(String login);

    /**
     * get related contributor
     * @param login
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<SecRepoEntity> getUserContributerRepos(String login);

    /**
     * get the count of user or organization
     * @return
     */
    public List<Object[]> getStatsUserType();

    /**
     * get the count of users who has repos between min and max
     * @param min
     * @param max
     * @return
     */
    public long getStatsUserOwnRepo(int min, int max);

    /**
     * get the count of users who has gists between min and max
     * @param min
     * @param max
     * @return
     */
    public long getStatsUserGist(int min, int max);

    /**
     * get the count if users who has repos between min and max
     * @param min
     * @param max
     * @return
     */
    public long getStatsUserFollower(int min, int max);

    /**
     * get the count of users create int a year
     * @return
     */
    public List<Object[]> getStatsCreateTime();

    /**
     * get the statistic for user email
     * @param maxResults
     * @return
     */
    public List<Object[]> getStatsEmail(int maxResults);

    /**
     * get the statistic for users' company
     * @param maxResults
     * @return
     */
    public List<Object[]> getStatsCompany(int maxResults);

    /**
     * get the max count of repos own by each user
     * @return
     */
    public double getMaxRepos();

    /**
     * get the max count of gist each user has
     * @return
     */
    public double getMaxGists();

    /**
     * get the max count of follower of each user
     * @return
     */
    public double getMaxFollower();

    /**
     * used to replace the function <tt>getMaxUserActive</tt>
     * @return
     */
    public double getMaxUserContribute();

    /**
     * get the max of user following
     * @return
     */
    public double getMaxUserFollowing();

    /**
     * get the active value of a user
     * @param login
     * @return
     */
    public double getUserContribute(String login);

}
