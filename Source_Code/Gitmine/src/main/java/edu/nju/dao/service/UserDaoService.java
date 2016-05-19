package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.entity.UserLabel;

import java.util.Calendar;
import java.util.List;

/**
 * dao of user info
 * @author cuihao
 */
public interface UserDaoService {
    /**
     * get user info
     * @param loginName
     * @return user info entity
     */
    public TblUser findUserByLoginName(String loginName);

    /**
     * get the avartar of the specific user
     * @param username
     * @return
     */
    public String getUserAvatar(String username);

    /**
     * search users by login name(keyword)
     * @param keyword
     * @return list of users
     */
    public List<TblUser> searchUserByLoginName(String keyword, SortType sortType, boolean isDesc, int offset, int maxNum);

    /**
     * get Total count of user.
     * @return number of user
     */
    public long getUserTotalCount();

    /**
     * get sorted user list.
     * @return list of sorted user.
     */
    @Deprecated
    public List<TblUser> getUsers(int offset, int maxNum);

    /**
     * get sorted user list.
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<TblUser> getUsers(SortType sortType, boolean isDesc, int offset, int maxNum);

    /**
     * get the repos owned by the user
     * @param userLoginName
     * @return
     */
    public List<TblRepo> getUserOwnRepos(String userLoginName, SortType sortType, int offset, int maxNum);

    /**
     *
     * get related repositories
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<List> getUserSubscribeRepos(String userLoginName,int offset,int maxNum);

    /**
     * get related collaborator repositories
     * @param userLoginName
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<List> getUserCollaboratorRepos(String userLoginName,int offset,int maxNum);

    /**
     * get related contributor
     * @param userLoginName
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<List> getUserContributorRepos(String userLoginName,int offset,int maxNum);

    /**
     * get user interest labels
     * @param userName
     * @return user label
     *  {@link UserLabel}
     */
    public UserLabel getUserInterest(String userName);

    /**
     * save userLabel
     * @param userLabel
     * @return
     *      is succeed
     */
    public boolean saveUserInterest(UserLabel userLabel);

    /**
     * get the count of user or organization
     * @param isOrg 0 for user, 1 for org
     * @return
     */
    public long getStatsUserType(byte isOrg);

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
     * get the count of users create between fromTime and toTime
     * @param fromTime
     * @param toTime
     * @return
     */
    public long getStatsCreateTime(Calendar fromTime, Calendar toTime);

    /**
     * get the statistic for user email
     * @param maxResults
     * @return
     */
    public List getStatsEmail(int maxResults);

    /**
     * get the statistic for users' company
     * @param maxResults
     * @return
     */
    public List getStatsCompany(int maxResults);

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
     * get the max value for user value
     * @return
     */
    public double getMaxUserValue();

    /**
     * get the active value of a user
     * @param username
     * @return
     */
    public double getUserContribute(String username);

    /**
     * get the value of a user
     * @param username
     * @return
     */
    public double getUserValue(String username);

}
