package edu.nju.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.LoginModelService;
import edu.nju.model.service.RepoModelService;
import edu.nju.model.service.RepoStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/5/4.
 *
 */
@Controller
@RequestMapping("/repo")
public class RepoController {

    @Resource
    private RepoModelService repoModelImpl;

    @Resource
    private RepoStatsService repoStatsImpl;

    @Resource
    private LoginModelService loginModelImpl;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public Map home(HttpSession session){
        //todo get current user from session scope and generate recommend content

        Map<String, Object> result = new HashMap<>();

        List<RepoVO> recommend;
        if (session.getAttribute("webUsername") == null){
            recommend = repoModelImpl.getPopularRepo();
        }
        else {
            String webUsername = (String) session.getAttribute("webUsername");
            recommend = repoModelImpl.getRecommendRepo(webUsername);
        }
        List<RepoVO> mainList = repoModelImpl.getRepos(SortType.Repo_Name, false, 0, Const.ITEMS_PER_PAGE);
        result.put("repoList", mainList);
        result.put("recommend", recommend);

        return result;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@RequestParam int pageNum,
                    @RequestParam(required = false, defaultValue = "repo_name") String sortType,
                    @RequestParam(required = false, defaultValue = "false") boolean isDesc){
        Map<String,Object> map = new HashMap<>();
        long totalPage = repoModelImpl.getTotalPage();
        List<RepoVO> repoList = null;
        if (pageNum<=totalPage){
            SortType type = SortTypeBuilder.getSortType(sortType);
            if (type == null){
                type = SortType.Repo_Name;
            }
            if (pageNum<1)  pageNum=1;
            repoList = repoModelImpl.getRepos(type, isDesc, (pageNum-1)*Const.ITEMS_PER_PAGE, Const.ITEMS_PER_PAGE);
        }
        map.put("totalPage", 10);
        map.put("currentPage", pageNum);
        map.put("repoList", repoList);
        return map;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<RepoVO> getSearchResult(@RequestParam String keyword,
                                        @RequestParam(required = false, defaultValue = "") String filterType,
                                        @RequestParam(required = false, defaultValue = "") String language,
                                        @RequestParam(required = false, defaultValue = "") String createYear,
                                        @RequestParam int pageNum,
                                        @RequestParam(required = false, defaultValue = "repo_name") String sortType,
                                        @RequestParam(required = false, defaultValue = "false") boolean reverse){
        List<RepoVO> resultList = repoModelImpl.getSearchResult(keyword, sortType, filterType,
                language, createYear, pageNum, reverse);

        //todo use the web user hobby to resort the result and put the items match the user hobby on the top

        return resultList;
    }


    @RequestMapping(value = "/{ownername:.+}/{reponame:.+}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRepoInfo(@PathVariable String ownername, @PathVariable String reponame) {
        RepoVO basicInfo = repoModelImpl.getRepoBasicInfo(ownername, reponame);
        RadarChart radarChart = repoModelImpl.getRepoRadarChart(ownername, reponame);
        List<RepoVO> relatedRepo = repoModelImpl.getRelatedRepo(ownername, reponame);

        Map<String, Object> repoDetailInfo = new HashMap<>();
        repoDetailInfo.put("basicInfo", basicInfo);
        repoDetailInfo.put("radarChart", radarChart);
        repoDetailInfo.put("relatedRepo", relatedRepo);

        return repoDetailInfo;
    }

    @RequestMapping(value = "/star", method = RequestMethod.POST)
    @ResponseBody
    public boolean star(@RequestParam String ownername,@RequestParam String reponame,
                        HttpSession session){
        if (session.getAttribute("webUsername") == null){   //not logined yet
            return false;
        }
        else {
            String webUsername = session.getAttribute("wenUsername").toString();
            return loginModelImpl.starRepo(ownername,reponame,webUsername);
        }
    }

    @RequestMapping(value = "/unstar", method = RequestMethod.POST)
    @ResponseBody
    public boolean unStar(@RequestParam String ownername,@RequestParam String reponame,
                          HttpSession session){
        if (session.getAttribute("webUsername") == null){   //not logined yet
            return false;
        }
        else {
            String webUsername = session.getAttribute("wenUsername").toString();
            return loginModelImpl.unstarRepo(ownername,reponame,webUsername);
        }
    }

    @RequestMapping(value = "/{ownername:.+}/{reponame:.+}/graph/commit_by_contributor", method = RequestMethod.GET)
    @ResponseBody
    public Map getRepoCommitByContributor(@PathVariable String ownername, @PathVariable String reponame){
        return repoModelImpl.getCommitByContributor(ownername,reponame);
    }

    @RequestMapping(value = "/{ownername:.+}/{reponame:.+}/graph/code_frequency", method = RequestMethod.GET)
    @ResponseBody
    public Object getRepoCodeFrequency(@PathVariable String ownername, @PathVariable String reponame){
        return repoModelImpl.getCodeFrequency(ownername, reponame);
    }

    @RequestMapping(value = "/{ownername:.+}/{reponame:.+}/graph/punch_card", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode getRepoCommitByTime(@PathVariable("ownername") String ownername, @PathVariable("reponame") String reponame){
        return repoModelImpl.getPunchCard(ownername, reponame);
    }

    @RequestMapping(value = "/statistic/create_at", method = RequestMethod.GET)
    @ResponseBody
    public SimpleChart statRepoCreateTime(){
        return repoStatsImpl.statsCreateTime();
    }

    @RequestMapping(value = "/statistic/size", method = RequestMethod.GET)
    @ResponseBody
    public SimpleChart statRepoSize(){
        return repoStatsImpl.statsSize();
    }

    @RequestMapping(value = "/statistic/language", method = RequestMethod.GET)
    @ResponseBody
    public SimpleChart statRepoLanguage(){
        return repoStatsImpl.statsLanguage();
    }

    @RequestMapping(value = "/statistic/star", method = RequestMethod.GET)
    @ResponseBody
    public SimpleChart statRepoStar(){
        return repoStatsImpl.statsStarCount();
    }

    @RequestMapping(value = "/statistic/fork", method = RequestMethod.GET)
    @ResponseBody
    public SimpleChart statRepoFork(){
        return repoStatsImpl.statsForkCount();
    }

    public RepoModelService getRepoModelImpl() {
        return repoModelImpl;
    }

    public void setRepoModelImpl(RepoModelService repoModelImpl) {
        this.repoModelImpl = repoModelImpl;
    }
}
