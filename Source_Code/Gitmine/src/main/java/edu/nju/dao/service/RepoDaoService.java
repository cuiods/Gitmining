package edu.nju.dao.service;

import edu.nju.common.SortType;
import edu.nju.entity.RepoLabel;
import edu.nju.entity.TblRepo;

import java.util.List;

/**
 * data access object service of repository
 * @author cuihao
 */
public interface RepoDaoService {
    /**
     * search repos by keyword.
     * @param keyword
     * @return list of repositorys
     */
    public List<TblRepo> getSearchResult(String keyword);

    /**
     * get total count of repository
     * @return number of repository
     */
    public long getTotalCount();

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     * @param sortType which type of list to get
     * @return the reference to the list
     */
    public List<TblRepo> getRepos(SortType sortType);


    /**
     * Get <b>detailed</b> info of a repository.
     * @param owner
     * 			name of owner of the repository
     * @param repoName
     * 			name of the repository
     * @return {@link TblRepo}:
     * 			detailed info of a repository
     */
    public TblRepo getRepoBasicInfo(String owner, String repoName);

    /**
     * Get brief info of contributors.
     * @param owner
     * 			name of the owner of the repository
     * @param repoName
     * 			name of the repository
     * @return list of {@link String}
     * 			list of name of contributors.
     * 			The return value will be null if the is no such repository.
     */
    public List<String> getRepoContributor(String owner, String repoName);

    /**
     * Get brief info of collaborators.
     * @param owner
     * 			name of the owner of the repository
     * @param repoName
     * 			name of the repository
     * @return
     * 			list of brief info of collaborators.
     */
    public List<String> getRepoCollaborator(String owner, String repoName);

    /**
     * get all subscribers' names of the given repo
     * @param owner
     * @param repoName
     * @return
     */
    public List<String> getRepoSubscriber(String owner, String repoName);

    /**
     * get repository label
     * @param repoOwner
     * @param repoName
     * @return RepoLabel
     */
    public RepoLabel getRepoInterest(String repoOwner, String repoName);

}
