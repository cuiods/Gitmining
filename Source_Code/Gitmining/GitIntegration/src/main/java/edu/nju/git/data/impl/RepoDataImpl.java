package edu.nju.git.data.impl;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.VO.*;
import edu.nju.git.data.factory.impl.POcreator;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.exception.NoSearchResultException;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public List<RepoBriefVO> getSearchResult(String regex) throws NoSearchResultException {
        List<RepoBriefVO> resultList = new ArrayList<RepoBriefVO>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        Iterator<RepoBriefPO> itr = nameOrderPOs.iterator();
        while(itr.hasNext()){
            RepoBriefPO po = itr.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(po.getOwner());
            stringBuilder.append("/");
            stringBuilder.append(po.getName());
            matcher = pattern.matcher(stringBuilder.toString());
            if (matcher.find()) {
                resultList.add(POVOConverter.convert(po));
            }
        }
        if (resultList.isEmpty()) {
            throw new NoSearchResultException("there is no result to show");
        }
        return resultList;
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

    /**
     * a creator to create po/vo object
     */


    private POcreator creator = POcreator.getInstance();
    @Override
    public RepoVO getRepoBasicInfo(String owner, String repoName) {
        return creator.getRepoPO(owner, repoName);
    }

    private List<UserBriefVO> convertObjectToUserBreif(List<Object> objects){
    	List<UserBriefVO> userBriefVOs = new ArrayList<UserBriefVO>();
    	for (Object object : objects) {
			userBriefVOs.add(creator.getUserBriefPO(object.toString()));
		}
    	return userBriefVOs;
    }
    
    @Override
    public List<UserBriefVO> getRepoContributor(String owner, String repoName) {
    	List<Object> objects = creator.getContributors(owner, repoName);
        return convertObjectToUserBreif(objects);
    }

    @Override
    public List<UserBriefVO> getRepoCollaborator(String owner, String repoName) {
    	List<Object> objects = creator.getCollaborators(owner, repoName);
        return convertObjectToUserBreif(objects);
    }

    @Override
    public List<BranchVO> getRepoBranch(String owner, String repoName) {
        List<Object> objects = creator.getBranches(owner, repoName);
        List<BranchVO> branchVOs = new ArrayList<BranchVO>();
        for (Object object : objects) {
			branchVOs.add(creator.getBranchPO(owner, repoName, object.toString()));
		}
    	return branchVOs;
    }

    @Override
    public List<RepoBriefVO> getRepoFork(String owner, String repoName) {
        List<Object> objects = creator.getForks(owner, repoName);
        List<RepoBriefVO> repoBriefVOs = new ArrayList<RepoBriefVO>();
        for (Object object : objects) {
			repoBriefVOs.add(creator.getRepoBriefPO(object.toString()) );
		}
    	return repoBriefVOs;
    }

    @Override
    public List<CommitVO> getRepoCommit(String owner, String repoName) {
        List<Object> objects = creator.getCommits(owner, repoName);
        List<CommitVO> commitVOs = new ArrayList<CommitVO>();
        for (Object object : objects) {
			commitVOs.add(creator.getCommitPO(owner, repoName, object.toString()));
		}
    	return commitVOs;
    }

    @Override
    public List<IssueVO> getRepoIssue(String owner, String repoName) {
        List<Object> objects = creator.getIssues(owner, repoName);
        List<IssueVO> issueVOs = new ArrayList<IssueVO>();
        for (Object object : objects) {
			issueVOs.add(creator.getIssue(owner, repoName, object.toString()));
		}
    	return issueVOs;
    }
}
