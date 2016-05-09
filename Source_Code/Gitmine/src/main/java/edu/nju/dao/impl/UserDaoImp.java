package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * user data impl
 * @author cuihao
 */
@Repository
public class UserDaoImp implements UserDaoService {
    @Resource
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    /**
     * get user info
     *
     * @param loginName
     * @return user info entity
     */
    public TblUser findUserByLoginName(String loginName) {
        TblUser user = null;
        Query query = getSession().createQuery("from TblUser as u where u.loginName=?");
        query.setString(0, loginName);
        List<TblUser> users = query.list();
        if (users.size() > 0) {
            return users.get(0);
        }
        return user;
    }

    /**
     * search users by login name(keyword)
     *
     * @param keyword
     * @return list of users
     */
    public List<TblUser> searchUserByLoginName(String keyword) {
        return null;
    }

    /**
     * get Total count of user.
     *
     * @return number of user
     */
    public int getUserTotalCount() {
        return 0;
    }

    /**
     * get sorted user list.
     *
     * @param type
     * @return list of sorted user.
     */
    public List<TblUser> getUsers(SortType type) {
        return null;
    }

    /**
     * get related repositorys
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<String> getUserSubscribeRepos(String userLoginName) {
        return null;
    }

    /**
     * get related collaborator repositories
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<String> getUserCollaboratorRepos(String userLoginName) {
        return null;
    }

    /**
     * get related contributor
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<String> getUserContriutorRepos(String userLoginName) {
        return null;
    }
}
