package org.GitServer.dataread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.VO.MyChartVO;
import edu.nju.git.comparators.repocomparators.po.*;
import edu.nju.git.comparators.usercomparators.po.UserPOFollowerComparator;
import edu.nju.git.comparators.usercomparators.po.UserPONameComparator;
import edu.nju.git.comparators.usercomparators.po.UserPORepoNumComparator;
import org.GitServer.cacheinit.DataEncapsulation;

/**
 * read local data form txt file.
 *
 * When the server is launched, it read all data from disk and compute some(actually very much!) second-hand data
 *
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

	private ReaderAndCount() {
		loadAllData();
	}

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

	/**
	 * this method load all data from disk.
	 * <p>
	 *     there are 8 lists variables in this class, but only two list is to be load from disk,<br>
	 *         other lists is resorted by this two list.
	 * </p>
	 * <p>
	 *     the map <tt>nameToRepo</tt> and map <tt>nameToUser</tt> is generated from list.
	 * </p>
	 * <p>
	 *     all the MyChartVOs are calculated by lists and maps loaded from disk.
	 * </p>
	 *
	 */
	public void loadAllData() {
		DataEncapsulation allData = new Reader().excute();

		this.nameOrderRepoPOs = allData.nameOrderRepoPOs;
		this.nameOrderUsers = allData.nameOrderUserPOs;


		this.userToOwnerRepo = allData.userToOwnerRepo;
		this.userToContribute = allData.userToContribute;
		this.userToCollabRepo = allData.userToCollabRepo;
		this.userToSubscribeRepo = allData.userToSubscribeRepo;

		this.repoToCollab = allData.repoToCollab;
		this.repoToContributor = allData.repoToContributor;
		this.repoToSubscriber = allData.repoToSubscriber;

		this.repoToCommit = allData.repoToCommit;
		this.repoToPull = allData.repoToPull;
		this.repoToIssue = allData.repoToIssue;

		setCounts();

		sortList();

		generateMap();




	}

	/**
	 * this method set num_contributors, num_collaborators, num_commits, num_pulls, num_issues of <br>
	 *     a RepoPO. set num_subscribe, num_contribute, num_collaborate, userValue.
	 * <p>
	 *     all the above value is counted each time the server launches.
	 * </p>
	 */
	private void setCounts() {
		for (RepoPO repoPO : nameOrderRepoPOs) {
			String repoID = repoPO.getOwnerName()+"/"+repoPO.getName();

			List<String> repoToContriList = repoToContributor.get(repoID);
			if (repoToContriList !=null) {
				repoPO.setNum_contrbutors(repoToContriList.size());
			}
			else { 		//there is no such repo in the map
				System.out.println("no element for "+repoID);
			}

			List<String> repoToCollaList = repoToCollab.get(repoID);
			if (repoToCollaList != null) {
				repoPO.setNum_collaborators(repoToCollaList.size());
			}
			else {
				System.out.println("no element for "+repoID);
			}

//			List<String> repoTo
			// TODO: 2016/3/24
		}
	}

	/**
	 * sort all list with different comparators
	 */
	private void sortList() {
		//sort repo list
		this.starOrderRepoPOs = (ArrayList<RepoPO>)((ArrayList)nameOrderRepoPOs).clone();
		starOrderRepoPOs.sort(new RepoPOStarComparator());
		this.forkOrderRepoPOs = (ArrayList<RepoPO>)((ArrayList)nameOrderRepoPOs).clone();
		forkOrderRepoPOs.sort(new RepoPOForkComparator());
		this.subscrOrderRepoPOs = (ArrayList<RepoPO>)((ArrayList)nameOrderRepoPOs).clone();
		subscrOrderRepoPOs.sort(new RepoPOSubscrComparator());
		this.updateOrderRepoPOs = (ArrayList<RepoPO>)((ArrayList)nameOrderRepoPOs).clone();
		updateOrderRepoPOs.sort(new RepoPOUpdateComparator());
		nameOrderRepoPOs.sort(new RepoPONameComparator());

		//sort user list
		this.followerOrderUsers = (ArrayList<UserPO>)((ArrayList)nameOrderUsers).clone();
		followerOrderUsers.sort(new UserPOFollowerComparator());
		this.repoNumOrderUsers = (ArrayList<UserPO>)((ArrayList)nameOrderUsers).clone();
		repoNumOrderUsers.sort(new UserPORepoNumComparator());
		nameOrderUsers.sort(new UserPONameComparator());
	}

	/**
	 * this method generate map from existing list
	 */
	private void generateMap(){
		StringBuilder builder = new StringBuilder();
		for (RepoPO repoPO: nameOrderRepoPOs) {
			builder.append(repoPO.getOwnerName());
			builder.append("/");
			builder.append(repoPO.getName());
			nameToRepo.put(builder.toString(), repoPO);
			builder.setLength(0);	//clear the string builder
		}

		for (UserPO userPO: nameOrderUsers) {
			nameToUser.put(userPO.getName(), userPO);
		}
	}


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
