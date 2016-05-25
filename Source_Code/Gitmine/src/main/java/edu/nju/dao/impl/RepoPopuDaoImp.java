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
        Query query1 = session.createQuery("select language from TblRepo group by language order by count(*) desc ");
        query1.setMaxResults(16);
        List<String> languages = query1.list();
        Query query = session.createQuery("select numStar from TblRepo where language=? ");
        query.setFirstResult(0);
        query.setMaxResults(1000);
        Map<String,List> lists = new HashMap<>(languages.size());
        for (int i = 0; i < languages.size(); i++) {
            query.setString(0,languages.get(i));
            lists.put(languages.get(i),query.list());
        }
        return lists;
    }
}
