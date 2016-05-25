package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.common.json.JsonNodeParser;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.*;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * imp of repo data service
 */
@Repository
public class RepoDaoImp implements RepoDaoService{
    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private JsonNodeParser jsonNodeParser;

    /**
     * search repos by keyword.
     *
     * @param keyword
     * @return list of repositorys
     */
    @Deprecated
    public List<TblRepo> getSearchResult(String keyword) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblRepo as u where u.name like ? ");
        query.setString(0, "%"+keyword+"%");
        List<TblRepo> repos = query.list();
        session.close();
        return repos;
    }

    public List<TblRepo> getSearchResult(String keyword, int offset, int maxNum, SortType type,
                                         boolean isDesc, String filterType, String language, Calendar createYear) {
        String hql = "from TblRepo where name like ? ";
        if (filterType!=null&&!filterType.isEmpty()) {
            hql+="and description like ? ";
        }
        if (language!=null&&!language.isEmpty()) {
            hql+="and language=? ";
        }
        if (createYear!=null) {
            hql+="and createAt <= ? and createAt >= ? ";
        }
        switch (type) {
            case Repo_Star:hql+="order by numStar ";break;
            case Repo_Fork:hql+="order by numFork ";break;
            case Repo_Subcri:hql+="order by numSubscriber ";break;
            case Repo_Contri:hql+="order by numContributor ";break;
            case Repo_Update:hql+="order by updateAt ";break;
            default:hql+="order by name ";break;
        }
        hql += isDesc?"desc":"asc";
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setString(0,"%"+keyword+"%");
        int param = 1;
        if (filterType!=null&&!filterType.isEmpty()) {
            query.setString(param++,"%"+filterType+"%");
        }
        if (language!=null&&!language.isEmpty()) {
            query.setString(param++,language);
        }
        if (createYear!=null) {
            Calendar c = (Calendar) createYear.clone();
            c.set(Calendar.YEAR,createYear.get(Calendar.YEAR)+1);
            Timestamp start = new Timestamp(createYear.getTimeInMillis());
            Timestamp end = new Timestamp(c.getTimeInMillis());
            query.setTimestamp(param++,end);
            query.setTimestamp(param++,start);
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List result = query.list();
        session.close();
        return result;
    }

    /**
     * get total count of repository
     *
     * @return number of repository
     */
    public long getTotalCount() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count(*) from TblRepo");
        long result = (Long) query.list().get(0);
        session.close();
        return result;
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param sortType which type of list to get
     * @return the reference to the list
     * @deprecated
     */
    @Deprecated
    public List<TblRepo> getRepos(SortType sortType) {
        Session session = sessionFactory.openSession();
        String[] sort = {"name","numStar","numFork","numSubscriber","numContributor","numCollaborator","updateAt"};
        Query query = session.createQuery("from TblRepo order by ?");
        query.setString(0,sort[sortType.ordinal()-SortType.Repo_Name.ordinal()]);
        List result = query.list();
        session.close();
        return result;
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param offset
     * @param maxNum
     * @return the reference to the list
     */
    @Deprecated
    public List<TblRepo> getRepos(int offset, int maxNum) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblRepo");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List result = query.list();
        session.close();
        return result;
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<TblRepo> getRepos(SortType sortType,boolean isDesc, int offset, int maxNum) {
        //String[] sort = {"name","numStar","numFork","numSubscriber","numContributor","numCollaborator","updateAt"};
        Session session = sessionFactory.openSession();
        Query query = null;
        String order = isDesc?"desc":"asc";
        switch (sortType) {
            case Repo_Star:query = session.createQuery("from TblRepo order by numStar "+order);break;
            case Repo_Fork:query = session.createQuery("from TblRepo order by numFork "+order);break;
            case Repo_Subcri:query = session.createQuery("from TblRepo order by numSubscriber "+order);break;
            case Repo_Contri:query = session.createQuery("from TblRepo order by numContributor "+order);break;
            case Repo_Update:query = session.createQuery("from TblRepo order by updateAt "+order);break;
            default:query = session.createQuery("from TblRepo order by name "+order);break;
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        List result = query.list();
        session.close();
        return result;
    }

    /**
     * Get <b>detailed</b> info of a repository.
     *
     * @param owner    name of owner of the repository
     * @param repoName name of the repository
     * @return {@link TblRepo}:
     * detailed info of a repository
     */
    public TblRepo getRepoBasicInfo(String owner, String repoName) {
        TblRepo repo = null;
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblRepo where ownerName=? and name=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblRepo> repos = query.list();
        if (repos.size()>0) {
            repo = repos.get(0);
        }
        session.close();
        return repo;
    }

    /**
     * Get brief info of contributors.
     *
     * @param owner    name of the owner of the repository
     * @param repoName name of the repository
     * @return list of {@link String}
     * list of name of contributors.
     * The return value will be null if the is no such repository.
     */
    public List<String> getRepoContributor(String owner, String repoName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblContributor where ownerName=? and repo=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblContributor> contributors = query.list();
        List<String> list = new ArrayList<String>(contributors.size());
        for(int i = 0; i < contributors.size(); i++) {
            list.add(contributors.get(i).getContributor());
        }
        session.close();
        return list;
    }

    /**
     * Get brief info of collaborators.
     *
     * @param owner    name of the owner of the repository
     * @param repoName name of the repository
     * @return list of brief info of collaborators.
     */
    public List<String> getRepoCollaborator(String owner, String repoName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblCollabrator where repoOwner=? and repo=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblCollabrator> collabrators = query.list();
        List<String> list = new ArrayList<String>(collabrators.size());
        for(int i = 0; i < collabrators.size(); i++) {
            list.add(collabrators.get(i).getCollabrator());
        }
        session.close();
        return list;
    }

    /**
     * get all subscribers' names of the given repo
     *
     * @param owner
     * @param repoName
     * @return
     */
    public List<String> getRepoSubscriber(String owner, String repoName) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TblSubscriber where repoOwner=? and repo=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblSubscriber> subscriber = query.list();
        List<String> list = new ArrayList<String>(subscriber.size());
        for(int i = 0; i < subscriber.size(); i++) {
            list.add(subscriber.get(i).getSubscriber());
        }
        session.close();
        return list;
    }

    /**
     * get repository label
     *
     * @param repoOwner
     * @param repoName
     * @return RepoLabel
     */
    public RepoLabel getRepoInterest(String repoOwner, String repoName) {
        Session session = sessionFactory.openSession();
        RepoLabel repoLabel = null;
        Query query = session.createQuery("from RepoLabel where repoOwner=? and repo=?");
        query.setString(0,repoOwner);
        query.setString(1,repoName);
        List<RepoLabel> repoLabels = query.list();
        if (repoLabels.size()>0) {
            repoLabel = repoLabels.get(0);
        }
        session.close();
        return repoLabel;
    }

    /**
     * save repository interest label.
     *
     * @param repoLabel
     * @return is succeed.
     */
    public boolean saveRepoInterest(RepoLabel repoLabel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(repoLabel);
        session.flush();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public double getMaxRepoSize() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(repo.size) from TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMinRepoSize() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select min(repo.size) from TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMaxRepoFork() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(repo.numFork) from  TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMinRepoFork() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select min(repo.numFork) from  TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMaxRepoPopular() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max(repo.numStar + repo.numSubscriber) from  TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMinRepoPopular() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select min(repo.numStar + repo.numSubscriber) from  TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMaxRepoComplex() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery(
                "SELECT max(num_colla + num_contri) AS complex FROM ( (SELECT owner_name AS owner, " +
                "repo AS repo, count(*) AS num_contri FROM tbl_contributor GROUP BY owner_name, repo) " +
                "AS A RIGHT JOIN (SELECT repo_owner AS owner, repo AS repo, count(*) AS num_colla " +
                "FROM tbl_collabrator GROUP BY repo_owner, repo) AS B ON A.owner = B.owner AND " +
                "A.repo = B.repo)");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMinRepoComplex() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery(
                "SELECT min(num_colla + num_contri) AS complex FROM ( (SELECT owner_name AS owner, " +
                        "repo AS repo, count(*) AS num_contri FROM tbl_contributor GROUP BY owner_name, repo) " +
                        "AS A RIGHT JOIN (SELECT repo_owner AS owner, repo AS repo, count(*) AS num_colla " +
                        "FROM tbl_collabrator GROUP BY repo_owner, repo) AS B ON A.owner = B.owner AND " +
                        "A.repo = B.repo)");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMaxActive() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select max (repo.numCommit) from  TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getMinActive() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select min (repo.numCommit) from  TblRepo repo");
        List list = query.list();
        session.close();
        return Double.valueOf(list.get(0).toString());
    }

    @Override
    public double getRepoComplex(String ownername, String reponame) {
        Session session = sessionFactory.openSession();
        double contrCount = 0;
        double collaCount = 0;

        Query query = session.createQuery("select count(*) from TblContributor where ownerName = ? and repo = ?");
        query.setString(0,ownername);
        query.setString(1,reponame);
        List contriList = query.list();
        if (contriList.size()>0){
            contrCount = ((Long)contriList.get(0)).doubleValue();
        }

        Query query1 = session.createQuery("select count(*) from TblCollabrator where repoOwner = ?  and repo = ?");
        query1.setString(0,ownername);
        query1.setString(1,reponame);
        List collaList = query1.list();
        if (collaList.size()>0){
            collaCount = ((Long)collaList.get(0)).doubleValue();
        }

        session.close();

        return contrCount+collaCount;
    }

    @Override
    public double getRepoActive(String ownername, String reponame) {
        double result = 0;
        Session session =sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT num_commit FROM tbl_repo WHERE owner_name = :owner and name = :repo ");
        query.setString("owner",ownername);
        query.setString("repo",reponame);
        List list = query.list();
        if (list.size()>0){
            result = Double.valueOf(list.get(0).toString());
        }

        session.close();
        return result;
    }

    @Override
    public long getStatsCreateTime(Calendar fromTime, Calendar toTime) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblRepo where createAt " +
                ">= :fT and createAt <= :tT");
        query.setTimestamp("fT", fromTime.getTime());
        query.setTimestamp("tT", toTime.getTime());
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = ((Long)list.get(0));
        }
        session.close();
        return result;
    }

    @Override
    public long getStatsFork(int min, int max) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblRepo where numFork >= :minFork " +
                "and numFork <= :maxFork");
        query.setInteger("minFork", min);
        query.setInteger("maxFork", max);
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = ((Long)list.get(0));
        }
        session.close();
        return result;
    }

    @Override
    public long getStatsStar(int min, int max) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblRepo where numStar >= ? and " +
                "numStar <= ?");
        query.setInteger(0,min);
        query.setInteger(1, max);
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = ((Long)list.get(0));
        }

        session.close();
        return  result;
    }

    @Override
    public List getStatsLanguage(int maxResults) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select language, count (*) as num from TblRepo group by " +
                "language order by num desc ");
        query.setMaxResults(maxResults);
        List list = query.list();
        session.close();
        return  list;
    }

    @Override
    public long getStatsSize(int min, int max){
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("select count (*) from TblRepo t  where t.size >= " +
                "? and t.size <= ?");
        query.setInteger(0,min);
        query.setInteger(1, max);
        List list = query.list();
        long result = 0;
        if (list.size()>0){
            result = ((Long)list.get(0));
        }
        session.close();
        return result;
    }
/*
    public void updateCommit(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int totalRepos = 3216;

        int totalSuccess = 0;

        try {
            //transaction = session.beginTransaction();

            int count = 0;

            for (int i=1;i<=totalRepos;i++){
                Query query = session.createQuery("select ownerName, name from TblRepo where repoId = ?");
                query.setInteger(0, i);
                List list = query.list();
                Object[] item = (Object[])list.get(0);
                String owner = item[0].toString();
                String repo = item[1].toString();
                Map<String, CommitChart> map = jsonNodeParser.getCommitByContributors(owner, repo);
                CommitChart all = map.get("all");
                if (all!=null){
                    int commitCount = (int)all.getCommitCount();
                    Query up = session.createQuery("update TblRepo set numCommit = ? where repoId = ?");
                    up.setInteger(0,commitCount);
                    up.setInteger(1, i);
                    int result = up.executeUpdate();
                    count++;
                    totalSuccess++;
                }
                if (count>=20){
                    session.flush();
                    transaction.commit();
                    count=0;
                }
            }
            if (count != 0)
                session.flush();
                transaction.commit();

        } catch (Exception e){
            System.out.println("===============Exception begin=====================");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println("================Exception end=======================");
            e.printStackTrace();
            System.out.println("total successs : "+totalSuccess);
            //if (transaction != null){
            //    transaction.rollback();
           // }

        } finally {
            session.close();
        }

    }
*/
    public List getAllRepoDescription(){
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT owner_name, name, description FROM tbl_repo");
        List list = query.list();
        session.close();
        return list;
    }
}
