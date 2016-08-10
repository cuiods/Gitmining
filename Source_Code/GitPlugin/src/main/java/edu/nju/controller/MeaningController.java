package edu.nju.controller;

import edu.nju.service.MeaningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * keyword of news and common keywords
 */
@Controller
@RequestMapping("/meaning")
public class MeaningController {

    @Resource
    private MeaningService meaningService;

    @RequestMapping("/news/keyword")
    @ResponseBody
    List<String> keywordsOfNews(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        return null;
    }

    @RequestMapping("/news/motion")
    @ResponseBody
    double positiveNews(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        return 0;
    }

    @RequestMapping("/comment/common")
    @ResponseBody
    Map<String, Long> commonComments(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        return null;
    }

    @RequestMapping("/comment/motion")
    @ResponseBody
    double positiveComments(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        return 0;
    }
}
