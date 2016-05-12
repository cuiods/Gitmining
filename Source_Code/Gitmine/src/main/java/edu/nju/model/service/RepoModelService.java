package edu.nju.model.service;

import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.SimpleChart;

import java.util.List;

/**
 * Created by Harry on 2016/5/12.
 */
public interface RepoModelService {

    /**
     * get the recommend repository for the given username
     * @param webUsername
     * @return
     */
    public List<TblRepo> getRecommendRepo(String webUsername);

    /**
     * if there is no logined user, recommend the most popular repos
     * @return
     */
    public List<TblRepo> getPopularRepo();

    /**
     * get the related repository for the given repository
     * @param owername
     * @param reponame
     * @return
     */
    public List<TblRepo> getRelatedRepo(String owername, String reponame);

    /**
     *
     * search the matching list for the given constrains
     * @param keyword
     * @param sortType
     * @param filterType
     * @param language
     * @param createYear
     * @return
     */
    public List<TblRepo> getSearchResult(String keyword, String sortType, String filterType,
                                         String language, String createYear);

    /**
     * get the basic information of a repo
     * @param ownername
     * @param reponame
     * @return
     */
    public TblRepo getRepoBasicInfo(String ownername, String reponame);

    /**
     * get the radar chart for the repo
     * @param ownername
     * @param reponame
     * @return
     */
    public SimpleChart getRepoRadarChart(String ownername, String reponame);

    /**
     * get graphs of a repo about commits<br/>
     * @param ownername
     * @param reponame
     * @return the array of SimpleCharts' length is 3, the 1st is per hour of a day,<br/>
     *          the 2nd is per day of a week, the 3rd is per day of a month
     */
    public SimpleChart[] getRepoCommitCharts(String ownername, String reponame);
}
