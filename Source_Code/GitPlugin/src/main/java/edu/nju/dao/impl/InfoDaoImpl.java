package edu.nju.dao.impl;

import edu.nju.dao.InfoDao;
import edu.nju.entity.CommentsEntity;
import edu.nju.entity.NewsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
    public long getRepoId(String owner, String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select id from SecRepoEntity where owner=:owner and name=:name ");
        long id = (Long) query.list().get(0);
        session.close();
        return id;
    }

    @Override
    public List<CommentsEntity> getCommentsByName(String owner, String name, int size, int page) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select id from SecRepoEntity where owner=:owner and name=:name ");
        query.setString("owner",owner);
        query.setString("name",name);
        long id = (Long) query.list().get(0);
        Query query1 = session.createQuery("from CommentsEntity where repoId=:id");
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
    public List<NewsEntity> getNewsByName(String owner, String name, int size, int page) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select id from SecRepoEntity where owner=:owner and name=:name ");
        query.setString("owner",owner);
        query.setString("name",name);
        long id = (Long) query.list().get(0);
        Query query1 = session.createQuery("from NewsEntity where repoId=:id");
        query1.setParameter("id",id);
        query1.setMaxResults(size);
        query1.setFirstResult((page-1)*size);
        List<NewsEntity> entities = query1.list();
        if (entities==null) {
            entities = new ArrayList<NewsEntity>();
        }
        session.close();
        return entities;
    }

}
