package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecRepoLabelEntity;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/24.
 */
@Repository
public class SecRepoDaoImpl implements SecRepoDaoService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<SecRepoEntity> getSearchResult(String keyword, int offset, int maxNum, SortType type, boolean isDesc, String filterType, String language, String createYear) {

        //todo add rank strategy and put the best result at the begin
        Session session =sessionFactory.openSession();
        String hql = "from SecRepoEntity where (name like :k1 or description like :k2) ";
        if ((filterType!=null)&&(!filterType.isEmpty())){
            hql+="and description like :filter ";
        }
        if ((language!=null)&&(!language.isEmpty())){
            hql+="and language = :lan ";
        }
        if ((createYear!=null)&&(!createYear.isEmpty())){
            hql+="and date_format(createAt,'%Y') = :create) ";
        }
        if (type!=null){
            switch (type) {
                case Repo_Star:hql+="order by starCount ";break;
                case Repo_Fork:hql+="order by forkCount ";break;
                case Repo_Watch:hql+="order by watchersCount ";break;
                case Repo_Update:hql+="order by updateAt ";break;
                case Repo_Name:hql+="order by name ";
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
            sql+="and date_format(createAt,'%Y') = :create) ";
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
            query.setString("create",createYear);
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
        //todo
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            SQLQuery query1 = session.createSQLQuery("SELECT id FROM sec_repo WHERE owner = :own AND name = :nam");
            query1.setString("own",ownername);
            query1.setString("nam",reponame);
            long id = Long.valueOf(query1.list().get(0).toString());
            Query query2 = session.createQuery("from SecRepoLabelEntity where repoId = :id");
            query2.setLong("id",id);
            SecRepoLabelEntity label = (SecRepoLabelEntity) query2.list().get(0);
            SQLQuery query3 = session.createSQLQuery("SELECT repo_id FROM sec_repo_label WHERE repo_id <> ? ORDER BY " +
                    "(node_js*?+javascript*?+library*?+ruby*?+web*?+api*?+vim*?+plugin*?+rust*?+app*?+client*?+server*?" +
                    "+json*?+framework*?+python*?+browser*?+rails*?+css*?+android*?+jquery*?+html*?+test*?+php*?+command*?" +
                    "+tool*?+demo*?+wrapper*?+ios*?+linux*?+windows*?+os_x*?+django*?+google*?+generator*?+docker*?+image*?+template*?) DESC ");
            query3.setLong(1,id);
            query3.setInteger(2,label.getNodeJs());
            query3.setInteger(3,label.getJavascript());
            query3.setInteger(4,label.getLibrary());
            query3.setInteger(5,label.getRuby());
            query3.setInteger(6,label.getWeb());
            query3.setInteger(7,label.getApi());
            query3.setInteger(8,label.getVim());
            query3.setInteger(9,label.getPlugin());
            query3.setInteger(10,label.getRust());
            query3.setInteger(11,label.getApp());
            query3.setInteger(12,label.getClient());
            query3.setInteger(13,label.getServer());
            query3.setInteger(14,label.getJson());
            query3.setInteger(15,label.getFramework());
            query3.setInteger(16,label.getPython());
            query3.setInteger(17,label.getBrowser());
            query3.setInteger(18,label.getRails());
            query3.setInteger(19,label.getCss());
            query3.setInteger(20,label.getAndroid());
            query3.setInteger(21,label.getJquery());
            query3.setInteger(22,label.getHtml());
            query3.setInteger(23,label.getTest());
            query3.setInteger(24,label.getPhp());
            query3.setInteger(25,label.getCommand());
            query3.setInteger(26,label.getTool());
            query3.setInteger(27,label.getDemo());
            query3.setInteger(28,label.getWrapper());
            query3.setInteger(29,label.getIos());
            query3.setInteger(30,label.getLinux());
            query3.setInteger(31,label.getWindows());
            query3.setInteger(32,label.getOsX());
            query3.setInteger(33,label.getDjango());
            query3.setInteger(34,label.getGoogle());
            query3.setInteger(35,label.getGenerator());
            query3.setInteger(36,label.getDocker());
            query3.setInteger(37,label.getImage());
            query3.setInteger(38,label.getTemplate());

            query3.setMaxResults(5);
            List<Long> id_list = query3.list();

        } catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<SecRepoEntity> getRecommendRepo(String register) {
        //todo
        return null;
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
