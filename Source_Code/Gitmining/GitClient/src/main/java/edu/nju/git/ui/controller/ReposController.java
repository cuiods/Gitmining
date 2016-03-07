package edu.nju.git.ui.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.bl.BrowseModel.impl.RepoCasualModel;
import edu.nju.git.bl.BrowseModel.impl.RepoSearchModel;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.factory.service.BlFactoryService;
import edu.nju.git.bl.factory.service.BrowseModelFactoryService;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.exception.PageOutOfBoundException;

/*
 * use to get information of the repositories from the logic part
 * @auther yyy
 * @date 2016-03-06 14:44
 * to complete
 */
public class ReposController {
//	private RepoBlService repoBl;
	public ReposController(){
//		repoBl = RepoBlImpl.instance();
	}
	
	/*
	 * get the brief information of the default repositories
	 * @param page :the number of the page now
	 * @param keyword:the keyword used to search, if keyword=null ,show the defualt order
	 */
	public ArrayList<RepoBriefVO> getRepos(int page,String keyword){
//		ArrayList<RepoBriefVO> repoArray = new ArrayList<RepoBriefVO>();
//		if (keyword.equals(null)){
//			repoBrow = RepoCasualModel.instance();
//		}else{
//			repoBrow = RepoSearchModel.instance();
//		}
//		//the first page
//		List<RepoBriefVO> repolist = repoBrow.getSearchResult(keyword);
//		//not the first page
//		if (page != 1){
//			try {
//				repolist = repoBrow.jumpToPage(page);
//			} catch (PageOutOfBoundException e) {
//				/*
//				 * exception , to deal with after time
//				 */
//				e.printStackTrace();
//			}
//		}
//		for (Iterator<RepoBriefVO> it = repolist.listIterator();it.hasNext();){
//			repoArray.add(it.next());
//		}
//		
//		return repoArray;
		return null;
	}
//	
//	/*
//	 * get detailed information of repo
//	 * @param name "ownerName/repoName"
//	 */
	public RepoVO getDetailedRepo(String name){
//		String[] ownRepo = new String[2];
//		ownRepo = name.split("/");
//		RepoVO repo = repoBl.getRepoBasicInfo(ownRepo[0], ownRepo[1]);
//		return repo;
		return null;
	}
	/*
	 * get the commits information of repo
	 */
	public CommitVO getCommits(int page,String name){
//		List<CommitVO> commitList = repoBl.getRepoCommit(name.split("/")[0], name.split("/")[1]);
		return null;
	}
	/*
	 * get the issues information of repo
	 */
	public IssueVO getIssues(int page,String repo){
		return null;
	}
	/*
	 * get the brief information of the forks of repo
	 */
	public ArrayList<RepoBriefVO> getFork(int page,String repo){
		return null;
	}
	/*
	 * get the breif information of the collaborators of repo
	 */
	public UserBriefVO getCollaborators(int page,String repo){
		return null;
	}
	/*
	 * get the brief information of the subscriber of repo
	 */
	public UserBriefVO getSubscriber(int page,String repo){
		return null;
	}
	/*
	 * get the brief information of the laudator of repo
	 */
	public UserBriefVO getLaudator(int page,String repo){
		return null;
	}
	/*
	 * get the branches information of repo
	 */
	public String getBranch(int page,String repo){
		return null;
	}
	
}
