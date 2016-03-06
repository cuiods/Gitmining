package edu.nju.git.data.service;

import java.util.List;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;

/**
 * The interface defines all the service that RepoData module must provide.
 * <p>
 * Repo data module gets data from outer api, and provide it to business logic module.
 * <p>
 *
 * @author benchaodong
 * @date 2016-03-03 14:20:49
 */
public interface RepoDataService {
    /**
     * Get repositories meeting demands of a search.
     * @param keyword :
     * 			full name <b>or</b> part of the name of a repository
     * @return List of {@link RepoBriefVO}:
     * 			brief info of a repository
     */
    public List<RepoBriefVO> getSearchResult(String keyword);

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
     * Get {@code List} of repositories which fork the project.
     * @param owner
     * 			name of the owner of the repository
     * @param repoName
     * 			name of the repository
     * @return list of {@link RepoBriefVO}
     * 			list of brief info of repositories.
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
