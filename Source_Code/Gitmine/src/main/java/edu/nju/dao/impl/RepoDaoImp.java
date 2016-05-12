package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    /**
     * search repos by keyword.
     *
     * @param keyword
     * @return list of repositorys
     */
    public List<TblRepo> getSearchResult(String keyword) {
        Query query = getSession().createQuery("from TblRepo as u where u.name like %?%");
        query.setString(0, keyword);
        List<TblRepo> repos = query.list();
        return repos;
    }

    public List<TblRepo> getSearchResult(String keyword, int offset, int maxNum, SortType type,
                                         String filterType, String language, Calendar createYear) {
        return null;
    }

    /**
     * get total count of repository
     *
     * @return number of repository
     */
    public long getTotalCount() {
        Query query = getSession().createQuery("select count(*) from TblRepo");
        return (Long) query.list().get(0);
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param sortType which type of list to get
     * @return the reference to the list
     */
    public List<TblRepo> getRepos(SortType sortType) {
        String[] sort = {"name","numStar","numFork","numSubscriber","numContributor","numCollaborator","updateAt"};
        Query query = getSession().createQuery("from TblRepo order by ?");
        query.setString(0,sort[sortType.ordinal()-SortType.Repo_Name.ordinal()]);
        return query.list();
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param offset
     * @param maxNum
     * @return the reference to the list
     */
    public List<TblRepo> getRepos(int offset, int maxNum) {
        Query query = getSession().createQuery("from TblRepo");
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        return query.list();
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<TblRepo> getRepos(SortType sortType, int offset, int maxNum) {
        String[] sort = {"name","numStar","numFork","numSubscriber","numContributor","numCollaborator","updateAt"};
        Session session = getSession();
        Query query = null;
        switch (sortType) {
            case Repo_Star:query = getSession().createQuery("from TblRepo order by numStar desc");break;
            case Repo_Fork:query = getSession().createQuery("from TblRepo order by numFork desc ");break;
            case Repo_Subcri:query = getSession().createQuery("from TblRepo order by numSubscriber desc");break;
            case Repo_Contri:query = getSession().createQuery("from TblRepo order by numContributor desc ");break;
            case Repo_Colla:query = getSession().createQuery("from TblRepo order by numCollaborator desc ");break;
            case Repo_Update:query = getSession().createQuery("from TblRepo order by updateAt desc ");break;
            default:query = getSession().createQuery("from TblRepo order by name desc");break;
        }
        query.setFirstResult(offset);
        query.setMaxResults(maxNum);
        return query.list();
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
        Query query = getSession().createQuery("from TblRepo where ownerName=? and name=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblRepo> repos = query.list();
        if (repos.size()>0) {
            return repos.get(0);
        }
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
        Query query = getSession().createQuery("from TblContributor where ownerName=? and repo=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblContributor> contributors = query.list();
        List<String> list = new ArrayList<String>(contributors.size());
        for(int i = 0; i < contributors.size(); i++) {
            list.add(contributors.get(i).getContributor());
        }
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
        Query query = getSession().createQuery("from TblCollabrator where repoOwner=? and repo=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblCollabrator> collabrators = query.list();
        List<String> list = new ArrayList<String>(collabrators.size());
        for(int i = 0; i < collabrators.size(); i++) {
            list.add(collabrators.get(i).getCollabrator());
        }
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
        Query query = getSession().createQuery("from TblSubscriber where repoOwner=? and repo=?");
        query.setString(0,owner);
        query.setString(1,repoName);
        List<TblSubscriber> subscriber = query.list();
        List<String> list = new ArrayList<String>(subscriber.size());
        for(int i = 0; i < subscriber.size(); i++) {
            list.add(subscriber.get(i).getSubscriber());
        }
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
        RepoLabel repoLabel = null;
        Query query = getSession().createQuery("from RepoLabel where repoOwner=? and repo=?");
        query.setString(0,repoOwner);
        query.setString(1,repoName);
        List<RepoLabel> repoLabels = query.list();
        if (repoLabels.size()>0) {
            return repoLabels.get(0);
        }
        return repoLabel;
    }

    /**
     * save repository interest label.
     *
     * @param repoLabel
     * @return is succeed.
     */
    public boolean saveRepoInterest(RepoLabel repoLabel) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(repoLabel);
        session.flush();
        transaction.commit();
        return true;
    }
}
