package edu.nju.model.service;

import edu.nju.common.SortType;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.UserVO;

import java.util.List;

/**
 * Created by Harry on 2016/5/12.
 * this class is the model part of MVC for github user
 */
public interface UserModelService {

    /**
     * recommend user for the logined web user
     * @param webUsername
     * @return
     */
    public List<UserVO> getRecommendUser(String webUsername);

    /**
     * get the user similar to the given user
     * @param username
     * @return
     */
    public List<UserVO> getRelatedUser(String username);

    /**
     * get the contribute repositories for the given user
     * @param username
     * @return
     */
    public List<RepoVO> getContributeRepo(String username);

    /**
     * get the subscribe repos for the given user
     * @param username
     * @return
     */
    public List<RepoVO> getSubscribeRepo(String username);

    /**
     * get the user has the most followers
     * @return
     */
    public List<UserVO> getPopularUser();

    /**
     * get the total page count of all users
     * @return
     */
    public int getTotalPage();

    /**
     * get the user list
     * @param sortType
     * @param isDesc
     * @param offset
     * @param maxNum
     * @return
     */
    public List<UserVO> getUsers(SortType sortType, boolean isDesc, int offset, int maxNum);

    /**
     * get the user(s) whose name matches the keyword<br/>
     * the function can only search name of a user, not the bio or other information.
     * @param keyword
     * @return
     */
    public List<UserVO> getSearchResult(String keyword, String sortType, int pageNum, boolean reverse);

    /**
     * get the basic information of a user, which means the name, email, etc.
     * @param username
     * @return
     */
    public UserVO getUserBasicInfo(String username);

    /**
     * get the data for radar chart of a user
     * @param username
     * @return
     */
    public RadarChart getUserRadarChart(String username);

}
