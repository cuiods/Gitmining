package edu.nju.git.data.factory.impl;

import java.util.List;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.data.api.centralization.RepoMapReader;
import edu.nju.git.data.api.centralization.UserMapReader;
import edu.nju.git.data.api.decentralization.BranchItemreader;
import edu.nju.git.data.api.decentralization.CommitItemReader;
import edu.nju.git.data.api.decentralization.IssueItemReader;
import edu.nju.git.data.api.liststring.BranchesListReader;
import edu.nju.git.data.api.liststring.CollaboratorsListReader;
import edu.nju.git.data.api.liststring.CommitsListReader;
import edu.nju.git.data.api.liststring.ContributorsListReader;
import edu.nju.git.data.api.liststring.ForksListReader;
import edu.nju.git.data.api.liststring.IssuesListReader;
import edu.nju.git.data.api.liststring.RepositoriesListReader;
import edu.nju.git.data.api.liststring.StargazersListReader;
import edu.nju.git.data.api.liststring.SubscribersListReader;
import edu.nju.git.data.factory.impl.POfactory.AbstractFieldsGetter;
import edu.nju.git.data.factory.impl.POfactory.BranchPOfactory;
import edu.nju.git.data.factory.impl.POfactory.CommitPOfactory;
import edu.nju.git.data.factory.impl.POfactory.IssuePOfactory;
import edu.nju.git.data.factory.impl.POfactory.RepoBriefPOfactory;
import edu.nju.git.data.factory.impl.POfactory.RepoPOfactory;
import edu.nju.git.data.factory.impl.POfactory.UserBriefPOfactory;
import edu.nju.git.data.factory.impl.POfactory.UserPOfactory;

/**
 * the encapsulated class to creat po/vo object.
 * <br/>some method named with ge**PO ,but return a VO.
 * @author daixinyan
 * @date 2016-03-09
 */
public class POcreator {

	private POcreator() {}
	
	/**
	 * singleton
	 */
	private static POcreator instance;
	private static synchronized void createInstance(){
		if(instance ==null){ instance = new POcreator(); }
	}
	/**
	 * 
	 * <br/><b>precondition</b>：no precondition
	 * <br/><b>postcondition</b>：return a singleton
	 * @date 2016-03-09
	 */
	public static POcreator getInstance(){
		if(instance==null){ createInstance(); }
		return instance;
	}
	
	public UserVO getUserPO(String name){
		AbstractFieldsGetter getter = new UserMapReader(name);
//		AbstractFieldsGetter getter = new UserItemReader(name);
		UserPOfactory pofactory = new UserPOfactory(getter);
		return pofactory.getPO();
	}

	public UserBriefVO getUserBriefPO(String name){
		AbstractFieldsGetter getter = new UserMapReader(name);
//		AbstractFieldsGetter getter = new UserItemReader(name);
		UserBriefPOfactory pofactory = new UserBriefPOfactory(getter);
		return pofactory.getPO();
	}
	
	
	public RepoBriefVO getRepoBriefPO(String fullname){
		AbstractFieldsGetter getter = new RepoMapReader(fullname);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoBriefPOfactory pOfactory = new RepoBriefPOfactory(getter);
		return pOfactory.getPO();
		
	}
	
	
	public RepoBriefVO getRepoBriefPO(String owner,String name){
		return this.getRepoBriefPO(owner+"/"+name);
	}
	
	
	public RepoVO getRepoPO(String fullname){
		AbstractFieldsGetter getter = new RepoMapReader(fullname);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoPOfactory pOfactory = new RepoPOfactory(getter);
		return pOfactory.getPO();
	}
	
	
	public RepoVO getRepoPO(String owner,String name){
		return this.getRepoPO(owner+"/"+name);
	}
	
	
	public IssueVO getIssue(String fullname,String sha){
		AbstractFieldsGetter getter = new IssueItemReader(fullname, sha);
		IssuePOfactory issuePOfactory = new IssuePOfactory(getter, sha);
		return issuePOfactory.getPO();
	}

	public IssueVO getIssue(String owner,String name,String sha){
		return this.getIssue(owner+"/"+name, sha);
	}
	
	public CommitVO getCommitPO(String fullname,String sha){
		AbstractFieldsGetter getter = new CommitItemReader(fullname, sha);
		CommitPOfactory commitPOfactory = new CommitPOfactory(getter,sha);
		return commitPOfactory.getPO();
	}
	
	public CommitVO getCommitPO(String owner,String name,String sha){
		return this.getCommitPO(owner+"/"+name, sha);
	}
	
	public BranchVO getBranchPO(String fullname, String sha){
		AbstractFieldsGetter getter = new BranchItemreader(fullname, sha);
		BranchPOfactory branchPOfactory = new BranchPOfactory(getter);
		return branchPOfactory.getPO();
	}
	
	public BranchVO getBranchPO(String owner,String name,String sha){
		return this.getBranchPO(owner+"/"+name, sha);
	}
	
	public List<Object> getBranches(String owner,String name){
		return this.getBranches(owner+"/"+name);
	}
	
	public List<Object> getBranches(String fullname){
		return new BranchesListReader(fullname).getNames();
	}
	
	public List<Object> getIssues(String owner,String name){
		return this.getIssues(owner+"/"+name);
	}
	
	public List<Object> getIssues(String fullname){
		return new IssuesListReader(fullname).getNames();
	}
	
	

	public List<Object> getForks(String owner,String name){
		return this.getIssues(owner+"/"+name);
	}
	
	public List<Object> getForks(String fullname){
		return new ForksListReader(fullname).getNames();
	}
	
	
	
	public List<Object> getCommits(String owner,String name){
		return this.getCommits(owner+"/"+name);
	}
	
	public List<Object> getCommits(String fullname){
		return new CommitsListReader(fullname).getNames();
	}
	
	public List<Object> getContributors(String fullname){
		return new ContributorsListReader(fullname).getNames();
	}
	public List<Object> getContributors(String owner,String name){
		return this.getContributors(owner+"/"+name);
	}
	
	public List<Object> getCollaborators(String fullname){
		return new CollaboratorsListReader(fullname).getNames();
	}
		
	public List<Object> getCollaborators(String owner,String name){
		return this.getCollaborators(owner+"/"+name);
	}
	
	public List<Object> getRepositories(){
		return new RepositoriesListReader().getNames();
	}
	
	public List<Object> getSubscribers(String fullname){
		return new SubscribersListReader(fullname).getNames();
	}
	
	public List<Object> getSubscribers(String owner,String name){
		return this.getSubscribers(owner+"/"+name);
	}
	
	public List<Object> getStars(String fullname){
		return new StargazersListReader(fullname).getNames();
	}
	
	public List<Object> getStars(String owner,String name){
		return this.getStars(owner+"/"+name);
	}
}
