package edu.nju.model.service;

import edu.nju.entity.TblUser;
import edu.nju.model.pojo.SimpleChart;

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
    public List<TblUser> getRecommendUser(String webUsername);

    /**
     * get the user similar to the given user
     * @param username
     * @return
     */
    public List<TblUser> getRelatedUser(String username);

    /**
     * get the user has the most followers
     * @return
     */
    public List<TblUser> getPopularUser();

    /**
     * get the user(s) whose name matches the keyword<br/>
     * the function can only search name of a user, not the bio or other information.
     * @param keyword
     * @return
     */
    public List<TblUser> getSearchResult(String keyword, int pageNum);

    /**
     * get the basic information of a user, which means the name, email, etc.
     * @param username
     * @return
     */
    public TblUser getUserBasicInfo(String username);

    /**
     * get the data for radar chart of a user
     * @param username
     * @return
     */
    public SimpleChart getUserRadarChart(String username);

}
