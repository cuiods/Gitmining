package edu.nju.git.data.factory.impl;

import edu.nju.git.PO.BranchPO;
import edu.nju.git.PO.CommitPO;
import edu.nju.git.PO.IssuePO;
import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.data.api.centralization.RepoMapReader;
import edu.nju.git.data.api.centralization.UserMapReader;
import edu.nju.git.data.api.decentralization.BranchItemreader;
import edu.nju.git.data.api.decentralization.CommitItemReader;
import edu.nju.git.data.api.decentralization.IssueItemReader;
import edu.nju.git.data.factory.impl.POfactory.AbstractFieldsGetter;
import edu.nju.git.data.factory.impl.POfactory.BranchPOfactory;
import edu.nju.git.data.factory.impl.POfactory.CommitPOfactory;
import edu.nju.git.data.factory.impl.POfactory.IssuePOfactory;
import edu.nju.git.data.factory.impl.POfactory.RepoBriefPOfactory;
import edu.nju.git.data.factory.impl.POfactory.RepoPOfactory;
import edu.nju.git.data.factory.impl.POfactory.UserBriefPOfactory;
import edu.nju.git.data.factory.impl.POfactory.UserPOfactory;

public class POcreator {

	public POcreator() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPO getUserPO(String name){
		AbstractFieldsGetter getter = new UserMapReader(name);
//		AbstractFieldsGetter getter = new UserItemReader(name);
		UserPOfactory pofactory = new UserPOfactory(getter);
		return pofactory.getPO();
	}

	public UserBriefPO getUserBriefPO(String name){
		AbstractFieldsGetter getter = new UserMapReader(name);
//		AbstractFieldsGetter getter = new UserItemReader(name);
		UserBriefPOfactory pofactory = new UserBriefPOfactory(getter);
		return pofactory.getPO();
	}
	
	
	public RepoBriefPO getRepoBriefPO(String fullname){
		AbstractFieldsGetter getter = new RepoMapReader(fullname);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoBriefPOfactory pOfactory = new RepoBriefPOfactory(getter);
		return pOfactory.getPO();
		
	}
	
	
	public RepoBriefPO getRepoBriefPO(String owner,String name){
		return this.getRepoBriefPO(owner+"/"+name);
	}
	
	
	public RepoPO getRepoPO(String fullname){
		AbstractFieldsGetter getter = new RepoMapReader(fullname);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoPOfactory pOfactory = new RepoPOfactory(getter);
		return pOfactory.getPO();
	}
	
	
	public RepoPO getRepoPO(String owner,String name){
		return this.getRepoPO(owner+"/"+name);
	}
	
	
	public IssuePO getIssue(String fullname,String sha){
		AbstractFieldsGetter getter = new IssueItemReader(fullname, sha);
		IssuePOfactory issuePOfactory = new IssuePOfactory(getter, sha);
		return issuePOfactory.getPO();
	}

	public IssuePO getIssue(String owner,String name,String sha){
		return this.getIssue(owner+"/"+name, sha);
	}
	
	public CommitPO getCommitPO(String fullname,String sha){
		AbstractFieldsGetter getter = new CommitItemReader(fullname, sha);
		CommitPOfactory commitPOfactory = new CommitPOfactory(getter,sha);
		return commitPOfactory.getPO();
	}
	
	public CommitPO getCommitPO(String owner,String name,String sha){
		return this.getCommitPO(owner+"/"+name, sha);
	}
	
	public BranchPO getBranchPO(String fullname, String sha){
		AbstractFieldsGetter getter = new BranchItemreader(fullname, sha);
		BranchPOfactory branchPOfactory = new BranchPOfactory(getter);
		return branchPOfactory.getPO();
	}
	
	public BranchPO getBranchPO(String owner,String name,String sha){
		return this.getBranchPO(owner+"/"+name, sha);
	}
}
