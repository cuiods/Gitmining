package edu.nju.git.bl.service;

import java.util.List;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;

/**
 * The {@code RepoBlService} defines all the service that Repo module must provide.
 * <p>
 * Repo business module gets data from Repo Integration module, and provide<br> 
 * view object for presentation.
 * <p>
 * {@code RepoBlService} can used to search repositorys, or search for <b>DETAILED</b><br>
 * information of a single repository.
 * @author cuihao
 * @date 2016-03-02 13:20:49
 */
public interface RepoBlService extends BlService{
	/**
	 * Get repositorys meeting demands of a search.
	 * @param keyword : 
	 * 			full name <b>or</b> part of the name of a repository
	 * @return List of {@link RepoBriefVO}: 
	 * 			brief info of a repository
	 */
	public List<RepoBriefVO> getSearchResult(String keyword);
	
	/**
	 * Get <b>detailed</b> info of a repositry.
	 * @param owner 
	 * 			name of owner of the repository
	 * @param repoName 
	 * 			name of the repository
	 * @return {@link RepoVO}: 
	 * 			detailed info of a repository
	 */
	public RepoVO getRepoBasicInfo(String owner, String repoName);
	
	/**
	 * Get detailed info of the owner
	 * @param repoName 
	 * 			name of repository
	 * @param ownerName 
	 * 			name of owner
	 * @return {@link UserVO} 
	 * 			detailed info of a owner.
	 */
	public UserVO getRepoOwner (String repoName, String ownerName);
	
	/**
	 * Get brief info of contributors.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link UserBriefVO}
	 * 			list of brief info of contributors.
	 */
	public List<UserBriefVO> getRepoContributor(String owner, String repoName);
	
	/**
	 * Get brief info of collaborators.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link UserBriefVO}
	 * 			list of brief info of collaborators.
	 */
	public List<UserBriefVO> getRepoCollaborator(String owner, String repoName);
	
	/**
	 * Get {@code List} of info of branches.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link BranchVO}
	 * 			list of info of branches.
	 */
	public List<BranchVO> getRepoBranch(String owner, String repoName);
	
	/**
	 * Get {@code List} of repositorys which fork the project.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link RepoBriefVO}
	 * 			list of brief info of repositorys.
	 */
	public List<RepoBriefVO> getRepoFork(String owner, String repoName);
	
	/**
	 * Get {@code List} of commit info of the repository.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link CommitVO}
	 * 			list of info of commits.
	 */
	public List<CommitVO> getRepoCommit(String owner, String repoName);
	
	/**
	 * Get {@code List} of issues of the repository.
	 * @param owner
	 * 			name of the owner of the repository
	 * @param repoName
	 * 			name of the repository
	 * @return list of {@link IssueVO}
	 * 			list of info of issues.
	 */
	public List<IssueVO> getRepoIssue(String owner, String repoName);
}
