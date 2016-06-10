package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2016/5/25.
 */
@Repository
public class SecUserDaoImpl implements SecUserDaoService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<SecUserEntity> getRecommendUser(String webUsername, int offset, int maxResults) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT id,login,avatar_url,html_url,type,name,company,blog,location,email,bio,public_repos,public_gists,followers,following,create_at,update_at FROM (SELECT web_username, count(*) AS num FROM ((SELECT username FROM register_star_user WHERE web_username = :web1 ) AS A LEFT JOIN register_star_user B ON A.username = B.username) GROUP BY  web_username) AS C LEFT JOIN register_star_user D ON C.web_username = D.web_username LEFT JOIN sec_user ON D.username = sec_user.login WHERE D.username NOT IN (SELECT username FROM register_star_user WHERE web_username = :web2 ) ORDER BY num DESC, followers DESC");
        query.setString("web1",webUsername);
        query.setString("web2",webUsername);
        query.setFirstResult(offset);
        query.setMaxResults(maxResults);
        List<Object[]> list = query.list();
        session.close();
        List<SecUserEntity> entities = new ArrayList<>();
        for (Object[] item:list){
            SecUserEntity entity = new SecUserEntity();
            entity.setId(Long.valueOf(item[0].toString()));
            entity.setLogin(item[1].toString());
            entity.setAvatarUrl(item[2].toString());
            entity.setHtmlUrl(item[3].toString());
            entity.setType(item[4].toString());
            entity.setName(item[5].toString());
            entity.setCompany(item[6].toString());
            entity.setBlog(item[7].toString());
            entity.setLocation(item[8].toString());
            entity.setEmail(item[9].toString());
            entity.setBio(item[10].toString());
            entity.setPublicRepos((int)item[11]);
            entity.setPublicGists((int)item[12]);
            entity.setFollowers((int)item[13]);
            entity.setFollowing((int)item[14]);
            entity.setCreateAt((Timestamp)item[15]);
            entity.setUpdateAt((Timestamp)item[16]);
            entities.add(entity);
        }
        return entities;
    }

    @Override
    public List<Object[]> getUserLanguage(String login) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT language,count(*) AS num FROM ((SELECT repo_owner, repo_name FROM sec_contributor WHERE contributor = 'defunkt') AS C JOIN sec_repo R ON C.repo_owner = R.owner AND C.repo_name = R.name) WHERE language <> '' GROUP BY language ORDER BY num DESC");
        List<Object []> list = query.list();
        session.close();
        return list;
    }

    @Override
    public SecUserEntity getUserBasicInfo(String login) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("from SecUserEntity u where u.login = :log");
        query.setString("log",login);
        List<SecUserEntity> list = query.list();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public String getUserAvatar(String login) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT avatar_url FROM sec_user WHERE login = :log");
        query.setString("log",login);
        List<String> list = query.list();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        else {
            return "";
        }
    }

    @Override
    public List<String> getAllUserLocation() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT location FROM sec_user WHERE location <> '' ");
        List<String> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<SecUserEntity> getSearchResult(String keyword, SortType sortType, boolean isDesc, int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        String hql = "from SecUserEntity where login like :k1 or name like :k2 order by ";
        if (sortType!=null){
            switch (sortType){
                case User_Follored:hql+="followers ";break;
                case User_Folloring:hql+="following ";break;
                case User_Repos:hql+="publicRepos ";break;
                case User_Update:hql+="updateAt ";break;
                default:hql+="login ";
            }
        }
        else {
            hql+="login ";
        }
        if (isDesc){
            hql+="desc";
        }
        Query query = session.createQuery(hql);
        query.setString("k1","%"+keyword+"%");
        query.setString("k2","%"+keyword+"%");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<SecUserEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<SecUserEntity> getUsers(SortType sortType, boolean isDesc, int offset, int maxNum) {
        Session session =sessionFactory.openSession();
        String hql = "from SecUserEntity order by :o1 ";
        if (isDesc){
            hql+="desc";
        }
        Query query = session.createQuery(hql);
        switch (sortType){
            case User_Follored:query.setString("o1","followers");break;
            case User_Folloring:query.setString("o1","following");break;
            case User_Repos:query.setString("o1", "publicRepos");break;
            case User_Update:query.setString("o1","updateAt");break;
            default:query.setString("o1","login");
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<SecUserEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getTotalCount() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_user");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Long.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public long getSearchCount(String keyword) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_user WHERE login LIKE :log OR name LIKE :log1");
        query.setString("log","%"+keyword+"%");
        query.setString("log1","%"+keyword+"%");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Long.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public List<SecRepoEntity> getUserOwnRepos(String login, SortType sortType, int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SecRepoEntity where owner = :own order by :o1");
        query.setString("own",login);
        if (sortType!=null){
            switch (sortType){
                case Repo_Star:query.setString("o1","starCount");break;
                case Repo_Watch:query.setString("o1","watchersCount");break;
                case Repo_Fork:query.setString("o1", "forkCount");break;
                case Repo_Update:query.setString("o1","updateAt");break;
                default:query.setString("o1","name");
            }
        }
        else {
            query.setString("o1","name");
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<SecRepoEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<SecRepoEntity> getUserSubscribeRepos(String login,int maxResults) {
        Session session  = sessionFactory.openSession();
        Query query  = session.createQuery("from SecRepoEntity e where (e.owner, e.name) in (select repoOwner, repoName from SecSubscriberEntity where subscriber = :log) order by starCount desc ");
        query.setFirstResult(0);
        query.setMaxResults(maxResults);
        query.setString("log", login);
        List<SecRepoEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getRelatedUser(String username,int limitResults) {
        Session session = sessionFactory.openSession();
        //SQLQuery query = session.createSQLQuery("SELECT contributor, count(*) AS num FROM sec_contributor t1 RIGHT JOIN (SELECT t2.repo_owner, t2.repo_name FROM sec_contributor t2 WHERE t2.contributor = :param1) AS t3 ON t3.repo_owner = t1.repo_owner AND t3.repo_name = t1.repo_name WHERE contributor <> :param2 GROUP BY contributor ORDER BY num DESC");
        SQLQuery query = session.createSQLQuery("SELECT contributor, group_concat(concat(t3.repo_owner,'/',t3.repo_name) ) FROM sec_contributor t1 RIGHT JOIN (SELECT t2.repo_owner, t2.repo_name FROM sec_contributor t2 WHERE t2.contributor = :param1) AS t3 ON t3.repo_owner = t1.repo_owner AND t3.repo_name = t1.repo_name WHERE contributor <> :param2 GROUP BY contributor ORDER BY count(*) DESC ");
        query.setString("param1",username);
        query.setString("param2",username);
        query.setMaxResults(limitResults);
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<SecRepoEntity> getUserContributeRepos(String login,int maxResults) {
        Session session  = sessionFactory.openSession();
        Query query  = session.createQuery("from SecRepoEntity e where (e.owner, e.name) in (select repoOwner, repoName from SecContributorEntity where contributor = :log) order by starCount desc ");
        query.setFirstResult(0);
        query.setMaxResults(maxResults);
        query.setString("log", login);
        List<SecRepoEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getStatsUserType() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT sec_user.type, count(*) FROM sec_user WHERE sec_user.type <> '' GROUP BY type");
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getStatsUserOwnRepo(int min, int max) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_user WHERE public_repos >= :m1 AND public_repos < :m2");
        query.setInteger("m1",min);
        query.setInteger("m2",max);
        List list = query.list();
        session.close();
        return Long.valueOf(list.get(0).toString());
    }

    @Override
    public long getStatsUserGist(int min, int max) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_user WHERE public_gists >= :m1 AND public_gists < :m2");
        query.setInteger("m1",min);
        query.setInteger("m2",max);
        List list = query.list();
        session.close();
        return Long.valueOf(list.get(0).toString());
    }

    @Override
    public long getStatsUserFollower(int min, int max) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_user WHERE followers >= :m1 AND followers < :m2");
        query.setInteger("m1",min);
        query.setInteger("m2",max);
        List list = query.list();
        session.close();
        return Long.valueOf(list.get(0).toString());
    }

    @Override
    public List<Object[]> getStatsCreateTime() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT y, count(*) FROM " +
                "(SELECT id, date_format(create_at, '%Y') AS y FROM sec_user) AS temp GROUP BY y");
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getStatsEmail(int maxResults) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT DISTINCT substring_index(email, '@', -1) AS suffix, count(*) AS cc FROM sec_user WHERE email <>'' GROUP BY suffix ORDER BY cc DESC");
        query.setMaxResults(maxResults);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> getStatsCompany(int maxResults) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT company, count(*) as num from sec_user " +
                "WHERE company <> '' group by company order by num desc ");
        query.setMaxResults(maxResults);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public double getMaxRepos() {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(public_repos) FROM sec_user");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxGists() {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(public_gists) FROM sec_user");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxFollower() {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(followers) FROM sec_user");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxUserContribute() {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(A.num) FROM (SELECT COUNT(*) AS num " +
                "FROM sec_contributor GROUP BY contributor) AS A ");
        List list = query.list();
        session.close();
        if (list.size()>0){
            return Double.valueOf(list.get(0).toString());
        }
        else {
            return 0;
        }
    }

    @Override
    public double getMaxUserFollowing() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(following) FROM sec_user");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getUserContribute(String login) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_contributor WHERE contributor = :con");
        query.setString("con",login);
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

}
