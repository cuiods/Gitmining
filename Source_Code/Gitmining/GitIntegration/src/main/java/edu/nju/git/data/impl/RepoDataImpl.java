package edu.nju.git.data.impl;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.VO.*;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * This is the implementation of {@link RepoDataService}, the class get data from outer api (but in <br>
 * iteration 2 or 3 we may change our strategy and get data from database.<p>
 * @author benchaodong
 * @date 2016-03-04
 */
public class RepoDataImpl implements RepoDataService {

    /**
     * the singleton reference pointed to the instance of this class.
     */
    private static RepoDataImpl uniqueInstance = null;

    /**
     * the index of <b>ALL</b> repositories. Each element in the list store one repository's brief information.
     */
    private List<RepoBriefPO> repoIndex;

    /**
     * Singleton method.
     * @return the singleton reference pointed to the instance of this class.
     */
    public static RepoDataImpl instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new RepoDataImpl();
        }
        return uniqueInstance;
    }

    /**
     * private constructor
     */
    private RepoDataImpl(){
        loadRepoIndex();
    }

    private List<RepoBriefPO> nameOrderPOs;

    private List<RepoBriefPO> starOrderPOs;

    private List<RepoBriefPO> forkOrderPOs;

    private List<RepoBriefPO> subscrOrderPOs;

    private List<RepoBriefPO> updateOrderPOs;

    /**
     * This method load the repo index list {@code repoIndex} from disk.
     * <p>todo
     */
    private void loadRepoIndex() {

    }

    /**
     * This method save the content of list <tt>repoIndex</tt> to disk file so that it can be read<br>
     * next time the system launches.
     * todo
     */
    private void saveRepoIndex() {

    }

    @Override
    public List<RepoBriefVO> getSearchResult(String regex) {
        return null;
    }

    @Override
    public int getTotalCount() {
        return nameOrderPOs.size();
    }

    @Override
    public List<RepoBriefPO> getRepoBriefPOs(SortType sortType) {
        switch (sortType) {
            case REPO_NAME:return nameOrderPOs;
            case STAR_NUM:return starOrderPOs;
            case FORK_NUM:return forkOrderPOs;
            case SUBSCR_NUM:return subscrOrderPOs;
            case UPDATE_TIME:return updateOrderPOs;
            default:return null;
        }
    }

    @Override
    public List<RepoBriefVO> acceptVisitor(RepoVisitor visitor) {
        return visitor.visit(this);
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
