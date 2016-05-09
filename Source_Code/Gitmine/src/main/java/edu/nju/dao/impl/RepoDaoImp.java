package edu.nju.dao.impl;

import edu.nju.common.SortType;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
        return null;
    }

    /**
     * get total count of repository
     *
     * @return number of repository
     */
    public int getTotalCount() {
        return 0;
    }

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     *
     * @param sortType which type of list to get
     * @return the reference to the list
     */
    public List<TblRepo> getRepos(SortType sortType) {
        return null;
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
        return null;
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
        return null;
    }

    /**
     * Get brief info of collaborators.
     *
     * @param owner    name of the owner of the repository
     * @param repoName name of the repository
     * @return list of brief info of collaborators.
     */
    public List<String> getRepoCollaborator(String owner, String repoName) {
        return null;
    }

    /**
     * get all subscribers' names of the given repo
     *
     * @param owner
     * @param repoName
     * @return
     */
    public List<String> getRepoSubscriber(String owner, String repoName) {
        return null;
    }
}
