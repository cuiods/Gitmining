package edu.nju.dao.impl;

import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
        return null;
    }
}
