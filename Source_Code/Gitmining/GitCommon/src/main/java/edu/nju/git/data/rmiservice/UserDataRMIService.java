package edu.nju.git.data.rmiservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.datavisitors.uservisitors.UserVisitor;
import edu.nju.git.type.SortType;

/**
 * This is an interface that dig and analyze data from Internet and provide it to the business logic<br>
 * layer.
 * <p>This module will invoke pytho program to help dig data
 * @author benchaodong 
 * @date 2016/03/03
 */
public interface UserDataRMIService extends Remote{
	/**
     * Get user list meeting demands of a search.
     * @param regex the regex representation of the keyword being searched
     * @return a List of {@link UserBriefVO}, which matches the search result
     */
    public List<UserBriefVO> getSearchResult(String regex)throws RemoteException;

    /**
     * get the total count of user.
     * @return the amount of user
     */
    public int getTotalCount()throws RemoteException;

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     * @param sortType which type of list to get
     * @return the reference to the list
     */
    public List<UserPO> getPOs(SortType sortType)throws RemoteException;

    /**
     * use a visitor to access the data and return the wanted value.
     * @see UserVisitor
     * @param visitor the visitor
     * @return list of repo vo
     */
    public List<UserBriefVO> acceptVisitor(UserVisitor visitor)throws RemoteException;

    /**
     * Get the detailed information of a user who matched by the parameter userName
     * @param userName the name of a github user which you want to get his <b>DETAILED</b> information
     * @return a {@link UserVO}} instance that describe the user
     */
    public UserVO getUserInfo (String userName)throws RemoteException;

    /**
     * Get a list of repositories that the specific user owns
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user owns 
     */
    public List<String> getUserOwnRepos (String userName)throws RemoteException;
    
    /**
     * Get a list of repositories that the specific user subscribes
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user subscribes 
     */
    public List<String> getUserSubscribeRepos (String userName)throws RemoteException;

    /**
     * Get a list of repositories that the specific user collaborates
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user collaborates 
     */
    public List<String> getUserCollaborateRepos(String userName)throws RemoteException;

    /**
     * Get a list of repositories that the specific user contributes
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user contributes 
     */
    public List<String> getUserContributeRepos(String userName)throws RemoteException;
}
