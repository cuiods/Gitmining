package edu.nju.dao.impl;

import edu.nju.dao.InfoDao;
import edu.nju.entity.CommentsEntity;
import edu.nju.entity.NewsEntity;
import edu.nju.entity.NewsOsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * info dao impl
 * @author cuihao
 */
@Repository
public class InfoDaoImpl implements InfoDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<CommentsEntity> getCommentsByName(String owner, String name, int size, int page) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select id from SecRepoEntity where owner=:owner and name=:name ");
        query.setString("owner",owner);
        query.setString("name",name);
        long id = (Long) query.list().get(0);
        Query query1 = session.createQuery("from CommentsEntity where repoId=:id and comment <> '' ");
        query1.setParameter("id",id);
        query1.setMaxResults(size);
        query1.setFirstResult((page-1)*size);
        List<CommentsEntity> entities = query1.list();
        if (entities==null) {
            entities = new ArrayList<CommentsEntity>();
        }
        session.close();
        return entities;
    }

    @Override
    public List<NewsOsEntity> getNewsByName(String owner, String name, int size, int page) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from NewsOsEntity where repoOwner = :owner and repoName = :name order by time desc ");
        query.setString("owner",owner);
        query.setString("name",name);
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);
        List<NewsOsEntity> entities = query.list();
        if (entities==null) {
            entities = new ArrayList<NewsOsEntity>();
        }
        session.close();
        return entities;
    }

    @Override
    public long getLastNewsTime(String owner, String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(time) from NewsOsEntity where repoOwner = :owner and repoName = :name ");
        query.setString("owner", owner);
        query.setString("name", name);
        List<Timestamp> times = query.list();
        session.close();
        if (times==null || times.size()<=0) {
            return 0;   //means 1970.01.01
        }
        else {
            return times.get(0).getTime();
        }
    }
}
