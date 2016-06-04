package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecRepoLabelEntity;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2016/5/24.
 */
@Repository
public class SecRepoDaoImpl implements SecRepoDaoService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<SecRepoEntity> searchWithLogin(String webUsername, String keyword, int offset, int maxResult, String filter, String language, String createYear) {
        Session session =sessionFactory.openSession();
        Transaction transaction = null;
        List<SecRepoEntity> entityList = new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            Query query1 = session.createQuery("from SecRegisterLabelEntity where registerLogin = :webName");
            query1.setString("webName",webUsername);
            SecRegisterLabelEntity label = (SecRegisterLabelEntity) query1.list().get(0);

            String sql = "SELECT id,owner,sec_repo.name,html_url,description,sec_repo.size,star_count,watchers_count,sec_repo.language," +
                    "fork_count,create_at,update_at FROM sec_repo JOIN sec_repo_label ON id = repo_id WHERE (sec_repo.name LIKE :k1 OR description LIKE :k2) ";
            if ((filter!=null)&&(!filter.isEmpty())){
                sql+="AND description LIKE :filter ";
            }
            if ((language!=null)&&(!language.isEmpty())){
                sql+="AND language = :lan ";
            }
            if ((createYear!=null)&&(!createYear.isEmpty())){
                sql+="and date_format(createAt,'%Y') = :createY) ";
            }

            sql+=("ORDER BY (node_js*:nodejs+javascript*:js+library*:li+ruby*:ruby+web*:we+api*:api+vim*:vim+plugin*:plugin+rust*:rust+app*:app+client*:client+server*:server" +
                    "+json*:json+framework*:frame+python*:py+browser*:browser+rails*:rails+css*:css+android*:android+jquery*:jquery+html*:html+test*:test+php*:php+command*:com" +
                    "+tool*:tool+demo*:demo+wrapper*:wrapper+ios*:ios+linux*:linux+windows*:win+os_x*:osx+django*:django+google*:google+generator*:gen+docker*:docker+image*:img+template*:tem) DESC ");

            SQLQuery query2 = session.createSQLQuery(sql);
            query2.setString("k1","%"+keyword+"%");
            query2.setString("k2","%"+keyword+"%");
            if ((filter!=null)&&(!filter.isEmpty())){
                query2.setString("filter", "%"+filter+"%");
            }
            if ((language!=null)&&(!language.isEmpty())){
                query2.setString("lan", language);
            }
            if ((createYear!=null)&&(!createYear.isEmpty())){
                query2.setString("createY",createYear);
            }
            query2.setDouble("nodejs",label.getNodeJs());
            query2.setDouble("js",label.getJavascript());
            query2.setDouble("li",label.getLibrary());
            query2.setDouble("ruby",label.getRuby());
            query2.setDouble("we",label.getWeb());
            query2.setDouble("api",label.getApi());
            query2.setDouble("vim",label.getVim());
            query2.setDouble("plugin",label.getPlugin());
            query2.setDouble("rust",label.getRust());
            query2.setDouble("app",label.getApp());
            query2.setDouble("client",label.getClient());
            query2.setDouble("server",label.getServer());
            query2.setDouble("json",label.getJson());
            query2.setDouble("frame",label.getFramework());
            query2.setDouble("py",label.getPython());
            query2.setDouble("browser",label.getBrowser());
            query2.setDouble("rails",label.getRails());
            query2.setDouble("css",label.getCss());
            query2.setDouble("android",label.getAndroid());
            query2.setDouble("jquery",label.getJquery());
            query2.setDouble("html",label.getHtml());
            query2.setDouble("test",label.getTest());
            query2.setDouble("php",label.getPhp());
            query2.setDouble("com",label.getCommand());
            query2.setDouble("tool",label.getTool());
            query2.setDouble("demo",label.getDemo());
            query2.setDouble("wrapper",label.getWrapper());
            query2.setDouble("ios",label.getIos());
            query2.setDouble("linux",label.getLinux());
            query2.setDouble("win",label.getWindows());
            query2.setDouble("osx",label.getOsX());
            query2.setDouble("django",label.getDjango());
            query2.setDouble("google",label.getGoogle());
            query2.setDouble("gen",label.getGenerator());
            query2.setDouble("docker",label.getDocker());
            query2.setDouble("img",label.getImage());
            query2.setDouble("tem",label.getTemplate());

            query2.setFirstResult(offset);
            query2.setMaxResults(maxResult);

            List<Object[]> list = query2.list();
            for (Object[] item:list){
                SecRepoEntity entity = new SecRepoEntity();
                entity.setId(Long.valueOf(item[0].toString()));
                entity.setOwner(item[1].toString());
                entity.setName(item[2].toString());
                entity.setHtmlUrl(item[3].toString());
                entity.setDescription(item[4].toString());
                entity.setSize((Integer)item[5]);
                entity.setStarCount((Integer)item[6]);
                entity.setWatchersCount((Integer)item[7]);
                entity.setLanguage(item[8].toString());
                entity.setForkCount((Integer) item[9]);
                entity.setCreateAt((Timestamp)item[10]);
                entity.setUpdateAt((Timestamp)item[11]);
                entityList.add(entity);
            }

        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }

        return entityList;
    }

    @Override
    public List<SecRepoEntity> getSearchResult(String keyword, int offset, int maxNum, SortType type, boolean isDesc, String filterType, String language, String createYear) {

        Session session =sessionFactory.openSession();
        String hql = "from SecRepoEntity where (name like :k1 or description like :k2) ";
        if ((filterType!=null)&&(!filterType.isEmpty())){
            hql+="and description like :filter ";
        }
        if ((language!=null)&&(!language.isEmpty())){
            hql+="and language = :lan ";
        }
        if ((createYear!=null)&&(!createYear.isEmpty())){
            hql+="and date_format(createAt,'%Y') = :create ";
        }
        if (type!=null){
            switch (type) {
                case Repo_Star:hql+="order by starCount ";break;
                case Repo_Fork:hql+="order by forkCount ";break;
                case Repo_Watch:hql+="order by watchersCount ";break;
                case Repo_Update:hql+="order by updateAt ";break;
                case Repo_Name:hql+="order by name ";break;
                default:hql+="order by name ";break;
            }
        }
        else{
            hql+="order by name ";
        }
        hql += isDesc?"desc":"asc";
        Query query = session.createQuery(hql);
        query.setString("k1","%"+keyword+"%");
        query.setString("k2","%"+keyword+"%");
        if ((filterType!=null)&&(!filterType.isEmpty())){
            query.setString("filter", "%"+filterType+"%");
        }
        if ((language!=null)&&(!language.isEmpty())){
            query.setString("lan", language);
        }
        if ((createYear!=null)&&(!createYear.isEmpty())){
            query.setString("create",createYear);
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<SecRepoEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getSearchCount(String keyword, String filterType, String language, String createYear) {
        Session session =sessionFactory.openSession();
        String sql = "SELECT count(*) from sec_repo where (sec_repo.name like :k1 or description like :k2) ";
        if ((filterType!=null)&&(!filterType.isEmpty())){
            sql+="and description like :filter ";
        }
        if ((language!=null)&&(!language.isEmpty())){
            sql+="and language = :lan ";
        }
        if ((createYear!=null)&&(!createYear.isEmpty())){
            sql+="and date_format(create_at,'%Y') = :createY ";
        }

        SQLQuery query = session.createSQLQuery(sql);
        query.setString("k1","%"+keyword+"%");
        query.setString("k2","%"+keyword+"%");
        if ((filterType!=null)&&(!filterType.isEmpty())){
            query.setString("filter", "%"+filterType+"%");
        }
        if ((language!=null)&&(!language.isEmpty())){
            query.setString("lan", language);
        }
        if ((createYear!=null)&&(!createYear.isEmpty())){
            query.setString("createY",createYear);
        }
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
    public List<SecRepoEntity> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = null;
        String order = isDesc?"desc":"asc";
        if (sortType!=null){
            switch (sortType) {
                case Repo_Star:query = session.createQuery("from SecRepoEntity order by starCount "+order);break;
                case Repo_Fork:query = session.createQuery("from SecRepoEntity order by forkCount "+order);break;
                case Repo_Watch:query = session.createQuery("from SecRepoEntity order by starCount "+order);break;
                case Repo_Update:query = session.createQuery("from SecRepoEntity order by updateAt "+order);break;
                default:query = session.createQuery("from SecRepoEntity order by name "+order);break;
            }
        }
        else {
            query = session.createQuery("from SecRepoEntity order by name "+order);
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List<SecRepoEntity> result = query.list();
        session.close();
        return result;
    }

    @Override
    public long getTotalCount() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_repo");
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
    public SecRepoEntity getRepoBasicInfo(String owner, String repoName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SecRepoEntity where owner = :own and name = :nam");
        query.setString("own",owner);
        query.setString("nam",repoName);
        List<SecRepoEntity> list = query.list();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<String> getRepoContributor(String owner, String repoName) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT DISTINCT contributor FROM sec_contributor WHERE repo_owner = :own AND repo_name = :nam ORDER BY contributions DESC ");
        query.setString("own",owner);
        query.setString("nam",repoName);
        List<String> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<String> getRepoSubscriber(String owner, String repoName) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT DISTINCT subscriber FROM sec_subscriber WHERE repo_owner = :own AND repo_name = :nam ");
        query.setString("own",owner);
        query.setString("nam",repoName);
        List<String> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<SecRepoEntity> getRelatedRepo(String ownername, String reponame) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<SecRepoEntity> repoEntityList = new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            SQLQuery query1 = session.createSQLQuery("SELECT id FROM sec_repo WHERE owner = :own AND name = :nam");
            query1.setString("own",ownername);
            query1.setString("nam",reponame);
            long id = Long.valueOf(query1.list().get(0).toString());
            Query query2 = session.createQuery("from SecRepoLabelEntity where repoId = :id");
            query2.setLong("id",id);
            SecRepoLabelEntity label = (SecRepoLabelEntity) query2.list().get(0);
            SQLQuery query3 = session.createSQLQuery("SELECT id,owner,sec_repo.name,html_url,description,sec_repo.size,star_count,watchers_count,sec_repo.language," +
                    "fork_count,create_at,update_at FROM sec_repo JOIN sec_repo_label ON id = repo_id WHERE repo_id <> :id ORDER BY " +
                    "(node_js*:nodejs+javascript*:js+library*:li+ruby*:ruby+web*:we+api*:api+vim*:vim+plugin*:plugin+rust*:rust+app*:app+client*:client+server*:server" +
                    "+json*:json+framework*:frame+python*:py+browser*:browser+rails*:rails+css*:css+android*:android+jquery*:jquery+html*:html+test*:test+php*:php+command*:com" +
                    "+tool*:tool+demo*:demo+wrapper*:wrapper+ios*:ios+linux*:linux+windows*:win+os_x*:osx+django*:django+google*:google+generator*:gen+docker*:docker+image*:img+template*:tem) DESC ");
            query3.setLong("id",id);
            query3.setInteger("nodejs",label.getNodeJs());
            query3.setInteger("js",label.getJavascript());
            query3.setInteger("li",label.getLibrary());
            query3.setInteger("ruby",label.getRuby());
            query3.setInteger("we",label.getWeb());
            query3.setInteger("api",label.getApi());
            query3.setInteger("vim",label.getVim());
            query3.setInteger("plugin",label.getPlugin());
            query3.setInteger("rust",label.getRust());
            query3.setInteger("app",label.getApp());
            query3.setInteger("client",label.getClient());
            query3.setInteger("server",label.getServer());
            query3.setInteger("json",label.getJson());
            query3.setInteger("frame",label.getFramework());
            query3.setInteger("py",label.getPython());
            query3.setInteger("browser",label.getBrowser());
            query3.setInteger("rails",label.getRails());
            query3.setInteger("css",label.getCss());
            query3.setInteger("android",label.getAndroid());
            query3.setInteger("jquery",label.getJquery());
            query3.setInteger("html",label.getHtml());
            query3.setInteger("test",label.getTest());
            query3.setInteger("php",label.getPhp());
            query3.setInteger("com",label.getCommand());
            query3.setInteger("tool",label.getTool());
            query3.setInteger("demo",label.getDemo());
            query3.setInteger("wrapper",label.getWrapper());
            query3.setInteger("ios",label.getIos());
            query3.setInteger("linux",label.getLinux());
            query3.setInteger("win",label.getWindows());
            query3.setInteger("osx",label.getOsX());
            query3.setInteger("django",label.getDjango());
            query3.setInteger("google",label.getGoogle());
            query3.setInteger("gen",label.getGenerator());
            query3.setInteger("docker",label.getDocker());
            query3.setInteger("img",label.getImage());
            query3.setInteger("tem",label.getTemplate());

            query3.setMaxResults(5);
            List<Object[]> list = query3.list();
            for (Object[] item:list){
                SecRepoEntity entity = new SecRepoEntity();
                entity.setId(Long.valueOf(item[0].toString()));
                entity.setOwner(item[1].toString());
                entity.setName(item[2].toString());
                entity.setHtmlUrl(item[3].toString());
                entity.setDescription(item[4].toString());
                entity.setSize((Integer)item[5]);
                entity.setStarCount((Integer)item[6]);
                entity.setWatchersCount((Integer)item[7]);
                entity.setLanguage(item[8].toString());
                entity.setForkCount((Integer) item[9]);
                entity.setCreateAt((Timestamp)item[10]);
                entity.setUpdateAt((Timestamp)item[11]);
                repoEntityList.add(entity);
            }

        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return repoEntityList;
    }

    @Override
    public List<SecRepoEntity> getRecommendRepo(String register, int offset, int maxResults) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<SecRepoEntity> repoEntityList = new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            Query query1 = session.createQuery("from SecRegisterLabelEntity where registerLogin = :register");
            query1.setString("register",register);
            SecRegisterLabelEntity label = (SecRegisterLabelEntity) query1.list().get(0);
            SQLQuery query3 = session.createSQLQuery("SELECT id,owner,sec_repo.name,html_url,description,sec_repo.size,star_count,watchers_count,sec_repo.language," +
                    "fork_count,create_at,update_at FROM sec_repo JOIN sec_repo_label ON id = repo_id ORDER BY " +
                    "(node_js*:nodejs+javascript*:js+library*:li+ruby*:ruby+web*:we+api*:api+vim*:vim+plugin*:plugin+rust*:rust+app*:app+client*:client+server*:server" +
                    "+json*:json+framework*:frame+python*:py+browser*:browser+rails*:rails+css*:css+android*:android+jquery*:jquery+html*:html+test*:test+php*:php+command*:com" +
                    "+tool*:tool+demo*:demo+wrapper*:wrapper+ios*:ios+linux*:linux+windows*:win+os_x*:osx+django*:django+google*:google+generator*:gen+docker*:docker+image*:img+template*:tem) DESC ");
            query3.setDouble("nodejs",label.getNodeJs());
            query3.setDouble("js",label.getJavascript());
            query3.setDouble("li",label.getLibrary());
            query3.setDouble("ruby",label.getRuby());
            query3.setDouble("we",label.getWeb());
            query3.setDouble("api",label.getApi());
            query3.setDouble("vim",label.getVim());
            query3.setDouble("plugin",label.getPlugin());
            query3.setDouble("rust",label.getRust());
            query3.setDouble("app",label.getApp());
            query3.setDouble("client",label.getClient());
            query3.setDouble("server",label.getServer());
            query3.setDouble("json",label.getJson());
            query3.setDouble("frame",label.getFramework());
            query3.setDouble("py",label.getPython());
            query3.setDouble("browser",label.getBrowser());
            query3.setDouble("rails",label.getRails());
            query3.setDouble("css",label.getCss());
            query3.setDouble("android",label.getAndroid());
            query3.setDouble("jquery",label.getJquery());
            query3.setDouble("html",label.getHtml());
            query3.setDouble("test",label.getTest());
            query3.setDouble("php",label.getPhp());
            query3.setDouble("com",label.getCommand());
            query3.setDouble("tool",label.getTool());
            query3.setDouble("demo",label.getDemo());
            query3.setDouble("wrapper",label.getWrapper());
            query3.setDouble("ios",label.getIos());
            query3.setDouble("linux",label.getLinux());
            query3.setDouble("win",label.getWindows());
            query3.setDouble("osx",label.getOsX());
            query3.setDouble("django",label.getDjango());
            query3.setDouble("google",label.getGoogle());
            query3.setDouble("gen",label.getGenerator());
            query3.setDouble("docker",label.getDocker());
            query3.setDouble("img",label.getImage());
            query3.setDouble("tem",label.getTemplate());

            query3.setFirstResult(offset);
            query3.setMaxResults(maxResults);
            List<Object[]> list = query3.list();
            for (Object[] item:list){
                SecRepoEntity entity = new SecRepoEntity();
                entity.setId(Long.valueOf(item[0].toString()));
                entity.setOwner(item[1].toString());
                entity.setName(item[2].toString());
                entity.setHtmlUrl(item[3].toString());
                entity.setDescription(item[4].toString());
                entity.setSize((Integer)item[5]);
                entity.setStarCount((Integer)item[6]);
                entity.setWatchersCount((Integer)item[7]);
                entity.setLanguage(item[8].toString());
                entity.setForkCount((Integer) item[9]);
                entity.setCreateAt((Timestamp)item[10]);
                entity.setUpdateAt((Timestamp)item[11]);
                repoEntityList.add(entity);
            }

        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return repoEntityList;
    }

    @Override
    public SecRepoLabelEntity getRepoLabel(String ownername, String reponame) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from SecRepoLabelEntity where repoId in (select id from SecRepoEntity where owner = :own and name = :nam)");
        query.setString("own",ownername);
        query.setString("nam",reponame);
        List<SecRepoLabelEntity> list = query.list();
        session.close();
        if (list.size()>0){
            return list.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public double getMaxRepoSize() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(repo.size) from sec_repo repo");
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
    public double getMinRepoSize() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT min(repo.size) from sec_repo repo");
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
    public double getMaxRepoFork() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(repo.fork_count) from sec_repo repo");
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
    public double getMinRepoFork() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT min(repo.fork_count) from sec_repo repo");
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
    public double getMaxRepoStar() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(repo.star_count) from sec_repo repo");
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
    public double getMinRepoStar() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT min(repo.star_count) from sec_repo repo");
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
    public double getMaxRepoContriCount() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(num) from (SELECT count(*) AS num FROM sec_contributor " +
                "con GROUP BY con.repo_owner, con.repo_name) AS temp ");
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
    public double getMinRepoContriCount() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT min(num) from (SELECT count(*) AS num FROM sec_contributor " +
                "con GROUP BY con.repo_owner, con.repo_name) AS temp ");
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
    public double getMaxCommitCount() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT max(num) from (SELECT sum(contributions) AS num FROM sec_contributor " +
                "con GROUP BY con.repo_owner, con.repo_name) AS temp ");
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
    public double getMinCommitCount() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT min(num) from (SELECT sum(contributions) AS num FROM sec_contributor " +
                "con GROUP BY con.repo_owner, con.repo_name) AS temp ");
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
    public double getRepoContriCount(String ownername, String reponame) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_contributor WHERE repo_owner " +
                "= :own AND repo_name = :nam");
        query.setString("own",ownername);
        query.setString("nam",reponame);
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
    public double getRepoCommitCount(String ownername, String reponame) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT sum(contributions) FROM sec_contributor WHERE repo_owner " +
                "= :own AND repo_name = :nam");
        query.setString("own",ownername);
        query.setString("nam",reponame);
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
    public List<Object[]> getStatsCreateTime() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT y, count(*) FROM " +
                "(SELECT id, date_format(create_at, '%Y') AS y FROM sec_repo) AS temp GROUP BY y");
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getStatsFork(int min, int max) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_repo WHERE fork_count " +
                ">= :minFork AND fork_count < :maxFork");
        query.setInteger("minFork", min);
        query.setInteger("maxFork", max);
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = Long.valueOf(list.get(0).toString());
        }
        session.close();
        return result;
    }

    @Override
    public long getStatsStar(int min, int max) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_repo WHERE star_count " +
                ">= :minFork AND star_count < :maxFork");
        query.setInteger("minFork", min);
        query.setInteger("maxFork", max);
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = Long.valueOf(list.get(0).toString());
        }
        session.close();
        return result;
    }

    @Override
    public List<Object[]> getStatsLanguage(int maxResults) {
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT repo.language, count(*) AS num FROM sec_repo repo " +
                "GROUP BY repo.language ORDER BY num DESC " );
        query.setMaxResults(maxResults);
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public long getStatsSize(int min, int max) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT count(*) FROM sec_repo WHERE sec_repo.size " +
                ">= :minSize AND sec_repo.size < :maxSize");
        query.setInteger("minSize", min);
        query.setInteger("maxSize", max);
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = Long.valueOf(list.get(0).toString());
        }
        session.close();
        return result;
    }
}
