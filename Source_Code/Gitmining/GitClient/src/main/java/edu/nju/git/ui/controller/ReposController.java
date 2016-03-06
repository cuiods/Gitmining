package edu.nju.git.ui.controller;

import java.util.ArrayList;

import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;

/*
 * use to get information of the repositories from the logic part
 * @auther yyy
 * @date 2016-03-06 14:44
 * to complete
 */
public class ReposController {
	
	public ReposController(){
		
	}
	
	/*
	 * get the brief information of the default repositories
	 * @param page :the number of the page now
	 * @param keyword:the keyword used to search, if keyword=null ,show the defualt order
	 */
	public ArrayList<RepoBriefVO> getRepos(int page,String keyword){
		return null;
	}
	
	/*
	 * get detailed information of repo
	 */
	public RepoBriefVO getDetailedRepo(String repo){
		return null;
	}
	/*
	 * get the commits information of repo
	 */
	public CommitVO getCommits(int page,String repo){
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
