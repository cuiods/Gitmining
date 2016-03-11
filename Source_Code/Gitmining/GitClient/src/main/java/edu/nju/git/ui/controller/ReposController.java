package edu.nju.git.ui.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import edu.nju.git.exception.NoSearchResultException;
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
	 * @param keyword:the keyword used to search, if keyword="" ,show the defualt order;else show the search result
	 */
	public ArrayList<RepoBriefVO> getRepos(int page,String keyword){
		ArrayList<RepoBriefVO> repoArray = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> repolist =null;
		if ((page == 1)){
			repolist = repoBl.getSearchResult(keyword);
		}else{
		
			try {
				repolist = repoBl.jumpToPage(page);
			} catch (PageOutOfBoundException e1) {
				// exception to deal with
				e1.printStackTrace();
			}
		}
		if(repolist!=null){
			for (Iterator<RepoBriefVO> it = repolist.listIterator();it.hasNext();){
				repoArray.add(it.next());
			}
		}
//		RepoBriefVO vo = new RepoBriefVO();
//		vo.setOwner("aaa");
//		vo.setName("gitming");
//		vo.setDescription("a very good project");
//		vo.setNum_stars(10000);
//		vo.setNum_forks(100);
//		vo.setNum_subscribers(8000);
//		vo.setLastUpdate("2016-0-09");
//		repoArray.add(vo);
//		RepoBriefVO vo1 = new RepoBriefVO();
//		vo1.setOwner("hhh");
//		vo1.setName("gitming");
//		vo1.setDescription("a very good project");
//		vo1.setNum_stars(10000);
//		vo1.setNum_forks(100);
//		vo1.setNum_subscribers(8000);
//		vo1.setLastUpdate("2016-0-09");
//		repoArray.add(vo1);
		
		return repoArray;
	}
	
	/*
	 * get detailed information of repo
	 * @param name "ownerName/repoName"
	 */
	public RepoVO getDetailedRepo(String owner,String repoName){
		RepoVO repo = new RepoVO();
		repo = repoBl.getRepoBasicInfo(owner, repoName);
		if(repo!=null)
			return repo;
		else{
			System.out.println("NoData");
			return repo;
		}
			
	}
	/*
	 * get the commits information of repo
	 */
	public ArrayList<CommitVO> getCommits(String owner,String repoName){
		ArrayList<CommitVO> commits = new ArrayList<CommitVO>();
		List<CommitVO> commitList = repoBl.getRepoCommit(owner, repoName);
		if(commitList!=null){
			for(Iterator<CommitVO> it = commitList.listIterator();it.hasNext();){
				commits.add(it.next());
			}
		}
		return commits;
	}
	/*
	 * get the issues information of repo
	 */
	public ArrayList<IssueVO> getIssues(String owner,String repoName){
		ArrayList<IssueVO> issues = new ArrayList<IssueVO>();
		List<IssueVO> issuelist = repoBl.getRepoIssue(owner, repoName);
		if(issuelist!=null){
			for (Iterator<IssueVO> it = issuelist.listIterator();it.hasNext();){
				issues.add(it.next());
			}
		}
		return issues;
	}
	/*
	 * get the brief information of the forks of repo
	 */
	public ArrayList<RepoBriefVO> getFork(String owner,String repoName){
		ArrayList<RepoBriefVO> forks = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> forkList = repoBl.getRepoFork(owner, repoName);
		if(forkList!=null){
			for(Iterator<RepoBriefVO> it = forkList.listIterator();it.hasNext();){
				forks.add(it.next());
			}
		}
		return forks;
	}
	/*
	 * get the breif information of the collaborators of repo
	 */
	public ArrayList<UserBriefVO> getCollaborators(String owner,String repoName){
		ArrayList<UserBriefVO> collaborators = new ArrayList<UserBriefVO>();
		List<UserBriefVO> collaList = repoBl.getRepoCollaborator(owner, repoName);
		if(collaList!=null){
			for(Iterator<UserBriefVO> it = collaList.listIterator();it.hasNext();){
				collaborators.add(it.next());
			}
		}
		return collaborators;
	}
	/*
	 * get the brief information of the subscriber of repo
	 */
	public ArrayList<UserBriefVO> getSubscriber(String owner,String repoName){
		ArrayList<UserBriefVO> subscribers = new ArrayList<UserBriefVO>();
		List<UserBriefVO> subList = repoBl.getRepoContributor(owner, repoName);
		if(subList!=null){
			for(Iterator<UserBriefVO> it = subList.listIterator();it.hasNext();){
				subscribers.add(it.next());
			}
		}
		
		return subscribers;
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
		if(branchList!=null){
			for(Iterator<BranchVO> it = branchList.listIterator();it.hasNext();){
				branches.add(it.next());
			}
		}
		return branches;
	}
	/*
	 * get the current page number 
	 */
	public int getCurrentPage(){
		int num = repoBl.getCurrentPage();
		return num;
//		return 5;
	}
	/*
	 * get total page
	 */
	public int getTotalPage(){
		return repoBl.getTotalPage();
	}
	
	public ArrayList<RepoBriefVO> jumpPage(int pageNum){
		ArrayList<RepoBriefVO> repos = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> repoList = null;
		try {
			repoList = repoBl.jumpToPage(pageNum);
		} catch (PageOutOfBoundException e) {
			// exception to deal with
			e.printStackTrace();
		}
		if(repoList!=null){
			for(Iterator<RepoBriefVO> it = repoList.listIterator();it.hasNext();){
				repos.add(it.next());
			}
		}
		return repos;
	}
	/*
	 * sort the table by {@link SortType} and {@link Reverse}
	 */
	
	public ArrayList<RepoBriefVO> sort(SortType type,Boolean isReverse){
		ArrayList<RepoBriefVO> newOrder = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> orderList = repoBl.sort(type,isReverse);
		if(orderList!=null){
			for(Iterator<RepoBriefVO> it = orderList.listIterator();it.hasNext();){
				newOrder.add(it.next());
			}
		}
		return newOrder;
	}
	
	/*
	 * used to get the before UI
	 */
	public ArrayList<RepoBriefVO> getShownList(){
		ArrayList<RepoBriefVO> repo = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> list = repoBl.getShownRepoList();
		if(list!=null){
			for(Iterator<RepoBriefVO> it = list.listIterator();it.hasNext();){
				repo.add(it.next());
			}
		}
		return repo;
	}
	
	
}
