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
            case Repo_Colla:hql+="order by numCollaborator ";break;
            case Repo_Update:hql+="order by updateAt ";break;
            default:hql+="order by name ";break;
        }
        hql += isDesc?"desc":"asc";
        Query query = getSession().createQuery(hql);
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
        return query.list();
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
     * @deprecated
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
    public List<TblRepo> getRepos(SortType sortType,boolean isDesc, int offset, int maxNum) {
        String[] sort = {"name","numStar","numFork","numSubscriber","numContributor","numCollaborator","updateAt"};
        Session session = getSession();
        Query query = null;
        String order = isDesc?"desc":"asc";
        switch (sortType) {
            case Repo_Star:query = getSession().createQuery("from TblRepo order by numStar "+order);break;
            case Repo_Fork:query = getSession().createQuery("from TblRepo order by numFork "+order);break;
            case Repo_Subcri:query = getSession().createQuery("from TblRepo order by numSubscriber "+order);break;
            case Repo_Contri:query = getSession().createQuery("from TblRepo order by numContributor "+order);break;
            case Repo_Colla:query = getSession().createQuery("from TblRepo order by numCollaborator "+order);break;
            case Repo_Update:query = getSession().createQuery("from TblRepo order by updateAt "+order);break;
            default:query = getSession().createQuery("from TblRepo order by name "+order);break;
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
