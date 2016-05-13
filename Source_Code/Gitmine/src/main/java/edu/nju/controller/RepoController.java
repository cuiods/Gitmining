package edu.nju.controller;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoModelService;
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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public Map home( HttpSession session){
        //todo get current user from session scope and generate recommend content

        Map<String, Object> result = new HashMap<>();

        List<TblRepo> recommend;
        if (session.getAttribute("webUsername") == null){
            recommend = repoModelImpl.getPopularRepo();
        }
        else {
            String webUsername = (String) session.getAttribute("webUsername");
            recommend = repoModelImpl.getRecommendRepo(webUsername);
        }
        List<TblRepo> mainList = repoModelImpl.getRepos(SortType.Repo_Name, false, 0, Const.ITEMS_PER_PAGE);
        result.put("recommend", recommend);
        result.put("mainList", mainList);

        return result;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public List<TblRepo> getSearchResult(@RequestParam String keyword, @RequestParam String sortType,
                                  @RequestParam String filterType, @RequestParam String language,
                                  @RequestParam String createYear, @RequestParam int pageNum,
                                  @RequestParam boolean reverse){
        List<TblRepo> resultList = repoModelImpl.getSearchResult(keyword, sortType, filterType,
                language, createYear, pageNum, reverse);

        //todo use the web user hobby to resort the result and put the items match the user hobby on the top

        return resultList;
    }


    @RequestMapping(value = "/{ownername}/{reponame}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getRepoInfo(@PathVariable String ownername, @PathVariable String reponame) {

        TblRepo basicInfo = repoModelImpl.getRepoBasicInfo(ownername, reponame);
        SimpleChart radarChart = repoModelImpl.getRepoRadarChart(ownername, reponame);
        SimpleChart[] commitCharts = repoModelImpl.getRepoCommitCharts(ownername, reponame);
        List<TblRepo> relatedRepo = repoModelImpl.getRelatedRepo(ownername, reponame);

        Map<String, Object> repoDetailInfo = new HashMap<>();
        repoDetailInfo.put("basicInfo", basicInfo);
        repoDetailInfo.put("radarChart", radarChart);
        try{
            repoDetailInfo.put("commitPerDayOfAll", commitCharts[0]);
            repoDetailInfo.put("commitPerHourOfDay", commitCharts[1]);
            repoDetailInfo.put("commitPerDayOfWeek", commitCharts[2]);
            repoDetailInfo.put("commitPerDayOfMonth", commitCharts[3]);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        repoDetailInfo.put("related", relatedRepo);

        return repoDetailInfo;
    }

    @RequestMapping(value = "/{ownername}/{reponame}/graph", method = RequestMethod.GET)
    public String getRepoDetailGraph(@PathVariable String ownername, @PathVariable String reponame, Model model){
        SimpleChart[] commitCharts = repoModelImpl.getRepoCommitCharts(ownername, reponame);
        model.addAttribute("commitPerHourOfDay", commitCharts[0]);
        model.addAttribute("commitPerDayOfWeek", commitCharts[1]);
        model.addAttribute("commitPerDayOfMonth", commitCharts[2]);

        //todo more graphs should be added here, such as contributions of each member

        return "repo/graph";
    }

    public RepoModelService getRepoModelImpl() {
        return repoModelImpl;
    }

    public void setRepoModelImpl(RepoModelService repoModelImpl) {
        this.repoModelImpl = repoModelImpl;
    }
}
