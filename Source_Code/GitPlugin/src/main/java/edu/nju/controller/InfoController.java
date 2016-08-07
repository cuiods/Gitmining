package edu.nju.controller;

import edu.nju.service.InfoService;
import edu.nju.vo.Comment;
import edu.nju.vo.News;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * info controller including comments and news of a repository
 * @author cuihao
 */
@Controller
@RequestMapping("/info")
public class InfoController {

    @Resource
    private InfoService infoService;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseBody
    public Comment getCommentByName(@RequestParam("owner") String owner, @RequestParam("name") String name) {
        return infoService.getCommentsByName(owner, name);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @ResponseBody
    public News getNewsByName(@RequestParam("owner") String owner, @RequestParam("owner") String name) {
        return infoService.getNewsByName(owner, name);
    }
}
