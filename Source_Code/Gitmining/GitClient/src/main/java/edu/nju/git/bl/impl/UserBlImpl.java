package edu.nju.git.bl.impl;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.BrowseModel.impl.UserCasualModel;
import edu.nju.git.bl.BrowseModel.impl.UserSearchModel;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.constant.Consts;
import edu.nju.git.data.factory.impl.DataFactory;
import edu.nju.git.data.factory.service.DataFactoryService;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.exception.NoSearchResultException;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.tools.RegexTranslator;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * This class get data from data layer, then it process the data and pass it to presentation layer<br>
 * to display.
 * <p>The class is designed to be a singleton.
 * @author benchaodong
 * @date 2016-03-04
 */
public class UserBlImpl implements UserBlService {

    /**
     * The reference pointed to the only instance of this class because this class is set to be a singleton.
     */
    private static UserBlImpl uniqueInstance = null;

    /**
     * current page displayed in ui module
     */
    private int CURRENT_PAGE = 1;

    /**
     * the strategy how to browse through the data
     */
    private UserBrowseModelService browseModelService;

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
     * This list stores references to vos that is being shown on presentation layer.
     */
    private List<UserBriefVO> shownUserList;

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

        //we use casual model in default
        browseModelService = new UserCasualModel(this);

    }

    @Override
    public List<UserBriefVO> getSearchResult(String keyword) {
        if (keyword.isEmpty()) {
            setBrowseModelService(new UserCasualModel(this));
            try {
                return jumpToPage(1);
            } catch (PageOutOfBoundException e) {
                System.out.println("No search result !");
            }
        }
        else {
            setBrowseModelService(new UserSearchModel(this));
            String regex = RegexTranslator.translate(keyword);
            briefUserList = userDataService.getSearchResult(regex);
            try {
                return jumpToPage(1);
            } catch (PageOutOfBoundException e) {
                System.out.println("No search result !");
            }
        }
        return null;
    }

    @Override
    public List<UserBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException {
        return shownUserList = browseModelService.jumpToPage(pageNum);
    }

    @Override
    public List<UserBriefVO> nextPage() throws PageOutOfBoundException {
        return shownUserList = browseModelService.nextPage();
    }

    @Override
    public List<UserBriefVO> previousPage() throws PageOutOfBoundException {
        return shownUserList = browseModelService.previousPage();
    }

    @Override
    public List<UserBriefVO> sort(SortType sortType, boolean reverse) {
        return shownUserList = browseModelService.sort(sortType, reverse);
    }

    @Override
    public UserVO getUserInfo(String userName) {
        return userDataService.getUserInfo(userName);
    }

    @Override
    public List<RepoBriefVO> getUserOwnRepos(String userName) {
        return userDataService.getUserOwnRepos(userName);
    }

    @Override
    public List<RepoBriefVO> getUserSubscribeRepos(String userName) {
        return userDataService.getUserSubscribeRepos(userName);
    }

    @Override
    public List<RepoBriefVO> getUserCollaborateRepos(String userName) {
        return userDataService.getUserCollaborateRepos(userName);
    }

    @Override
    public List<RepoBriefVO> getUserContributeRepos(String userName) {
        return userDataService.getUserContributeRepos(userName);
    }

    @Override
    public List<UserBriefVO> getShownUserList() {
        return shownUserList;
    }

    /**
     * set browse model, notify that the model can not be null
     * @param browseModelService
     */
    public void setBrowseModelService(UserBrowseModelService browseModelService) {
        if (browseModelService != null) {
            this.browseModelService = browseModelService;
        }
    }

    @Override
    public int getCurrentPage() {
        return CURRENT_PAGE;
    }

    @Override
    public int getTotalPage(){
        int elementNum;
        if (browseModelService instanceof UserCasualModel) {
            elementNum = userDataService.getTotalCount();
        }
        else {
            elementNum = briefUserList.size();
        }
        return (elementNum % Consts.PAGE_CAPACITY)==0?elementNum/Consts.PAGE_CAPACITY:elementNum/Consts.PAGE_CAPACITY+1;
    }

    /**
     * set the page number that is being viewed.
     * @param number
     */
    public void setCurrentPage(int number) {
        if (number>0) {
            CURRENT_PAGE = number;
        }
    }

    /**
     * return the brief list.
     * <p>this interface should only be used by {@link edu.nju.git.bl.BrowseModel.impl.UserSearchModel} or <br>
     *    {@link UserCasualModel} for sort.
     * @return brief user list.
     */
    public List<UserBriefVO> getBriefUserList() {
        return briefUserList;
    }

    /**
     * get the number of items a page shows one time.
     * @return the capacity of a page
     */
    public int getDEFAULT_PAGE_CAPACITY(){
        return Consts.PAGE_CAPACITY;
    }

    public UserDataService getUserDataService(){
        return userDataService;
    }
}
