package edu.nju.dao.impl;

import edu.nju.dao.service.RepoPopuService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * stat relationship between language and popularity
 * @author cuihao
 */
@Repository
public class RepoPopuDaoImp implements RepoPopuService {
    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Map<String,List> statPopuLanguage() {
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("select language from SecRepoEntity where language!='' group by language order by count(*) desc ");
        query1.setMaxResults(10);
        List<String> languages = query1.list();
        Query query = session.createQuery("select starCount from SecRepoEntity where language=? order by starCount desc");
        query.setFirstResult(20);
        query.setMaxResults(100);
        Map<String,List> lists = new HashMap<>(languages.size());
        for (int i = 0; i < languages.size(); i++) {
            query.setString(0,languages.get(i));
            lists.put("lan"+i,query.list());
        }
        lists.put("language",languages);
        return lists;
    }

    @Override
    public Map<String, List> statStarRelation(int max) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new list(starCount,forkCount) from SecRepoEntity where starCount<1000 and forkCount<200");
        if (max > 0) {
            query.setFirstResult((int)(Math.random()*10000));
            query.setMaxResults(max);
        }
        Map<String, List> result = new HashMap<>();
        result.put("fork",query.list());
        query = session.createQuery("select new list(starCount,watchersCount) from SecRepoEntity where starCount<1000");
        if (max > 0) {
            query.setFirstResult((int)(Math.random()*10000));
            query.setMaxResults(max);
        }
        result.put("watcher",query.list());
        return result;
    }
}
