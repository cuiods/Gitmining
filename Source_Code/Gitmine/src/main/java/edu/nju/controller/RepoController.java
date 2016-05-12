package edu.nju.controller;

import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by cuihao on 2016/5/4.
 *
 */
@Controller
@RequestMapping("/repo")
public class RepoController {

    @Resource
    private RepoModelService repoModelImpl;

    @RequestMapping(value = "/home")
    public String home(Model model, HttpSession session){
        //todo get current user from session scope and generate recommend content
        if (session.getAttribute("username") == null){  //not login yet
            //todo
        }
        else {  //has login

        }

        return "repo/recommend";
    }

    @RequestMapping(value = "/search")
    public String getSearchResult(@RequestParam String keyword, @RequestParam String sortType,
                                  @RequestParam String filterType, @RequestParam String language,
                                  @RequestParam String createYear, @RequestParam int pageNum,
                                  @RequestParam boolean reverse, Model model){
        List<TblRepo> resultList = repoModelImpl.getSearchResult(keyword, sortType, filterType,
                language, createYear, pageNum, reverse);

        //todo use the web user hobby to resort the result and put the items match the user hobby on the top

        model.addAttribute("list", resultList);
        return "repo/list";
    }


    @RequestMapping(value = "/{ownername}/{reponame}", method = RequestMethod.GET)
    @ResponseBody
    public String getRepoBasicInfo(@PathVariable String ownername, @PathVariable String reponame, Model model) {
        model.addAttribute("basicInfo", repoModelImpl.getRepoBasicInfo(ownername, reponame));
        model.addAttribute("radarChart", repoModelImpl.getRepoRadarChart(ownername, reponame));

        return "repo/detail";
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
