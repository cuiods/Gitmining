package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.entity.SecRepoEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        switch (type) {
            case Repo_Star:hql+="order by starCount ";break;
            case Repo_Fork:hql+="order by forkCount ";break;
            case Repo_Watch:hql+="order by watchersCount ";break;
            case Repo_Update:hql+="order by updateAt ";break;
            default:hql+="order by name ";break;
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
        switch (sortType) {
            case Repo_Star:query = session.createQuery("from SecRepoEntity order by starCount "+order);break;
            case Repo_Fork:query = session.createQuery("from SecRepoEntity order by forkCount "+order);break;
            case Repo_Watch:query = session.createQuery("from SecRepoEntity order by starCount "+order);break;
            case Repo_Update:query = session.createQuery("from SecRepoEntity order by updateAt "+order);break;
            default:query = session.createQuery("from SecRepoEntity order by name "+order);break;
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
