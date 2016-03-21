package org.GitServer.dataread;

import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.VO.MyChartVO;

/**
 * read local data form txt file.
 * @author daixinyan
 * @date 2016-03-20
 */
public class ReaderAndCount {

	private List<RepoBriefPO> nameOrderRepoPOs;
    private List<RepoBriefPO> starOrderRepoPOs;
    private List<RepoBriefPO> forkOrderRepoPOs;
    private List<RepoBriefPO> subscrOrderRepoPOs;
    private List<RepoBriefPO> updateOrderRepoPOs;
    
    private List<UserBriefPO> nameOrderUsers;
    private List<UserBriefPO> followerOrderUsers;
    private List<UserBriefPO> repoNumOrderUsers;
	
	private Map<String, RepoBriefPO>  nameToRepo ;
	private Map<String, UserBriefPO>  nameToUser ;
	
	private Map<String, List<String>> userToOwnerRepo ;
	private Map<String, List<String>> userToCollabRepo ;
	private Map<String, List<String>> userToContribute ;
	private Map<String, List<String>> userToSubscribeRepo ;
	
	private Map<String, List<String>> repoToContributor ;
	private Map<String, List<String>> repoToCollab ;
	private Map<String, List<String>> repoToSubscriber;
	
	
	private MyChartVO language;
	private MyChartVO createTime;
	private MyChartVO contributors;
	private MyChartVO collabrators;
	private MyChartVO size;
	
	private MyChartVO statUserType;  
	private MyChartVO statCompanyUser;
	private MyChartVO statUserBlog;
	private MyChartVO statUserLocation;
	private MyChartVO statUserEmail;
	private MyChartVO statUserCreateTime;
	private MyChartVO statUserOwnRepo;
	private MyChartVO statUserGist;
	private MyChartVO statUserFolllowers;
	
	private MyChartVO statOrganizationUser;
	private MyChartVO statOrganizationRepo;
	
	public ReaderAndCount(){
		this.nameOrderRepoPOs = new RepoBriefReader().readRepos();
		this.nameOrderUsers = new UserBriefReader().readUsers();
	}
	
	
	public List<RepoBriefPO> getNameOrderRepoPOs() {
		return nameOrderRepoPOs;
	}
	public List<RepoBriefPO> getStarOrderRepoPOs() {
		return starOrderRepoPOs;
	}
	public List<RepoBriefPO> getForkOrderRepoPOs() {
		return forkOrderRepoPOs;
	}
	public List<RepoBriefPO> getSubscrOrderRepoPOs() {
		return subscrOrderRepoPOs;
	}
	public List<RepoBriefPO> getUpdateOrderRepoPOs() {
		return updateOrderRepoPOs;
	}
	public List<UserBriefPO> getNameOrderUsers() {
		return nameOrderUsers;
	}
	public List<UserBriefPO> getFollowerOrderUsers() {
		return followerOrderUsers;
	}
	public List<UserBriefPO> getRepoNumOrderUsers() {
		return repoNumOrderUsers;
	}
	public Map<String, RepoBriefPO> getNameToRepo() {
		return nameToRepo;
	}
	public Map<String, UserBriefPO> getNameToUser() {
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
	public MyChartVO getLanguage() {
		return language;
	}
	public MyChartVO getCreateTime() {
		return createTime;
	}
	public MyChartVO getContributors() {
		return contributors;
	}
	public MyChartVO getCollabrators() {
		return collabrators;
	}
	public MyChartVO getSize() {
		return size;
	}
	public MyChartVO getStatUserType() {
		return statUserType;
	}
	public MyChartVO getStatCompanyUser() {
		return statCompanyUser;
	}
	public MyChartVO getStatUserBlog() {
		return statUserBlog;
	}
	public MyChartVO getStatUserLocation() {
		return statUserLocation;
	}
	public MyChartVO getStatUserEmail() {
		return statUserEmail;
	}
	public MyChartVO getStatUserCreateTime() {
		return statUserCreateTime;
	}
	public MyChartVO getStatUserOwnRepo() {
		return statUserOwnRepo;
	}
	public MyChartVO getStatUserGist() {
		return statUserGist;
	}
	public MyChartVO getStatUserFolllowers() {
		return statUserFolllowers;
	}
	public MyChartVO getStatOrganizationUser() {
		return statOrganizationUser;
	}
	public MyChartVO getStatOrganizationRepo() {
		return statOrganizationRepo;
	}

	
}
