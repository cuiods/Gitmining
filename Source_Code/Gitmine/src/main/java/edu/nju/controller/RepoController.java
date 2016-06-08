package edu.nju.controller;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.pojo.SimpleRepoVO;
import edu.nju.model.service.HobbyModelService;
import edu.nju.model.service.LoginModelService;
import edu.nju.model.service.RepoModelService;
import edu.nju.model.service.RepoStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/5/4.
 *
 */
@Controller
@RequestMapping("/repo")
public class RepoController {

    private int totalPage = 0;

    private RepoModelService repoModelImpl;

    @Resource
    private RepoStatsService repoStatsImpl;

    @Resource
    private HobbyModelService hobbyModelImpl;

    @Autowired
    public RepoController(RepoModelService repoModelImpl){
        this.repoModelImpl = repoModelImpl;
        this.totalPage = repoModelImpl.getTotalPage();
    }

    @RequestMapping(value = "/languages")
    @ResponseBody
    public List<String> getAllLanguages(){
        //todo
        return null;
    }

    @RequestMapping(value = "/recommend")
    @ResponseBody
    public List<RepoVO> home(@RequestParam(required = false,defaultValue = "0") int offset,
                    @RequestParam(required = false,defaultValue = "5") int maxResults,
                    HttpSession session){

        List<RepoVO> recommend;
        if (session.getAttribute("webUsername") == null){
            recommend = repoModelImpl.getPopularRepo(offset,maxResults);
            HashSet<String> staredRepo = (HashSet<String>) session.getAttribute("staredRepo");
            for (RepoVO vo:recommend){
                if (staredRepo.contains(vo.getOwnerName()+"/"+vo.getReponame())){
                    vo.setIsStared(true);
                }
            }

        }
        else {
            String webUsername = (String) session.getAttribute("webUsername");
            recommend = repoModelImpl.getRecommendRepo(webUsername,offset,maxResults);
        }

        return recommend;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@RequestParam int pageNum,
                    @RequestParam(required = false, defaultValue = "repo_name") String sortType,
                    @RequestParam(required = false, defaultValue = "false") boolean isDesc,
                    HttpSession session){
        Map<String,Object> map = new HashMap<>();
        List<RepoVO> repoList = null;
        if (pageNum<=totalPage){
            SortType type = SortTypeBuilder.getSortType(sortType);
            if (type == null){
                type = SortType.Repo_Name;
            }
            if (pageNum<1)  pageNum=1;
            repoList = repoModelImpl.getRepos(type, isDesc, (pageNum-1)*Const.ITEMS_PER_PAGE, Const.ITEMS_PER_PAGE);
            if (session.getAttribute("webUsername") != null){
                HashSet<String> staredRepo = (HashSet<String>) session.getAttribute("staredRepo");
                for (RepoVO vo:repoList){
                    if (staredRepo.contains(vo.getOwnerName()+"/"+vo.getReponame())){
                        vo.setIsStared(true);
                    }
                }
            }
        }
        map.put("totalPage", totalPage);
        map.put("currentPage", pageNum);
        map.put("repoList", repoList);
        return map;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Map getSearchResult(@RequestParam String keyword,
                                        @RequestParam(required = false, defaultValue = "") String filterType,
                                        @RequestParam(required = false, defaultValue = "") String language,
                                        @RequestParam(required = false, defaultValue = "") String createYear,
                                        @RequestParam int pageNum,
                                        @RequestParam(required = false, defaultValue = "hobby_match") String sortType,
                                        @RequestParam(required = false, defaultValue = "true") boolean reverse,
                                        @RequestParam boolean isKeyChanged,
                                        HttpSession session){
        String webUsername = null;
        if (session.getAttribute("webUsername") != null){
            webUsername = session.getAttribute("webUsername").toString();
        }
        List<RepoVO> resultList = repoModelImpl.getSearchResult(keyword, sortType, filterType,
                language, createYear, pageNum, reverse, webUsername);
        if (session.getAttribute("webUsername") != null){
            HashSet<String> staredRepo = (HashSet<String>) session.getAttribute("staredRepo");
            for (RepoVO vo:resultList){
                if (staredRepo.contains(vo.getOwnerName()+"/"+vo.getReponame())){
                    vo.setIsStared(true);
                }
            }
        }
        int totalSearchPage = 0;
        if (isKeyChanged){
            totalSearchPage = repoModelImpl.getSearchPage(keyword,filterType,language,createYear);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",totalSearchPage);
        map.put("currentPage",pageNum);
        map.put("repoList",resultList);
        return map;
    }


    @RequestMapping(value = "/{ownername:.+}/{reponame:.+}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRepoInfo(@PathVariable String ownername,
                                           @PathVariable String reponame,
                                           HttpSession session) {
        RepoVO basicInfo = repoModelImpl.getRepoBasicInfo(ownername, reponame);
        if (session.getAttribute("webUsername") != null){
            HashSet<String> staredRepo = (HashSet<String>) session.getAttribute("staredRepo");

            if (staredRepo.contains(basicInfo.getOwnerName()+"/"+basicInfo.getReponame())){
                basicInfo.setIsStared(true);
            }

        }
        RadarChart radarChart = repoModelImpl.getRepoRadarChart(ownername, reponame);
        List<SimpleRepoVO> relatedRepo = repoModelImpl.getRelatedRepo(ownername, reponame);

        Map<String, Object> repoDetailInfo = new HashMap<>();
        repoDetailInfo.put("basicInfo", basicInfo);
        repoDetailInfo.put("radarChart", radarChart);
        repoDetailInfo.put("relatedRepo", relatedRepo);

        return repoDetailInfo;
    }

    @RequestMapping(value = "/star")
    @ResponseBody
    public boolean star(@RequestParam String ownername,@RequestParam String reponame,
                        HttpSession session){
        if (session.getAttribute("webUsername") == null){   //not logined yet
            return false;
        }
        else {
            String webUsername = session.getAttribute("webUsername").toString();
            boolean result = hobbyModelImpl.starRepo(ownername,reponame,webUsername);
            if (result){
                HashSet<String> staredUser = (HashSet<String>) session.getAttribute("staredRepo");
                staredUser.add(ownername+"/"+reponame);
            }
            return result;
        }
    }

    @RequestMapping(value = "/unstar")
    @ResponseBody
    public boolean unStar(@RequestParam String ownername,@RequestParam String reponame,
                          HttpSession session){
        if (session.getAttribute("webUsername") == null){   //not logined yet
            return false;
        }
        else {
            String webUsername = session.getAttribute("webUsername").toString();
            boolean result = hobbyModelImpl.unstarRepo(ownername,reponame,webUsername);
            if (result){
                HashSet<String> staredUser = (HashSet<String>) session.getAttribute("staredRepo");
                staredUser.remove(ownername+"/"+reponame);
            }
            return result;
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
