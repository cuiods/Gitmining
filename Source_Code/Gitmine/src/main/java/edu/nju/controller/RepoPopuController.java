package edu.nju.controller;

import edu.nju.model.service.RepoPopuModelService;
import edu.nju.model.service.RepoStatsService;
import edu.nju.model.statistic.RegressionLine;
import edu.nju.model.statistic.StarRegressionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * repository popularity controller
 */
@Controller
@RequestMapping("/popularity")
public class RepoPopuController {
    @Resource
    private RepoPopuModelService repoPopuModelService;


    @RequestMapping("/language")
    @ResponseBody
    public Map<String,List> statLanguagePopu() {
        return repoPopuModelService.statLanguagePopularity();
    }

    @RequestMapping("/star")
    @ResponseBody
    public Map<String, List> statStar() {
        return repoPopuModelService.statStarRelation();
    }

    @RequestMapping("/languageRate")
    @ResponseBody
    public Map<String, List> statLanguageRate() {
        return repoPopuModelService.statLanguageRate();
    }

    @RequestMapping("/languageYearRate")
    @ResponseBody
    public List<List> statLanguageYearRate() {
        return repoPopuModelService.statLanguageYearRate();
    }

    @RequestMapping("/languageRateYear")
    @ResponseBody
    public List<List> statLanguageRateYear() {
        return repoPopuModelService.statLanguageRateYear();
    }

    @RequestMapping("/fieldNumber")
    @ResponseBody
    public List statFieldNumber() {
        return repoPopuModelService.statFieldNumber();
    }

    @RequestMapping("/fieldNumberBox")
    @ResponseBody
    public List statFieldNumberBox() {
        return repoPopuModelService.statFieldNumberBox();
    }

    @RequestMapping("/fieldCreate")
    @ResponseBody
    public List statFieldCreateAt() {
        return repoPopuModelService.statFieldCreateAt();
    }

    @RequestMapping("/personFollower")
    @ResponseBody
    public List statSpecailFollower() {
        return repoPopuModelService.statSpecialFollower();
    }

    @RequestMapping("/personCompare")
    @ResponseBody
    public List statCompareFollower() {
        return repoPopuModelService.statsCompareFollower();
    }

    @RequestMapping("/personRate")
    @ResponseBody
    public List<List> statFollowerRate() {
        return repoPopuModelService.statSpecialFollowRate();
    }

    @RequestMapping("/personSuper")
    @ResponseBody
    public Map<String,List> statFollowerSuper() {
        return repoPopuModelService.statFollowerSuper();
    }

    @RequestMapping("/vaLanguage")
    @ResponseBody
    public List vaLanguage() {
        return repoPopuModelService.vaLanguage();
    }

    @RequestMapping("/vaField")
    @ResponseBody
    public List vaField() {
        return repoPopuModelService.vaField();
    }

    @RequestMapping("/vaPerson")
    @ResponseBody
    public List vaPerson() {
        return repoPopuModelService.vaPerson();
    }

    @RequestMapping("/vaLanguageData")
    @ResponseBody
    public List<List<Integer>> vaLanguageData() {
        return repoPopuModelService.vaLanguageData();
    }

    @RequestMapping("/vaFieldData")
    @ResponseBody
    public List<List<Integer>> vaFieldData() {
        return repoPopuModelService.vaFieldData();
    }

    @RequestMapping("/vaPersonData")
    @ResponseBody
    public List<List<Integer>> vaPersonData() {
        return repoPopuModelService.vaPersonData();
    }

    @RequestMapping("/rate")
    @ResponseBody
    public double popuRate(@RequestParam String repoOwner, @RequestParam String repoName) {
        return repoPopuModelService.popularRate(repoOwner,repoName);
    }

    @RequestMapping("/followerRegression")
    @ResponseBody
    public List<Object[]> followerRegression() {
        return repoPopuModelService.regressionFollower();
    }

}
