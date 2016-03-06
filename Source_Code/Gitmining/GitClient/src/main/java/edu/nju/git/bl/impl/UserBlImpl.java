package edu.nju.git.bl.impl;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.data.factory.impl.DataFactory;
import edu.nju.git.data.factory.service.DataFactoryService;
import edu.nju.git.data.service.UserDataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2016/3/4.
 */
public class UserBlImpl implements UserBlService {

    /**
     * The reference pointed to the only instance of this class because this class is set to be a singleton.
     */
    private static UserBlImpl uniqueInstance = null;

    /**
     * default page capacity, namely how many items of search results one page can show.
     */
    private final int DEFAULT_PAGE_CAPACITY = 10;

    /**
     * the data service which this class uses to get data
     */
    private UserDataService userDataService;

    /**
     * This list stores the search result of {@code getSearchResult}. <p>
     * Each element in this list contains brief information of a user.
     */
    private List<UserBriefVO> briefUserList;

    /**
     * This static method returns the reference to the only instance of this class.<p>
     * Other class can get an instance of this class only by this method.
     * @return the instance of this class.
     */
    public static UserBlImpl instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new UserBlImpl();
        }
        return uniqueInstance;
    }

    /**
     * The constructor is designed to be private in order to set this class to singleton.<p>
     *
     * The constructor initialize <tt>userDataService</tt> and <tt>briefUserList</tt> variables.
     */
    private UserBlImpl() {
        DataFactoryService dataFactoryService = DataFactory.instance();
        userDataService = dataFactoryService.getUserDataService();
        briefUserList = new ArrayList<UserBriefVO>();
    }

    @Override
    public List<UserBriefVO> getSearchResult(String keyword) {
        return null;
    }

    @Override
    public UserVO getUserInfo(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserOwnRepos(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserSubscribeRepos(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserCollaborateRepos(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserContributeRepos(String userName) {
        return null;
    }
}
