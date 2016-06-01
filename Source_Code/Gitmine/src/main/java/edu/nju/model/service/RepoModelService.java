package edu.nju.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.SortType;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Harry on 2016/5/12.
 */
public interface RepoModelService {

    /**
     * get the recommend repository for the given username
     * @param webUsername
     * @return
     */
    public List<RepoVO> getRecommendRepo(String webUsername);

    /**
     * if there is no logined user, recommend the most popular repos
     * @return
     */
    public List<RepoVO> getPopularRepo();

    /**
     * get the total count of page of all repos
     * @return
     */
    public int getTotalPage();

    /**
     * get the related repository for the given repository
     * @param ownername
     * @param reponame
     * @return
     */
    public List<SimpleRepoVO> getRelatedRepo(String ownername, String reponame);

    /**
     * get the user po list in the order specified by parameter <tt>sortType</tt>
     * @param sortType
     * @param offset
     * @param maxNum
     * @return
     */
    public List<RepoVO> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum);

    /**
     *
     * search the matching list for the given constrains
     * @param keyword
     * @param sortType
     * @param filterType
     * @param language
     * @param createYear
     * @param pageNum from 1, not 0
     * @return
     */
    public List<RepoVO> getSearchResult(String keyword, String sortType, String filterType,
                                         String language, String createYear, int pageNum, boolean reverse);

    /**
     * get the total page of the search result
     * @param keyword
     * @param filterType
     * @param language
     * @param createYear
     * @return
     */
    public int getSearchPage(String keyword, String filterType, String language, String createYear);

    /**
     * get the basic information of a repo
     * @param ownername
     * @param reponame
     * @return
     */
    public RepoVO getRepoBasicInfo(String ownername, String reponame);

    /**
     * get the radar chart for the repo
     * @param ownername
     * @param reponame
     * @return
     */
    public RadarChart getRepoRadarChart(String ownername, String reponame);

    /**
     * get graphs of a repo about commits<br/>
     * @param ownername
     * @param reponame
     * @return
     */
    public Map<String,CommitChart> getCommitByContributor(String ownername, String reponame);

    /**
     * get the code add and delete for each week
     * @param ownername
     * @param reponame
     * @return
     */
    public CodeFrequency getCodeFrequency(String ownername, String reponame);

    /**
     * get the commit per hour of day and per day of week
     * @param ownername
     * @param reponame
     * @return an array of <b>TWO</b> <tt>CommitChart</tt>
     */
    public JsonNode getPunchCard(String ownername, String reponame);
}
