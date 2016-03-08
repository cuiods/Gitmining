package edu.nju.git.ui.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

/*
 * use to get information of the repositories from the logic part
 * @auther yyy
 * @date 2016-03-06 14:44
 * to complete
 */
public class ReposController {
	private RepoBlService repoBl;
	public ReposController(){
		repoBl = RepoBlImpl.instance();
	}
	
	/*
	 * get the brief information of the default repositories
	 * @param page :the number of the page now
	 * @param keyword:the keyword used to search, if keyword=null ,show the defualt order;else show the search result
	 */
	public ArrayList<RepoBriefVO> getRepos(int page,String keyword){
		ArrayList<RepoBriefVO> repoArray = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> repolist =null;
		if ((page == 1)&&!(keyword.equals(null))){
			repolist = repoBl.getSearchResult(keyword);
		}else{
			try {
				repolist = repoBl.jumpToPage(page);
			} catch (PageOutOfBoundException e1) {
				// exception to deal with
				e1.printStackTrace();
			}
		}
		for (Iterator<RepoBriefVO> it = repolist.listIterator();it.hasNext();){
			repoArray.add(it.next());
		}
		
		return repoArray;
	}
	
	/*
	 * get detailed information of repo
	 * @param name "ownerName/repoName"
	 */
	public RepoVO getDetailedRepo(String owner,String repoName){
		RepoVO repo = repoBl.getRepoBasicInfo(owner, repoName);
		return repo;
	}
	/*
	 * get the commits information of repo
	 */
	public ArrayList<CommitVO> getCommits(String owner,String repoName){
		ArrayList<CommitVO> commits = new ArrayList<CommitVO>();
		List<CommitVO> commitList = repoBl.getRepoCommit(owner, repoName);
		for(Iterator<CommitVO> it = commitList.listIterator();it.hasNext();){
			commits.add(it.next());
		}
		return commits;
	}
	/*
	 * get the issues information of repo
	 */
	public ArrayList<IssueVO> getIssues(String owner,String repoName){
		ArrayList<IssueVO> issues = new ArrayList<IssueVO>();
		List<IssueVO> issuelist = repoBl.getRepoIssue(owner, repoName);
		for (Iterator<IssueVO> it = issuelist.listIterator();it.hasNext();){
			issues.add(it.next());
		}
		return issues;
	}
	/*
	 * get the brief information of the forks of repo
	 */
	public ArrayList<RepoBriefVO> getFork(String owner,String repoName){
		ArrayList<RepoBriefVO> forks = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> forkList = repoBl.getRepoFork(owner, repoName);
		for(Iterator<RepoBriefVO> it = forkList.listIterator();it.hasNext();){
			forks.add(it.next());
		}
		return forks;
	}
	/*
	 * get the breif information of the collaborators of repo
	 */
	public ArrayList<UserBriefVO> getCollaborators(String owner,String repoName){
		ArrayList<UserBriefVO> collaborators = new ArrayList<UserBriefVO>();
		List<UserBriefVO> collaList = repoBl.getRepoCollaborator(owner, repoName);
		for(Iterator<UserBriefVO> it = collaList.listIterator();it.hasNext();){
			collaborators.add(it.next());
		}
		return collaborators;
	}
	/*
	 * get the brief information of the subscriber of repo
	 */
	public ArrayList<UserBriefVO> getSubscriber(String owner,String repoName){
		ArrayList<UserBriefVO> subscribers = new ArrayList<UserBriefVO>();
		
		return null;
	}
	/*
	 * get the brief information of the laudator of repo
	 */
	public ArrayList<UserBriefVO> getLaudator(String owner,String repoName){
		return null;
	}
	/*
	 * get the branches information of repo
	 */
	public ArrayList<BranchVO> getBranch(String owner,String repoName){
		ArrayList<BranchVO> branches = new ArrayList<BranchVO>();
		List<BranchVO> branchList = repoBl.getRepoBranch(owner, repoName);
		for(Iterator<BranchVO> it = branchList.listIterator();it.hasNext();){
			branches.add(it.next());
		}
		return branches;
	}
	/*
	 * get the current page number 
	 */
	public int getCurrentPage(){
		int num = repoBl.getCurrentPage();
		return num;
	}
	/*
	 * get total page
	 */
	public int getTotalPage(){
		return repoBl.getTotalPage();
	}
	/*
	 * sort the table by {@link SortType} and {@link Reverse}
	 */
	
	public ArrayList<RepoBriefVO> sort(SortType type,Boolean isReverse){
		ArrayList<RepoBriefVO> newOrder = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> orderList = repoBl.sort(type,isReverse);
		for(Iterator<RepoBriefVO> it = orderList.listIterator();it.hasNext();){
			newOrder.add(it.next());
		}
		return newOrder;
	}
}
