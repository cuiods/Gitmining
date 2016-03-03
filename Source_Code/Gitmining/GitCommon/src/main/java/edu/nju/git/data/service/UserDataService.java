package edu.nju.git.data.service;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;

import java.util.List;

/**
 * This is an interface that dig and analyze data from Internet and provide it to the business logic<br>
 * layer.
 * <p>This module will invoke pytho program to help dig data
 * @author benchaodong 
 * @date 2016/03/03
 */
public interface UserDataService {
	/**
     * Get user list meeting demands of a search.
     * @param keyword the full or part of the name of the user
     * @return a List of {@link UserBriefVO}, which matches the search result
     */
    public List<UserBriefVO> getSearchResult(String keyword);

    /**
     * Get the detailed information of a user who matched by the parameter userName
     * @param userName the name of a github user which you want to get his <b>DETAILED</b> information
     * @return a {@link UserVO}} instance that describe the user
     */
    public UserVO getUserInfo (String userName);

    /**
     * Get a list of repositories that the specific user owns
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user owns 
     */
    public List<RepoBriefVO> getUserOwnRepos (String userName);
    
    /**
     * Get a list of repositories that the specific user subscribes
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user subscribes 
     */
    public List<RepoBriefVO> getUserSubscribeRepos (String userName);

    /**
     * Get a list of repositories that the specific user collaborates
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user collaborates 
     */
    public List<RepoBriefVO> getUserCollaborateRepos(String userName);

    /**
     * Get a list of repositories that the specific user contributes
     * @param userName name of the user you want to search
     * @return a list of brief information of repositories that the user contributes 
     */
    public List<RepoBriefVO> getUserContributeRepos(String userName);
}
