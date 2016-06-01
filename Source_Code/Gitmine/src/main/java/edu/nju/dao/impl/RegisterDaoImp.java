package edu.nju.dao.impl;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.entity.*;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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

    @Override
    public boolean starRepo(String webUsername, String ownername, String reponame) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("INSERT INTO register_star_repo (web_username, repo_name, repo_owner) VALUES (:web, :rname, :owner)");
        query.setString("web",webUsername);
        query.setString("rname",reponame);
        query.setString("owner",ownername);
        boolean result = false;
        try{
            query.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean unStarRepo(String webUsername, String ownername, String reponame) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("DELETE FROM register_star_repo WHERE web_username = :web AND repo_owner = :owner AND repo_name = :rname");
        query.setString("web",webUsername);
        query.setString("owner",ownername);
        query.setString("rname", reponame);
        boolean result = false;
        try{
            query.executeUpdate();
            result = true;
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;

    }

    @Override
    public boolean starUser(String username, String webUsername) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("INSERT INTO register_star_user (web_username, username) VALUES (:web, :uname)");
        query.setString("web",webUsername);
        query.setString("uname",username);
        boolean result = false;
        try{
            query.executeUpdate();
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean unStarUser(String useraname, String webUsername) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("DELETE FROM register_star_user WHERE web_username = :web AND username = :uname");
        query.setString("web",webUsername);
        query.setString("uname",useraname);
        boolean result = false;
        try{
            query.executeUpdate();
            result = true;
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<SecUserEntity> getStaredUsers(String webUsername, int offset, int maxResults) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("from SecUserEntity where name in (SELECT username FROM RegisterStarUserEntity WHERE webUsername = :web) ");
        query.setString("web",webUsername);
        query.setFirstResult(offset);
        query.setMaxResults(maxResults);
        List<SecUserEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getStaredUserCount(String webUsername) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM register_star_user WHERE web_username = :web");
        query.setString("web",webUsername);
        List list = query.list();
        session.close();
        return Long.valueOf(list.get(0).toString());
    }

    @Override
    public long getStaredRepoCount(String webUsername) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM register_star_repo WHERE web_username = :web");
        query.setString("web",webUsername);
        List list = query.list();
        session.close();
        return Long.valueOf(list.get(0).toString());
    }

    @Override
    public List<SecRepoEntity> getStaredRepos(String webUsername,int offset, int maxResults) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("from SecRepoEntity where (owner, name) in (SELECT repoOwner,repoName FROM RegisterStarRepoEntity WHERE webUsername = :web) ");
        query.setString("web",webUsername);
        query.setFirstResult(offset);
        query.setMaxResults(maxResults);
        List<SecRepoEntity> list = query.list();
        session.close();
        return list;
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
