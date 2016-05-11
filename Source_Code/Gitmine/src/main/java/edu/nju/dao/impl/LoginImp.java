package edu.nju.dao.impl;

import edu.nju.dao.service.LoginDaoService;
import edu.nju.entity.TblRegister;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;

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
        Query query = getSession().createQuery("from TblRegister where loginName=?");
        query.setString(0,name);
        if (query.list().size()>0) return true;
        return false;
    }

    /**
     * login
     *
     * @param userName
     * @param password
     * @return TblRegister
     */
    public boolean login(String userName, String password) {
        Query query = getSession().createQuery("from TblRegister where loginName=? and password=?");
        query.setString(0,userName);
        query.setString(1,password);
        if (query.list().size()>0) return true;
        return false;
    }

    /**
     * registe
     *
     * @param userName
     * @param passWord
     * @return isSucceed
     */
    public boolean register(String userName, String passWord, String email) {
        TblRegister register = new TblRegister();
        register.setLoginName(userName);
        register.setPassword(passWord);
        register.setEmail(email);
        Serializable test = getSession().save(register);
        return !(test==null);
    }
}
