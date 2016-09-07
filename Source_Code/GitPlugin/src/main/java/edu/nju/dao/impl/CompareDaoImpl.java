package edu.nju.dao.impl;

import edu.nju.dao.CompareDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

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
    public long rangeOfFollwer(double num) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecRepoEntity where weightFollower <= :number ");
        query.setDouble("number",num);
        long result = (Long) query.list().get(0);
        session.close();
        return result;
    }

    @Override
    public double peopleFollower(String owner, String name) {
        Session session = sessionFactory.openSession();
        double result = 0;
        Query query = session.createQuery("select weightFollower from SecRepoEntity where owner=:owner and name=:name ");
        query.setString("owner",owner);
        query.setString("name",name);
        List list = query.list();
        session.close();
        if (list.size()>0){
            result = (Double) list.get(0);
        }
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
