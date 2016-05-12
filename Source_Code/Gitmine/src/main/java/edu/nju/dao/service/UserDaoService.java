package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.TblUser;
import edu.nju.entity.UserLabel;

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
     * search users by login name(keyword)
     * @param keyword
     * @return list of users
     */
    public List<TblUser> searchUserByLoginName(String keyword);

    /**
     * get Total count of user.
     * @return number of user
     */
    public long getUserTotalCount();

    /**
     * get sorted user list.
     * @param type
     * @return list of sorted user.
     */
    public List<TblUser> getUsers(SortType type);

    /**
     *
     * get related repositorys
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<List> getUserSubscribeRepos(String userLoginName);

    /**
     * get related collaborator repositories
     * @param userLoginName
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<List> getUserCollaboratorRepos(String userLoginName);

    /**
     * get related contributor
     * @param userLoginName
     * @return list of repo names
     *  [[ownerName, repoName],[ownerName, repoName]]
     */
    public List<List> getUserContriutorRepos(String userLoginName);

    /**
     * get user interest labels
     * @param userName
     * @return user label
     *  {@link UserLabel}
     */
    public UserLabel getUserInterest(String userName);

}
