package edu.nju.dao.impl;

import edu.nju.dao.InfoDao;
import edu.nju.entity.*;
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
    public List<CommentsOsEntity> getCommentsByName(String repoName, int size, int page) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from CommentsOsEntity where pName = :name");
        query.setString("name",repoName);
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);
        List<CommentsOsEntity> entities = query.list();
        session.close();
        return entities;
    }

    @Override
    public List<NewsOsEntity> getNewsByName(String owner, String name, int size, int page) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from NewsOsEntity where repoOwner = :owner and repoName = :name order by time desc, id desc ");
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

    @Override
    public SecRepoEntity getRepoByName(String owner, String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SecRepoEntity where owner=:owner and name=:name ");
        session.close();
        return null;
    }
}
