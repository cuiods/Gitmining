package edu.nju.git.ui.controller;

import java.util.ArrayList;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.service.UserBlService;
/*
 * used to get information of users from the logic part
 * @auther yyy
 * @date 2016-03-07
 */
public class UserController {
	private UserBlService userBl ;
	public UserController(){
//		userBl = UserBlImpl.instance();
	}
	/*
	 * get the simple information of users for NO.@param pageNum page 
	 */
	public ArrayList<UserBriefVO> getUsers(int pageNum){
		return null;
	}
	/*
	 * get detailed information of @param userName referring user
	 */
	public UserVO getDetailedUser(String userName){
		return null;
	}
	
	public ArrayList<RepoBriefVO> getOwnRepos(String userName){
		return null;
	}
	
	public ArrayList<RepoBriefVO> getContributeRepos(String userName){
		return null;
	}
	
	public ArrayList<RepoBriefVO> getSubscribeRepos(String userName){
		return null;
	}
	
	public ArrayList<RepoBriefVO> getCollaborateRepos(String userName){
		return null;
	}
}
