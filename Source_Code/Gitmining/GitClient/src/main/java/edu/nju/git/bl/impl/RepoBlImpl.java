package edu.nju.git.bl.impl;

import edu.nju.git.VO.*;
import edu.nju.git.bl.BrowseModel.impl.RepoCasualModel;
import edu.nju.git.bl.BrowseModel.impl.RepoSearchModel;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.constant.Consts;
import edu.nju.git.data.factory.impl.DataFactory;
import edu.nju.git.data.factory.service.DataFactoryService;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.exception.NoSearchResultException;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.tools.RegexTranslator;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * This class implements {@link RepoBlService} interface and provide ui module with basic support.
 * <p>Whenever ui module need data, this class get data from data layer and then pass it to ui.
 * @author benchaodong
 * @date 2016-03-04
 */
public class RepoBlImpl implements RepoBlService {

    /**
     * The reference pointed to the only instance of this class because this class is set to be a singleton.
     */
    private static RepoBlImpl uniqueInstance = null;

    /**
     * the page is being viewed
     */
    private int CURRENT_PAGE = 1;

    /**
     * the data service which this class uses to get data
     */
    private RepoDataService repoDataService;

    /**
     * the strategy how to browse through the data
     */
    private RepoBrowseModelService browseModelService;

    /**
     * This list stores the search result of {@code getSearchResult}. <p>
     * Each element in this list contains brief information of a repository.
     */
    private List<RepoBriefVO> briefRepoList;

    /**
     * This static method returns the reference to the only instance of this class.<p>
     * Other class can get an instance of this class only by this method.
     * @return the instance of this class.
     */
    public static RepoBlImpl instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new RepoBlImpl();
        }
        return uniqueInstance;
    }

    /**
     * The constructor is designed to be private in order to set this class to singleton. <p>
     *
     * The constructor also initialize some variables of this class.
     */
    private RepoBlImpl() {
        DataFactoryService dataFactoryService = DataFactory.instance();
        repoDataService = dataFactoryService.getRepoDataService();

        //we use casual model in default
        browseModelService  = new RepoCasualModel(this);

    }



    @Override
    public List<RepoBriefVO> getSearchResult(String keyword) throws NoSearchResultException {
        if (keyword.isEmpty()) {
            setBrowseModelService(new RepoCasualModel(this));
            try {
                return jumpToPage(1);
            } catch (PageOutOfBoundException e) {
                e.printStackTrace();
                throw new NoSearchResultException("there is no result to show");
            }
        }
        else {
            setBrowseModelService(new RepoSearchModel(this));
            String regex = RegexTranslator.translate(keyword);
            briefRepoList = repoDataService.getSearchResult(regex);
            try {
                return jumpToPage(1);
            } catch (PageOutOfBoundException e) {
                e.printStackTrace();
                throw new NoSearchResultException("there is no result to show");
            }
        }
    }

    @Override
    public List<RepoBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException{
        return browseModelService.jumpToPage(pageNum);
    }

    @Override
    public List<RepoBriefVO> nextPage() throws PageOutOfBoundException {
        return browseModelService.nextPage();
    }

    @Override
    public List<RepoBriefVO> previousPage() throws PageOutOfBoundException {
        return browseModelService.previousPage();
    }

    @Override
    public List<RepoBriefVO> sort(SortType sortType, boolean reverse) {
        return browseModelService.sort(sortType, reverse);
    }

    @Override
    public RepoVO getRepoBasicInfo(String owner, String repoName) {
        return repoDataService.getRepoBasicInfo(owner, repoName);
    }

    @Override
    public List<UserBriefVO> getRepoContributor(String owner, String repoName) {
        return repoDataService.getRepoContributor(owner, repoName);
    }

    @Override
    public List<UserBriefVO> getRepoCollaborator(String owner, String repoName) {
        return repoDataService.getRepoCollaborator(owner, repoName);
    }

    @Override
    public List<BranchVO> getRepoBranch(String owner, String repoName) {
        return repoDataService.getRepoBranch(owner, repoName);
    }

    @Override
    public List<RepoBriefVO> getRepoFork(String owner, String repoName) {
        return repoDataService.getRepoFork(owner, repoName);
    }

    @Override
    public List<CommitVO> getRepoCommit(String owner, String repoName) {
        return repoDataService.getRepoCommit(owner, repoName);
    }

    @Override
    public List<IssueVO> getRepoIssue(String owner, String repoName) {
        return repoDataService.getRepoIssue(owner, repoName);
    }

    /**
     * set browse model, notify that the model can not be null
     * @param browseModelService
     */
    public void setBrowseModelService(RepoBrowseModelService browseModelService) {
        if (browseModelService != null) {
            this.browseModelService = browseModelService;
        }
    }

    @Override
    public int getCurrentPage() {
        return CURRENT_PAGE;
    }

    @Override
    public int getTotalPage() {
        int elementNum;
        if (browseModelService instanceof RepoCasualModel) {
            elementNum = repoDataService.getTotalCount();
        }
        else {
            elementNum = briefRepoList.size();
        }
        return (elementNum% Consts.PAGE_CAPACITY)==0?elementNum/Consts.PAGE_CAPACITY:elementNum/Consts.PAGE_CAPACITY+1;
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
     * <p>this interface should only be used by {@link edu.nju.git.bl.BrowseModel.impl.RepoSearchModel} or <br>
     *    {@link RepoCasualModel} for sort.
     * @return brief repository list.
     */
    public List<RepoBriefVO> getBriefRepoList() {
        return briefRepoList;
    }

    /**
     * get the amount of items show on one page
     * @return the number
     */
    public int getDEFAULT_PAGE_CAPACITY(){
        return Consts.PAGE_CAPACITY;
    }

    public RepoDataService getRepoDataService() {
        return repoDataService;
    }
}
