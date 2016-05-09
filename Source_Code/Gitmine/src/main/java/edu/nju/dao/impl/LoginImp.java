package edu.nju.dao.impl;

import edu.nju.dao.service.LoginDaoService;
import edu.nju.entity.TblRegister;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * login data imp
 * @author cuihao
 */
@Repository
public class LoginImp implements LoginDaoService{
    @Resource
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    /**
     * used in register
     *
     * @param name
     * @return whether exist the user
     */
    public boolean existName(String name) {
        return false;
    }

    /**
     * login
     *
     * @param userName
     * @param password
     * @return TblRegister
     */
    public TblRegister login(String userName, String password) {
        return null;
    }

    /**
     * registe
     *
     * @param userName
     * @param passWord
     * @return isSucceed
     */
    public boolean register(String userName, String passWord) {
        return false;
    }
}
