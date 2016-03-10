package edu.nju.git.bl.service;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.exception.NoSearchResultException;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * This interface define all the service the <b>userbusinesslogic</b> module should provide<br>
 * to presentation module.
 * <p>
 * The module get data from data module and analyze the data, then provide them<br>
 * to ui module.
 * @author benchaodong
 * @date 2016/03/03 
 */
public interface UserBlService {

    /**
     * Get user list meeting demands of a search.
     * @param keyword the full or part of the name of the user
     * @return a List of {@link UserBriefVO}, which matches the search result
     */
    public List<UserBriefVO> getSearchResult(String keyword) throws NoSearchResultException;

    /**
     * jump to the specific page.
     * @param pageNum page number
     * @return the list in this page
     */
    public List<UserBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException;

    /**
     * jump to next page of result list
     * @return list of user brief information
     * @throws PageOutOfBoundException
     */
    public List<UserBriefVO> nextPage() throws PageOutOfBoundException;

    /**
     * jump to the previous page
     * @return list of user brief information
     * @throws PageOutOfBoundException
     */
    public List<UserBriefVO> previousPage() throws PageOutOfBoundException;

    /**
     * sort the list in specific aspect defined by <tt>sortType</tt>
     * @param sortType in which way sort
     * @return
     */
    default List<UserBriefVO> sort(SortType sortType) {
        return sort(sortType, true);
    }

    /**
     * sort the list in specific aspect defined by <tt>sortType</tt>
     * @param sortType in which way sort
     * @param reverse if the order is reversed
     * @return
     */
    public List<UserBriefVO> sort(SortType sortType, boolean reverse);

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

    /**
     * return the brief list.
     *
     * @return brief user list.
     */
    public List<UserBriefVO> getShownUserList();

    /**
     * get the page number that is being viewed.
     * @return page number
     */
    public int getCurrentPage();

    /**
     * get total page of the result list.
     * <p>if in casual model, the totalPage is meaningless.
     * @return page number
     */
    public int getTotalPage();

}
