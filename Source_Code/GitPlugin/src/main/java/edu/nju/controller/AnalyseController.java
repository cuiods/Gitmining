package edu.nju.controller;

import edu.nju.service.AnalyseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author cuihao
 */
@Controller
@RequestMapping("/analyse")
public class AnalyseController {

    @Resource
    private AnalyseService analyseService;

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    @ResponseBody
    double getPopularRate(String owner, String name) {
        return analyseService.popularRate(owner, name);
    }
}
