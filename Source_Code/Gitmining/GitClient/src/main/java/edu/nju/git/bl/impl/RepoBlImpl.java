package edu.nju.git.bl.impl;

import edu.nju.git.VO.*;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.data.factory.impl.DataFactory;
import edu.nju.git.data.factory.service.DataFactoryService;
import edu.nju.git.data.service.RepoDataService;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link RepoBlService} interface and provide ui module with basic support.
 * <p>The class todo
 * @author benchaodong
 * @date 2016-03-04
 */
public class RepoBlImpl implements RepoBlService {

    /**
     * The reference pointed to the only instance of this class because this class is set to be a singleton.
     */
    private static RepoBlImpl uniqueInstance = null;

    /**
     * default page capacity, namely how many items of search results one page can show.
     */
    private final int DEFAULT_PAGE_CAPACITY = 10;

    /**
     * the data service which this class uses to get data
     */
    private RepoDataService repoDataService;

    /**
     * This list stores the search result of {@code getSearchResult}. <p>
     * Each element in this list contains brief information of a repository.
     */
    private List<RepoBriefVO> briefRepoList;

    /**
     * This static method returns the reference to the only instance of this class.<p>
     * Other class can get an instance of this class only by this method.
     * @return the instance of this class.
     */
    public static RepoBlImpl instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new RepoBlImpl();
        }
        return uniqueInstance;
    }

    /**
     * The constructor is designed to be private in order to set this class to singleton. <p>
     *
     * The constructor also initialize some variables of this class.
     */
    private RepoBlImpl() {
        DataFactoryService dataFactoryService = DataFactory.instance();
        repoDataService = dataFactoryService.getRepoDataService();
        briefRepoList = new ArrayList<RepoBriefVO>();
    }



    @Override
    public List<RepoBriefVO> getSearchResult(String keyword) {
        return null;
    }

    @Override
    public RepoVO getRepoBasicInfo(String owner, String repoName) {
        return null;
    }

    @Override
    public List<UserBriefVO> getRepoContributor(String owner, String repoName) {
        return null;
    }

    @Override
    public List<UserBriefVO> getRepoCollaborator(String owner, String repoName) {
        return null;
    }

    @Override
    public List<BranchVO> getRepoBranch(String owner, String repoName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getRepoFork(String owner, String repoName) {
        return null;
    }

    @Override
    public List<CommitVO> getRepoCommit(String owner, String repoName) {
        return null;
    }

    @Override
    public List<IssueVO> getRepoIssue(String owner, String repoName) {
        return null;
    }
}
