package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblUser;
import edu.nju.entity.UserLabel;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Calendar;
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
    public List<TblUser> searchUserByLoginName(String keyword, int offset, int maxNum) {
        Query query = getSession().createQuery("from TblUser as u where u.loginName like ?");
        query.setString(0, "%"+keyword+"%");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblUser> users = query.list();
        System.out.println(users);
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
     * @deprecated
     */
    public List<TblUser> getUsers(SortType type) {
        Session session = getSession();
        Query query = null;
        switch (type) {
            case User_Follored:query = session.createQuery("from TblUser order by follower desc ");break;
            case User_Folloring:query = session.createQuery("from TblUser order by following desc");break;
            case User_Repos:query = session.createQuery("from TblUser order by publicRepo desc");break;
            default:query = session.createQuery("from TblUser order by loginName desc");break;
        }
        return query.list();
    }

    /**
     * get sorted user list.
     *
     * @param offset
     * @param maxNum
     * @return list of sorted user.
     */
    @Deprecated
    public List<TblUser> getUsers(int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblUser");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblUser> list = query.list();
        session.close();
        return list;
    }

    /**
     * get sorted user list.
     *
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<TblUser> getUsers(SortType sortType, boolean isDesc,  int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = null;
        String order = isDesc?"desc":"asc";
        switch (sortType) {
            case User_Follored:query = session.createQuery("from TblUser order by follower "+order);break;
            case User_Folloring:query = session.createQuery("from TblUser order by following "+order);break;
            case User_Repos:query = session.createQuery("from TblUser order by publicRepo "+order);break;
            default:query = session.createQuery("from TblUser order by loginName "+order);break;
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<TblUser> list = query.list();
        session.close();
        return list;
    }


    /**
     * get related repositorys
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserSubscribeRepos(String userLoginName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new list(repoOwner,repo) from TblSubscriber where subscriber=?");
        query.setString(0,userLoginName);
        List<List> list = query.list();
        session.close();
        return list;
    }

    /**
     * get related collaborator repositories
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserCollaboratorRepos(String userLoginName) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select new list(repoOwner,repo) from TblCollabrator where collabrator=?");
        query.setString(0,userLoginName);
        List<List> list = query.list();
        session.close();
        return list;
    }

    /**
     * get related contributor
     *
     * @param userLoginName
     * @return list of repo names
     */
    public List<List> getUserContriutorRepos(String userLoginName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new list(ownerName,repo) from TblContributor where contributor=?");
        query.setString(0,userLoginName);
        List<List> list = query.list();
        session.close();
        return list;
    }

    /**
     * get user interest labels
     *
     * @param userName
     * @return user label
     * {@link UserLabel}
     */
    public UserLabel getUserInterest(String userName) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("from UserLabel where userLogin=?");
        query.setString(0,userName);
        List<UserLabel> userLabels = query.list();
        session.close();
        if(userLabels.size()>0) {
            return userLabels.get(0);
        }
        return null;
    }

    /**
     * save userLabel
     *
     * @param userLabel
     * @return is succeed
     */
    public boolean saveUserInterest(UserLabel userLabel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userLabel);
        session.flush();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public long getStatsUserType(byte isOrg) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where type = ?");
        query.setByte(0, isOrg);
        List list = query.list();
        session.close();
        return ((BigInteger)list.get(0)).longValue();
    }

    @Override
    public long getStatsUserOwnRepo(int min, int max) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where publicRepo >= " +
                "? and publicRepo <= ?");
        query.setInteger(0, min);
        query.setInteger(1, max);
        List list = query.list();
        session.close();
        return ((BigInteger)list.get(0)).longValue();
    }

    @Override
    public long getStatsUserGist(int min, int max) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where publicGist >= " +
                "? and publicGist <= ?");
        query.setInteger(0, min);
        query.setInteger(1, max);
        List list = query.list();
        session.close();
        return ((BigInteger)list.get(0)).longValue();
    }

    @Override
    public long getStatsUserFollower(int min, int max) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where follower >= " +
                "? and follower <= ?");
        query.setInteger(0, min);
        query.setInteger(1, max);
        List list = query.list();
        session.close();
        return ((BigInteger)list.get(0)).longValue();
    }

    @Override
    public long getStatsCreateTime(Calendar fromTime, Calendar toTime) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblUser where createAt " +
                ">= :fT and createAt <= :tT");
        query.setTimestamp("fT", fromTime.getTime());
        query.setTimestamp("tT", toTime.getTime());
        List list = query.list();
        long result = ((BigInteger)list.get(0)).longValue();
        session.close();
        return result;
    }

    @Override
    public List getStatsEmail(int maxResults) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT email_suffix, COUNT (*) AS num FROM tbl_user WHERE " +
                "email_suffix <> '' GROUP BY email_suffix ORDER BY num DESC ");
        query.setMaxResults(maxResults);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List getStatsCompany(int maxResults) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select company, count (*) as num from TblUser " +
                "where company <> '' group by company order by num desc ");
        List list = query.list();
        session.close();
        return list;
    }
}
