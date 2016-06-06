package edu.nju.dao.impl;

import edu.nju.dao.service.RepoPopuService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/**
 * stat relationship between language and popularity
 * @author cuihao
 */
@SuppressWarnings("JpaQueryApiInspection")
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
    public List statSpecialFollower() {
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
        SQLQuery query = session.createSQLQuery("select count(*) from sec_repo GROUP BY (CASE WHEN description REGEXP '(^| +)node.js($| +|[^a-zA-Z])' THEN 0 " +
                "WHEN description REGEXP '(^| +)javascript($| +|[^a-zA-Z])' THEN 1 " +
                "WHEN description REGEXP '(^| +)library($| +|[^a-zA-Z])' THEN 2 " +
                "WHEN description REGEXP '(^| +)ruby($| +|[^a-zA-Z])' THEN 3 " +
                "WHEN description REGEXP '(^| +)web($| +|[^a-zA-Z])' THEN 4 " +
                "WHEN description REGEXP '(^| +)api($| +|[^a-zA-Z])' THEN 5 " +
                "WHEN description REGEXP '(^| +)vim($| +|[^a-zA-Z])' THEN 6 " +
                "WHEN description REGEXP '(^| +)plugin($| +|[^a-zA-Z])' THEN 7 " +
                "WHEN description REGEXP '(^| +)rust($| +|[^a-zA-Z])' THEN 8 " +
                "WHEN description REGEXP '(^| +)app($| +|[^a-zA-Z])' THEN 9 " +
                "WHEN description REGEXP '(^| +)server($| +|[^a-zA-Z])' THEN 10 " +
                "WHEN description REGEXP '(^| +)json($| +|[^a-zA-Z])' THEN 11 ELSE 12 END ) ");
        List<BigInteger> list = query.list();
        for (int i = 0; i < list.size()-1; i++) {
            BigInteger bigInteger = list.get(i);
            longs.add(bigInteger.intValue());
        }
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
        Query query = session.createSQLQuery("SELECT COUNT(CASE WHEN YEAR(create_at)=2008 THEN 1 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2009 THEN 2 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2010 THEN 3 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2011 THEN 4 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2012 THEN 5 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2013 THEN 6 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2014 THEN 7 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2015 THEN 8 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2016 THEN 9 END) " +
                "FROM sec_repo GROUP BY " +
                "(CASE WHEN description REGEXP '(^| +)node.js($| +|[^a-zA-Z])' THEN 10 " +
                "WHEN description REGEXP '(^| +)javascript($| +|[^a-zA-Z])' THEN 11 " +
                "WHEN description REGEXP '(^| +)library($| +|[^a-zA-Z])' THEN 12 " +
                "WHEN description REGEXP '(^| +)ruby($| +|[^a-zA-Z])' THEN 13 " +
                "WHEN description REGEXP '(^| +)web($| +|[^a-zA-Z])' THEN 14 " +
                "WHEN description REGEXP '(^| +)api($| +|[^a-zA-Z])' THEN 15 " +
                "WHEN description REGEXP '(^| +)vim($| +|[^a-zA-Z])' THEN 16 " +
                "WHEN description REGEXP '(^| +)plugin($| +|[^a-zA-Z])' THEN 17 " +
                "WHEN description REGEXP '(^| +)rust($| +|[^a-zA-Z])' THEN 18 " +
                "WHEN description REGEXP '(^| +)app($| +|[^a-zA-Z])' THEN 19 " +
                "WHEN description REGEXP '(^| +)server($| +|[^a-zA-Z])' THEN 20 " +
                "WHEN description REGEXP '(^| +)json($| +|[^a-zA-Z])' THEN 21 ELSE 22 END ) ");
        List<Object[]> list = query.list();
        for (int i = 0; i < list.size()-1; i++) {
            Object[] objects = list.get(i);
            longs.add(Arrays.asList(objects));
        }
        session.close();
        return longs;
    }

    /**
     * stat user follower of popular repository
     * @return
     */
    @Override
    public List<List> statFollowerRate() {
        Session session = sessionFactory.openSession();
        List list = new ArrayList<>();
        Query query = session.createSQLQuery("SELECT COUNT(*) FROM sec_repo AS Repo " +
                "LEFT JOIN sec_contributor AS Contri ON (Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name)" +
                "LEFT JOIN sec_user AS U ON U.login = Contri.contributor " +
                "WHERE Repo.star_count > 1000 GROUP BY U.followers > 10 ORDER BY COUNT(*) DESC LIMIT 0,2");
        list.add(query.list());
        query = session.createSQLQuery("SELECT COUNT(*) FROM sec_user AS secUser GROUP BY secUser.followers>10 ORDER BY COUNT(*) DESC LIMIT 0,2");
        list.add(query.list());
        session.close();
        return list;
    }

    @Override
    public List<Object[]> statFollowerSuper(int min, int max) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT COUNT(CASE WHEN Repo.star_count<2000 THEN 1 END)," +
                "COUNT(CASE WHEN Repo.star_count>=2000 AND Repo.star_count<3000 THEN 2 END)," +
                "COUNT(CASE WHEN Repo.star_count>=3000 AND Repo.star_count<4000 THEN 3 END)," +
                "COUNT(CASE WHEN Repo.star_count>=4000 AND Repo.star_count<5000 THEN 4 END)," +
                "COUNT(CASE WHEN Repo.star_count>=5000 AND Repo.star_count<6000 THEN 5 END)," +
                "COUNT(CASE WHEN Repo.star_count>=6000 THEN 6 END) " +
                "FROM sec_repo AS Repo " +
                "LEFT JOIN sec_contributor AS Contri ON (Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name)" +
                "LEFT JOIN sec_user AS U ON U.login = Contri.contributor " +
                "WHERE Repo.star_count>1000 AND U.followers>=:minValue AND U.followers<:maxValue AND YEAR(Repo.create_at)>2007 " +
                "GROUP BY YEAR(Repo.create_at) ");
        query.setInteger("minValue",min);
        query.setInteger("maxValue",max);
        List list =  query.list();
        session.close();
        return list;
    }

    /**
     * follower statistic used to do one-way ANOVA
     *
     * @return {xij}
     */
    @Override
    public List<List<Integer>> variableLanguage() {
        List<List<Integer>> lists = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query2 = session.createQuery("select language from SecRepoEntity group by language order by count(*) desc ");
        query2.setMaxResults(8);
        List<String> lans = query2.list();
        Query query = session.createQuery("select starCount from SecRepoEntity where language=:lan and createAt<'2013-12-30' order by updateAt desc ");
        query.setMaxResults(121);
        for (String lan: lans) {
            query.setString("lan",lan);
            lists.add(query.list());
        }
        session.close();
        return lists;
    }

    /**
     * fields statistic used to do one-way ANOVA
     *
     * @return {xij}
     */
    @Override
    public List<List<Integer>> variableFields() {
        Session session = sessionFactory.openSession();
        List<List<Integer>> longs = new ArrayList<>();
        //regexp
        SQLQuery query = session.createSQLQuery("select star_count from sec_repo where YEAR(create_at)<=2013 AND description REGEXP :keyword ORDER BY update_at DESC LIMIT 0,121 ");
        query.setString("keyword", "(^| +)node.js($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)library($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)web($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)api($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)vim($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)plugin($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)json($| +|[^a-zA-Z])");
        longs.add(query.list());
        query.setString("keyword","(^| +)app($| +|[^a-zA-Z])");
        longs.add(query.list());
        session.close();
        return longs;
    }

    /**
     * person statistic used to do one-way statistic
     *
     * @return {xij}
     */
    @Override
    public List<List<Integer>> variablePerson() {
        Session session = sessionFactory.openSession();
        List list = new ArrayList<>();
        Query query = session.createSQLQuery("SELECT Repo.star_count FROM sec_repo AS Repo " +
                "LEFT JOIN sec_contributor AS Contri ON (Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name)" +
                "LEFT JOIN sec_user AS U ON U.login = Contri.contributor " +
                "WHERE YEAR(Repo.create_at)<=2013 GROUP BY Repo.owner,Repo.name " +
                "HAVING COUNT(CASE WHEN followers>100 THEN 1 END)=:pValue ORDER BY Repo.update_at DESC LIMIT 0,121");
        for (int i = 0; i < 7; i++) {
            query.setInteger("pValue",i);
            list.add(query.list());
        }
        query = session.createSQLQuery("SELECT Repo.star_count FROM sec_repo AS Repo " +
                "LEFT JOIN sec_contributor AS Contri ON (Repo.owner = Contri.repo_owner AND Repo.name = Contri.repo_name)" +
                "LEFT JOIN sec_user AS U ON U.login = Contri.contributor " +
                "WHERE YEAR(Repo.create_at)<=2013 GROUP BY Repo.owner,Repo.name " +
                "HAVING COUNT(CASE WHEN followers>100 THEN 1 END)>=7 ORDER BY Repo.update_at DESC LIMIT 0,121");
        list.add(query.list());
        session.close();
        return list;
    }

    /**
     * just for 'group by case' test
     *
     * @return
     */
    @Override
    public List<Object[]> refactorTest() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT COUNT(CASE WHEN YEAR(create_at)=2008 THEN 1 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2009 THEN 2 END), " +
                "COUNT(CASE WHEN YEAR(create_at)=2010 THEN 3 END) " +
                "FROM sec_repo GROUP BY (CASE WHEN description REGEXP '(^| +)node.js($| +|[^a-zA-Z])' THEN 1 " +
                "WHEN description REGEXP '(^| +)javascript($| +|[^a-zA-Z])' THEN 2 ) ");
        List<Object[]> objects = query.list();
        session.close();
        return objects;
    }

}
