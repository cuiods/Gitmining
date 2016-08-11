package edu.nju.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import edu.nju.service.MeaningService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * key words of news
     * @param owner owner of the repository
     * @param name name of the repository
     * @return list of key words
     */
    @RequestMapping("/news/keyword")
    @ResponseBody
    List<String> keywordsOfNews(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        List<String> list = new ArrayList<>();
        try {
            list =  meaningService.keywordsOfNews(owner,name);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * mean positive number of news
     * @param owner owner of the repository
     * @param name name of the repository
     * @return mean positive number of news (0-1)
     *      positive: return_value; negative: 1-return_value
     */
    @RequestMapping("/news/motion")
    @ResponseBody
    double positiveNews(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        double result = 0;
        try {
            result = meaningService.positiveNews(owner,name);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * common comments of the repository
     * @param owner
     * @param name
     * @return
     */
    @RequestMapping("/comment/common")
    @ResponseBody
    Map<String, List> commonComments(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        Map<String,List> map = new HashMap<>();
        try {
            map = meaningService.commonComments(owner,name);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/comment/motion")
    @ResponseBody
    double positiveComments(@RequestParam("owner") String owner, @RequestParam("name")String name) {
        double result = 0;
        try {
            result = meaningService.positiveComments(owner, name);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
