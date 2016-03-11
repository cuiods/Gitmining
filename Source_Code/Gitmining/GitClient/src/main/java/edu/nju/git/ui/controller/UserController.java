package edu.nju.git.ui.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.exception.NoSearchResultException;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;
/*
 * used to get information of users from the logic part
 * @auther yyy
 * @date 2016-03-07
 */
public class UserController {
	private UserBlService userBl ;
	public UserController(){
		userBl = UserBlImpl.instance();
	}
	/*
	 * get the simple information of users for NO.@param pageNum page 
	 */
	public ArrayList<UserBriefVO> getUsers(int page,String keyword){
		ArrayList<UserBriefVO> users = new ArrayList<UserBriefVO>();
		List<UserBriefVO> userList = null;
		if((page == 1)){
			userList = userBl.getSearchResult(keyword);
		}else{
			try {
				userList = userBl.jumpToPage(page);
			} catch (PageOutOfBoundException e) {
				// exception to deal with
				e.printStackTrace();
			}
		}
		if(userList!=null){
			for (Iterator<UserBriefVO> it = userList.listIterator();it.hasNext();){
				users.add(it.next());
			}
		}
		return users;
	}
	/*
	 * get detailed information of @param userName referring user
	 */
	public UserVO getDetailedUser(String userName){
		UserVO user = userBl.getUserInfo(userName);
		if(user==null) System.out.println("no data1!");
		return user;
	}
	
	public ArrayList<RepoBriefVO> getOwnRepos(String userName){
		ArrayList<RepoBriefVO> owns = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> ownList = userBl.getUserOwnRepos(userName);
		if (ownList!=null) {
			for(Iterator<RepoBriefVO> it = ownList.listIterator();it.hasNext();){
				owns.add(it.next());
			}
		}
		return owns;
	}
	
	public ArrayList<RepoBriefVO> getContributeRepos(String userName){
		ArrayList<RepoBriefVO> contri = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> contriList = userBl.getUserContributeRepos(userName);
		if (contriList!=null) {
			for(Iterator<RepoBriefVO> it = contriList.listIterator();it.hasNext();){
				contri.add(it.next());
			}
		}
		return contri;
	}
	
	public ArrayList<RepoBriefVO> getSubscribeRepos(String userName){
		ArrayList<RepoBriefVO> subscri = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> subscriList = userBl.getUserSubscribeRepos(userName);
		if(subscriList!=null){
			for(Iterator<RepoBriefVO> it = subscriList.listIterator();it.hasNext();){
				subscri.add(it.next());
			}
		}
		return subscri;
	}
	
	public ArrayList<RepoBriefVO> getCollaborateRepos(String userName){
		ArrayList<RepoBriefVO> collab = new ArrayList<RepoBriefVO>();
		List<RepoBriefVO> collabList = userBl.getUserCollaborateRepos(userName);
		if(collabList!=null){
			for(Iterator<RepoBriefVO> it = collabList.listIterator();it.hasNext();){
				collab.add(it.next());
			}
		}
				
		return collab;
	}
	
	public ArrayList<UserBriefVO> sort(SortType type,Boolean isReverse){
		ArrayList<UserBriefVO> newOrder = new ArrayList<UserBriefVO>();
		List<UserBriefVO> orderList = userBl.sort(type, isReverse);
		if(orderList!=null){
			for(Iterator<UserBriefVO> it = orderList.listIterator();it.hasNext();){
				newOrder.add(it.next());
			}
		}
		return newOrder;
	}
	
	public int getCurrentPage(){
		return userBl.getCurrentPage();
	}
	
	public int getTotalPage(){
		return userBl.getTotalPage();
	}
	
	public ArrayList<UserBriefVO> jumpPage(int pageNum){
		ArrayList<UserBriefVO> users = new ArrayList<UserBriefVO>();
		List<UserBriefVO> userList = null;
		try {
			userList = userBl.jumpToPage(pageNum);
		} catch (PageOutOfBoundException e) {
			// exception to deal with
			e.printStackTrace();
		}
		if(userList!=null){
			for(Iterator<UserBriefVO> it = userList.listIterator();it.hasNext();){
				users.add(it.next());
			}
		}
		return users;
	}
	/*
	 * get the before UI table
	 */
	public ArrayList<UserBriefVO> getBeforeList(){
		ArrayList<UserBriefVO> user = new ArrayList<UserBriefVO>();
		List<UserBriefVO> list = userBl.getShownUserList();
		if(list!=null){
			for(Iterator<UserBriefVO> it = list.listIterator();it.hasNext();){
				user.add(it.next());
			}
		}else System.out.println("wrong!");
		return user;
	}
}
