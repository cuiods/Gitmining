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
        TblUser user = null;
        Query query = getSession().createQuery("from TblUser as u where u.loginName like %?%");
        query.setString(0, keyword);
        List<TblUser> users = query.list();
        return users;
    }

    /**
     * get Total count of user.
     *
     * @return number of user
     */
    public long getUserTotalCount() {
        Query query = getSession().createQuery("select count(*) from TblUser");
        return (Long) query.list().get(0);
    }

    /**
     * get sorted user list.
     *
     * @param type
     * @return list of sorted user.
     */
    public List<TblUser> getUsers(SortType type) {
        String[] sort = {"follower","following","publicRepo","loginName"};
        Query query = getSession().createQuery("from TblUser order by ?");
        query.setString(0,sort[type.ordinal()]);
        return query.list();
    }

    /**
     * get related repositorys
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserSubscribeRepos(String userLoginName) {
        Query query = getSession().createQuery("select new list(repoOwner,repo) from TblSubscriber where subscriber=?");
        query.setString(0,userLoginName);
        return query.list();
    }

    /**
     * get related collaborator repositories
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserCollaboratorRepos(String userLoginName) {
        Query query = getSession().createQuery("select new list(repoOwner,repo) from TblCollabrator where collabrator=?");
        query.setString(0,userLoginName);
        return query.list();
    }

    /**
     * get related contributor
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserContriutorRepos(String userLoginName) {
        Query query = getSession().createQuery("select new list(ownerName,repo) from TblContributor where contributor=?");
        query.setString(0,userLoginName);
        return query.list();
    }
}
