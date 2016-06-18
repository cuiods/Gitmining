package edu.nju.dao.impl;

import edu.nju.dao.service.LongTailDaoService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * long tail theory test
 */
@Repository
public class LongTailDaoImpl implements LongTailDaoService{
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public long[] twentyEightyRate() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecUserEntity ");
        long num = ((Long) query.list().get(0)).longValue();
        query = session.createQuery("select followers from SecUserEntity order by followers desc ");
        long result1 = 0;
        long result2 = 0;
        List<Object> list = query.list();
        for (int i = 0; i < num*0.2; i++) {
            result1 += ((Integer)list.get(i)).longValue();
        }
        for (int i = (int)(num*0.2); i < num; i++) {
            result2 += ((Integer)list.get(i)).longValue();
        }
        long[] results = new long[]{result1,result2};
        session.close();
        return results;
    }

    @Override
    public long[] nintyEightRate() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from SecUserEntity ");
        long num = (long) query.list().get(0);
        query = session.createQuery("select count(*) from SecUserEntity where followers>0 ");
        long num1 = (long) query.list().get(0);
        long[] results = new long[]{num1,num-num1};
        session.close();
        return results;
    }

    @Override
    public List<Integer> followerDistribution() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select followers from SecUserEntity where followers>0 order by followers desc ");
        List<Integer> integers = query.list();
        session.close();
        return integers;
    }
}
