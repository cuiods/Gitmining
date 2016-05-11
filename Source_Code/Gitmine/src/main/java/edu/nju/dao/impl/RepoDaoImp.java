package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblCollabrator;
import edu.nju.entity.TblContributor;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblSubscriber;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
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
}
