package edu.nju.dao.impl;

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
}
