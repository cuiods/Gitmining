package edu.nju.dao.impl;

import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public void saveEntity(Object entity){
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
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!=null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }

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
