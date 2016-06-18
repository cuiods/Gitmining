package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;

import java.util.List;

/**
 * Created by Harry on 2016/5/25.
 */
public interface SecUserDaoService {

    public List<SecUserEntity> getRecommendUser(String webUsername,int offset,int maxResults);

    public List<Object []> getUserLanguage(String login);

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
     * get all the location of all user
     * @return
     */
    public List<String> getAllUserLocation();

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
     * get subscribed repositories, order by star
     * @param maxResults the count of repos will returned
     * @return list of repo names
     */
    public List<SecRepoEntity> getUserSubscribeRepos(String login,int maxResults);

    /**
     * get contributed repositories, order by star
     * @param login
     * @return list of repo names
     */
    public List<SecRepoEntity> getUserContributeRepos(String login,int maxResults);

    /**
     * get the users that have contributed to the same repo with the given user
     * @param username
     * @return
     */
    public List<Object[]> getRelatedUser(String username, int limitResults);

    /**
     * get the count of user or organization
     * @return
     */
    public List<Object[]> getStatsUserType();

    /**
     * get the count of users who has repos between min and max
     * @return
     */
    public long[] getStatsUserOwnRepo();

    /**
     * get the count of users who has gists between min and max
     * @return
     */
    public long[] getStatsUserGist();

    /**
     * get the count if users who has repos between min and max
     * @return
     */
    public long[] getStatsUserFollower();

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
