package edu.nju.dao.impl;

import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecRepoLabelEntity;
import edu.nju.entity.SecUserEntity;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/22.
 * this class is responsible for adding new data to database
 */
@Repository
public class DataUpdater {

    @Resource
    private SessionFactory sessionFactory;

    public boolean saveEntity(Object entity){
        boolean result = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
//            SQLQuery sqlQuery = session.createSQLQuery("SELECT * from sec_user WHERE login = :log");
//            sqlQuery.setString("log",userEntity.getLogin());
//            List l = sqlQuery.list();
//            if (l.size() == 0){
                session.save(entity);
//            }
//            else {
//                //the user has exist
//            }

            session.flush();
            transaction.commit();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!=null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    public void deleteLastRepo(String username){
        Session session = sessionFactory.openSession();
        SQLQuery query0 = session.createSQLQuery("SELECT max(name) FROM sec_repo WHERE owner = :own ");
        query0.setString("own", username);
        List<String> list = query0.list();
        if (list.size()>0){
            String repo = list.get(0);
            SQLQuery query = session.createSQLQuery("DELETE FROM sec_repo WHERE owner = :owne AND name = :repo");
            query.setString("owne", username);
            query.setString("repo",repo);
            query.executeUpdate();
        }

        session.close();
    }

    //public void deleteRepo(String )

    public List<String> getNoRepoUsers(){
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT login FROM sec_user WHERE public_repos > 0 AND login NOT IN (SELECT DISTINCT owner FROM sec_repo) ORDER BY update_at DESC LIMIT 1");
        List<String> list = query.list();
        session.close();
        return list;
    }

    public boolean existUser(String username){
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT login FROM sec_user WHERE login = :log");
        query.setString("log",username);
        List list = query.list();
        session.close();
        return list.size() > 0;
    }

    public List<Object[]> getAllDescription(){
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT id, name, description FROM sec_repo WHERE id NOT IN (SELECT repo_id FROM sec_repo_label)");
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

//
//    public void saveRepo(SecRepoEntity repoEntity){
//        Session session =sessionFactory.openSession();
//        Transaction transaction = null;
//        try{
//            transaction = session.beginTransaction();
//            session.save(repoEntity);
//            session.flush();
//            transaction.commit();
//        } catch (Exception e){
//            e.printStackTrace();
//            if (transaction!=null){
//                transaction.rollback();
//            }
//        } finally {
//            session.close();
//        }

//    }


}
