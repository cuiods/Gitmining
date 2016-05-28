package edu.nju.dao.impl;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.entity.RegisterLabel;
import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.entity.TblRegister;
import org.hibernate.*;
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

    /**
     * used in register
     *
     * @param name
     * @return whether exist the user
     */
    public boolean existName(String name) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT * FROM tbl_register WHERE login_name = :login");
        query.setString("login",name);
        List list = query.list();
        session.close();
        if (list.size()>0) return true;
        return false;
    }

    @Override
    public boolean existUser(String username, String email) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT * FROM tbl_register WHERE login_name = :login OR email = :email");
        query.setString("login", username);
        query.setString("email", email);
        List list = query.list();
        session.close();
        if (list.size() > 0) return true;
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblRegister where loginName=? and password=?");
        query.setString(0,userName);
        query.setString(1,password);
        List list = query.list();
        session.close();
        if (list.size()>0) return true;
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
        Session session = sessionFactory.openSession();
        TblRegister register = new TblRegister();
        register.setLoginName(userName);
        register.setPassword(passWord);
        register.setEmail(email);
        Transaction transaction = null;
        boolean result = false;
        try{
            transaction = session.beginTransaction();
            session.save(register);
            session.flush();
            transaction.commit();
            result = true;
        } catch (Exception  e){
            if (transaction !=null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * get register labels
     *
     * @param userName username of the register
     * @return registerLabel
     */
    public SecRegisterLabelEntity getRegisterInterest(String userName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SecRegisterLabelEntity where registerLogin=?");
        query.setString(0,userName);
        List<SecRegisterLabelEntity> list = query.list();
        session.close();
        if (list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * save register labels
     *
     * @param registerLabel
     * @return is succeed.
     */
    public boolean saveOrUpdateRegisterInterest(SecRegisterLabelEntity registerLabel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean result = false;
        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(registerLabel);
            session.flush();
            transaction.commit();
            result = true;
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return result;
    }
}
