package edu.nju.dao.impl;

import edu.nju.dao.service.RepoPopuService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
        session.close();
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
        session.close();
        return result;
    }

    /**
     * stat rate of each language of repositories whose star number > 500
     *
     * @return <li>lan: list of languages</li>
     * <li>rate: rate of each language</li>
     */
    @Override
    public List<List> statLanguageRate() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new list(language,count(*)) from SecRepoEntity where starCount>300 and language!='' group by language order by count(*) desc ");
        query.setMaxResults(12);
        List<List> lists = query.list();
        session.close();
        return lists;
    }

    /**
     * stat rate of each language of repositories created each year
     *
     * @return
     */
    @Override
    public List<List> statLanguageYearRate() {
        List<List> data = new ArrayList<>(11);
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("select language from SecRepoEntity where language!='' group by language order by count(*) desc ");
        query1.setMaxResults(10);
        List<String> languages = query1.list();
        data.add(languages);
        Query query2 = session.createSQLQuery("select count(*) from sec_repo as repo where repo.language=:lan and YEAR(repo.create_at) > 2007 GROUP BY YEAR(repo.create_at)");
        for (int i = 0; i < languages.size(); i++) {
            query2.setString("lan",languages.get(i));
            data.add(query2.list());
        }
        session.close();
        return data;
    }

    /**
     * stat rate of each language of repositories created each year
     *
     * @return
     */
    @Override
    public List<List> statLanguageRateYear() {
        List<List> data = new ArrayList<>(11);
        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("select language from SecRepoEntity where language!='' group by language order by count(*) desc ");
        query1.setMaxResults(10);
        List<String> languages = query1.list();
        data.add(languages);
        Query query2 = session.createQuery("select count(*) from SecRepoEntity as repo where repo.language=:lan and date_format(createAt,'%Y')=:time");
        for (int j = 2008; j <= 2016; j++) {
            List thisYear = new ArrayList<>();
            query2.setString("time", j+"");
            for (int i = 0; i < languages.size(); i++) {
                query2.setString("lan",languages.get(i));
                thisYear.add(query2.list().get(0));
            }
            data.add(thisYear);
        }
        session.close();
        return data;
    }

    /**
     * stat distribution of follows of popular repositories
     *
     * @return
     */
    @Override
    public List statSpecialFollower(int min, int max) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT FLOOR(U.followers/10),COUNT(*) FROM sec_repo AS Repo " +
                "LEFT JOIN sec_contributor AS Contri ON (Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name)" +
                "LEFT JOIN sec_user AS U ON U.login = Contri.contributor " +
                "WHERE Repo.star_count > 1000 GROUP BY FLOOR(U.followers/10) ");
        List list =  query.list();
        session.close();
        return list;
    }

    /**
     * stat repository distribution of different fields
     *
     * @return list of repository number of different fields
     */
    @Override
    public List statFields() {
        Session session = sessionFactory.openSession();
        List longs = new ArrayList<>();
        //regexp
        SQLQuery query = session.createSQLQuery("select count(*) from sec_repo where description REGEXP :keyword ");
        query.setString("keyword", "(^| +)node.js($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)javascript($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)library($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)ruby($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)web($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)api($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)vim($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)plugin($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)rust($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)app($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)server($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        query.setString("keyword","(^| +)json($| +|[^a-zA-Z])");
        longs.add(query.list().get(0));
        session.close();
        return longs;
    }

    @Override
    public List<List> statFieldBox() {
        Session session = sessionFactory.openSession();
        List<List> longs = new ArrayList<>();
        //regexp
        SQLQuery query = session.createSQLQuery("select star_count from sec_repo where description REGEXP :keyword ORDER BY star_count DESC LIMIT 20,100 ");
        query.setString("keyword", "(^| +)node.js($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)javascript($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)library($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)ruby($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)web($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)api($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)vim($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)plugin($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)rust($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)app($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)server($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)json($| +|[^a-zA-Z])");
        longs.add(query.list());
        session.close();
        return longs;
    }

    /**
     * field create time statistic.
     *
     * @return
     */
    @Override
    public List<List> statFieldCreateTime() {
        Session session = sessionFactory.openSession();
        List<List> longs = new ArrayList<>();
        //regexp
        SQLQuery query = session.createSQLQuery("select COUNT(*) from sec_repo as Repo where description REGEXP :keyword AND YEAR(Repo.create_at)>=2008 GROUP BY YEAR(Repo.create_at) ");
        query.setString("keyword", "(^| +)node.js($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)javascript($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)library($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)ruby($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)web($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)api($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)vim($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)plugin($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)rust($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)app($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)server($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)json($| +|[^a-zA-Z])");
        longs.add(query.list());
        session.close();
        return longs;
    }
}
