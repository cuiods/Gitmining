package edu.nju.git.bl.service;

import java.util.List;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

/**
 * The {@code RepoBlService} defines all the service that Repo module must provide.
 * <p>
 * Repo business module gets data from Repo Integration module, and provide<br> 
 * view object for presentation.
 * <p>
 * {@code RepoBlService} can used to search repositories, or search for <b>DETAILED</b><br>
 * information of a single repository.
 * @author cuihao
 * @date 2016-03-02 13:20:49
 */
public interface RepoBlService {
	/**
	 * Get repositories meeting demands of a search.
	 * @param keyword : 
	 * 			full name <b>or</b> part of the name of a repository
	 * @return List of {@link RepoBriefVO}: 
	 * 			brief info of a repository
	 */
	public List<RepoBriefVO> getSearchResult(String keyword);

	/**
	 * jump to the specific page.
	 * @param pageNum page number
	 * @return the list in this page
	 * @throws PageOutOfBoundException
	 */
	public List<RepoBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException;

	/**
	 * jump to next page of result list
	 * @return list of repository brief information
	 * @throws PageOutOfBoundException
	 */
	public List<RepoBriefVO> nextPage() throws PageOutOfBoundException;

	/**
	 * jump to the previous page
	 * @return list of repository brief information
	 * @throws PageOutOfBoundException
	 */
	public List<RepoBriefVO> previousPage() throws PageOutOfBoundException;

	/**
	 * sort the list in specific aspect defined by <tt>sortType</tt>
	 * @param sortType in which way sort
	 * @return
	 */
	default List<RepoBriefVO> sort(SortType sortType) {
		return sort(sortType, true);
	}

	/**
	 * sort the list in specific aspect defined by <tt>sortType</tt>
	 * @param sortType in which way sort
	 * @param reverse if the order is reversed
	 * @return
	 */
	public List<RepoBriefVO> sort(SortType sortType, boolean reverse);

	/**
	 * Get <b>detailed</b> info of a repository.
	 * @param owner 
	 * 			name of owner of the repository
	 * @param repoName 
	 * 			name of the repository
	 * @return {@link RepoVO}: 
	 * 			detailed info of a repository
	 */
	public RepoVO getRepoBasicInfo(String owner, String repoName);
	
	/**
	 * Get brief info of contributors.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link UserBriefVO}
	 * 			list of brief info of contributors.
	 */
	public List<String> getRepoContributor(String owner, String repoName);
	
	/**
	 * Get brief info of collaborators.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link UserBriefVO}
	 * 			list of brief info of collaborators.
	 */
	public List<String> getRepoCollaborator(String owner, String repoName);


	//public List<BranchVO> getRepoBranch(String owner, String repoName);

	//public List<RepoBriefVO> getRepoFork(String owner, String repoName);

	//public List<CommitVO> getRepoCommit(String owner, String repoName);

	//public List<IssueVO> getRepoIssue(String owner, String repoName);


	/**
	 * return the brief list.
	 *
	 * @return brief repository list.
	 */
	public List<RepoBriefVO> getShownRepoList();

	/**
	 * get the page number that is being viewed.
	 * @return page number
	 */
	public int getCurrentPage();

	/**
	 * get total page number of result list.
	 * <p>Notify that in casual model the retrn value is meaningless.
	 * @return total page number
	 */
	public int getTotalPage();

}
