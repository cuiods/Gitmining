package org.GitServer.dataread;

import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.VO.MyChartVO;

/**
 * read local data form txt file.
 * @author daixinyan
 * @date 2016-03-20
 */
public class ReaderAndCount {

	private static ReaderAndCount uniqueInstance = null;

	public static ReaderAndCount instance(){
		if (uniqueInstance == null) {
			uniqueInstance = new ReaderAndCount();
		}
		return uniqueInstance;
	}

	private ReaderAndCount() {}

	private List<RepoPO> nameOrderRepoPOs;
	private List<RepoPO> starOrderRepoPOs;
	private List<RepoPO> forkOrderRepoPOs;
	private List<RepoPO> subscrOrderRepoPOs;
    private List<RepoPO> updateOrderRepoPOs;

	private List<UserPO> nameOrderUsers;
	private List<UserPO> followerOrderUsers;
    private List<UserPO> repoNumOrderUsers;

	private Map<String, RepoPO>  nameToRepo ;
	private Map<String, UserPO>  nameToUser ;

	private Map<String, List<String>> userToOwnerRepo ;
	private Map<String, List<String>> userToCollabRepo ;
	private Map<String, List<String>> userToContribute ;
	private Map<String, List<String>> userToSubscribeRepo ;

	private Map<String, List<String>> repoToContributor ;
	private Map<String, List<String>> repoToCollab ;
	private Map<String, List<String>> repoToSubscriber;
	private Map<String, List<String>> repoToCommit;
	private Map<String, List<String>> repoToIssue;
	private Map<String, List<String>> repoToPull;

	private MyChartVO repoCreateTime;	//for create time bar chart
	private MyChartVO repoSize;
	private MyChartVO forkCount;	//for fork times of a repo bar chart
	private MyChartVO starCount;	//for star number of a repo bar chart
	private MyChartVO repoContributors;//number of repo's contributors
	private MyChartVO repoCollabrators;//
	private MyChartVO repoSubscribers;

	private MyChartVO language;		//for language pie chart
	private MyChartVO statUserType;  //for user type pie chart
	private MyChartVO statUserCreateTime;	//for user create time  line chart
	private MyChartVO statUserOwnRepo;	//for the number of user owns repos bar chart
	private MyChartVO statUserCollaborateRepo;//for the number of user collaborate repos bar chart
	private MyChartVO statUserContributorRepo;
	private MyChartVO statUserSubscriberRepo;
	private MyChartVO statUserGist;
	private MyChartVO statCompanyUser;	//for how many users a company has bar chart
	private MyChartVO statUserEmail;
	private MyChartVO statUserFolllowers;

	public List<RepoPO> getNameOrderRepoPOs() {
		return nameOrderRepoPOs;
	}
	public List<RepoPO> getStarOrderRepoPOs() {
		return starOrderRepoPOs;
	}
	public List<RepoPO> getForkOrderRepoPOs() {
		return forkOrderRepoPOs;
	}
	public List<RepoPO> getSubscrOrderRepoPOs() {
		return subscrOrderRepoPOs;
	}
	public List<RepoPO> getUpdateOrderRepoPOs() {
		return updateOrderRepoPOs;
	}
	public List<UserPO> getNameOrderUsers() {
		return nameOrderUsers;
	}
	public List<UserPO> getFollowerOrderUsers() {
		return followerOrderUsers;
	}
	public List<UserPO> getRepoNumOrderUsers() {
		return repoNumOrderUsers;
	}
	public Map<String, RepoPO> getNameToRepo() {
		return nameToRepo;
	}
	public Map<String, UserPO> getNameToUser() {
		return nameToUser;
	}
	public Map<String, List<String>> getUserToOwnerRepo() {
		return userToOwnerRepo;
	}
	public Map<String, List<String>> getUserToCollabRepo() {
		return userToCollabRepo;
	}
	public Map<String, List<String>> getUserToContribute() {
		return userToContribute;
	}
	public Map<String, List<String>> getUserToSubscribeRepo() {
		return userToSubscribeRepo;
	}
	public Map<String, List<String>> getRepoToContributor() {
		return repoToContributor;
	}
	public Map<String, List<String>> getRepoToCollab() {
		return repoToCollab;
	}
	public Map<String, List<String>> getRepoToSubscriber() {
		return repoToSubscriber;
	}
	public Map<String, List<String>> getRepoToCommit() {
		return repoToCommit;
	}
	public Map<String, List<String>> getRepoToIssue() {
		return repoToIssue;
	}
	public Map<String, List<String>> getRepoToPull() {
		return repoToPull;
	}
	public MyChartVO getRepoCreateTime() {
		return repoCreateTime;
	}

	public MyChartVO getRepoSubscribers() {
		return repoSubscribers;
	}

	public MyChartVO getForkCount() {
		return forkCount;
	}
	public MyChartVO getStarCount() {
		return starCount;
	}
	public MyChartVO getLanguage() {
		return language;
	}
	public MyChartVO getStatUserType() {
		return statUserType;
	}
	public MyChartVO getStatUserCreateTime() {
		return statUserCreateTime;
	}
	public MyChartVO getStatUserCollaborateRepo() {
		return statUserCollaborateRepo;
	}
	public MyChartVO getStatUserContributorRepo() {
		return statUserContributorRepo;
	}
	public MyChartVO getStatUserSubscriberRepo() {
		return statUserSubscriberRepo;
	}
	public MyChartVO getStatUserOwnRepo() {
		return statUserOwnRepo;
	}
	public MyChartVO getStatCompanyUser() {
		return statCompanyUser;
	}
	public MyChartVO getRepoContributors() {
		return repoContributors;
	}
	public MyChartVO getRepoCollabrators() {
		return repoCollabrators;
	}
	public MyChartVO getRepoSize() {
		return repoSize;
	}
	public MyChartVO getStatUserEmail() {
		return statUserEmail;
	}
	public MyChartVO getStatUserGist() {
		return statUserGist;
	}
	public MyChartVO getStatUserFolllowers() {
		return statUserFolllowers;
	}
	

	
	

	
}
