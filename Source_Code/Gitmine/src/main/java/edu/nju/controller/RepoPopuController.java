package edu.nju.controller;

import edu.nju.model.service.RepoPopuModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * repository popularity controller
 */
@Controller
@RequestMapping("/popularity")
public class RepoPopuController {
    @Resource
    public RepoPopuModelService repoPopuModelService;

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
}
