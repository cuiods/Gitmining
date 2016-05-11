package edu.nju.controller;

import edu.nju.controller.recommend.service.RepoRecommendService;
import edu.nju.dao.service.RepoDaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by cuihao on 2016/5/4.
 *
 */
@Controller
@RequestMapping("/repo")
public class RepoController {
    @Resource
    private RepoDaoService repoDaoImpl;

    @Resource
    private RepoRecommendService repoRecommendImpl;

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
    public String getSearchResult(@RequestParam String keyword, String sortType){

        return "repo/list";
    }


    @RequestMapping(value = "/{ownername}/{reponame}", method = RequestMethod.GET)
    @ResponseBody
    public String getRepoInfo(@PathVariable String ownername, @PathVariable String reponame, Model model) {
        model.addAttribute("repo", repoDaoImpl.getRepoBasicInfo(ownername, reponame));
        return "repo/detail";
    }

    public RepoDaoService getRepoDaoImpl() {
        return repoDaoImpl;
    }

    public void setRepoDaoImpl(RepoDaoService repoDaoImpl) {
        this.repoDaoImpl = repoDaoImpl;
    }


}
