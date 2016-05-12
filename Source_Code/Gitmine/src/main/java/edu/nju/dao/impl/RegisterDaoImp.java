package edu.nju.dao.impl;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.entity.RegisterLabel;
import edu.nju.entity.TblRegister;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * login data imp
 * @author cuihao
 */
@Repository
public class RegisterDaoImp implements RegisterDaoService {
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

    /**
     * get register labels
     *
     * @param userName username of the register
     * @return registerLabel
     */
    public RegisterLabel getRegisterInterest(String userName) {
        Query query = getSession().createQuery("from RegisterLabel where register=?");
        query.setString(0,userName);
        List<RegisterLabel> registerLabels = query.list();
        if (registerLabels.size()>0) {
            return registerLabels.get(0);
        }
        return null;
    }

    /**
     * save register labels
     *
     * @param registerLabel
     * @return is succeed.
     */
    public boolean saveRegisterInterest(RegisterLabel registerLabel) {
        return !(getSession().save(registerLabel)==null);
    }
}
