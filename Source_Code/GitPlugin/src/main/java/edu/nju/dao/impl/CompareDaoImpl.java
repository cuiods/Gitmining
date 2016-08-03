package edu.nju.dao.impl;

import edu.nju.dao.CompareDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * compare dao impl
 * @author cuihao
 */
@Repository
public class CompareDaoImpl implements CompareDao {

    @Resource
    private SessionFactory sessionFactory;

    private long repoSum = -1;

    @Override
    public long rangeOfWatch(long num) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecRepoEntity where watchersCount <= :num ");
        query.setLong("num",num);
        long result = (Long) query.list().get(0);
        session.close();
        return result;
    }

    @Override
    public long rangeOfStar(long num) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecRepoEntity as m where starCount <= :num ");
        query.setLong("num",num);
        long result = (Long) query.list().get(0);
        session.close();
        return result;
    }

    @Override
    public long rangeOfFork(long num) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecRepoEntity where forkCount <= :number ");
        query.setLong("number",num);
        long result = (Long) query.list().get(0);
        session.close();
        return result;
    }

    @Override
    public long sumRepo() {
        if (repoSum > 0) {
            return repoSum;
        }
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecRepoEntity ");
        repoSum = (Long) query.list().get(0);
        session.close();
        return repoSum;
    }
}