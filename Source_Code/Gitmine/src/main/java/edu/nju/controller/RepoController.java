package edu.nju.controller;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.RadarChart;
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

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@RequestParam int pageNum,
                    @RequestParam(required = false, defaultValue = "repo_name") String sortType,
                    @RequestParam(required = false, defaultValue = "false") boolean isDesc){
        Map<String,Object> map = new HashMap<>();
        long totalPage = repoModelImpl.getTotalPage();
        List<TblRepo> repoList = null;
        if (pageNum<=totalPage){
            SortType type = SortTypeBuilder.getSortType(sortType);
            if (type == null){
                type = SortType.Repo_Name;
            }
            if (pageNum<1)  pageNum=1;
            repoList = repoModelImpl.getRepos(type, isDesc, (pageNum-1)*Const.ITEMS_PER_PAGE, Const.ITEMS_PER_PAGE);
        }
        map.put("totalPage", totalPage);
        map.put("currentPage", pageNum);
        map.put("repoList", repoList);
        return map;
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


    @RequestMapping(value = "/{ownername}/{reponame}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRepoInfo(@PathVariable String ownername, @PathVariable String reponame) {

        TblRepo basicInfo = repoModelImpl.getRepoBasicInfo(ownername, reponame);
        RadarChart radarChart = repoModelImpl.getRepoRadarChart(ownername, reponame);
        List<TblRepo> relatedRepo = repoModelImpl.getRelatedRepo(ownername, reponame);

        Map<String, Object> repoDetailInfo = new HashMap<>();
        repoDetailInfo.put("basicInfo", basicInfo);
        repoDetailInfo.put("radarChart", radarChart);
        repoDetailInfo.put("related", relatedRepo);

        return repoDetailInfo;
    }

    @RequestMapping(value = "/{ownername}/{reponame}/graph", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRepoDetailGraph(@PathVariable String ownername, @PathVariable String reponame){
        //SimpleChart[] commitCharts = repoModelImpl.getRepoCommitCharts(ownername, reponame);


        //todo more graphs should be added here, such as contributions of each member

        return null;
    }

    public RepoModelService getRepoModelImpl() {
        return repoModelImpl;
    }

    public void setRepoModelImpl(RepoModelService repoModelImpl) {
        this.repoModelImpl = repoModelImpl;
    }
}
