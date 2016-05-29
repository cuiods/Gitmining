package edu.nju.controller;

import edu.nju.model.pojo.SimpleRepoVO;
import edu.nju.model.service.HobbyModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2016/5/29.
 */
@Controller
@RequestMapping(value = "/favorite")
public class CollectionController {

    @Resource
    private HobbyModelService hobbyModelService;

    @RequestMapping(value = "/repos")
    @ResponseBody
    public List<SimpleRepoVO> getFavoriteRepos(@RequestParam(required = false,defaultValue = "1") int page, HttpSession session){
        if (session.getAttribute("webUsername") == null){
            return new ArrayList<>();
        }
        else {
            String webUsername = session.getAttribute("webUsername").toString();
            return hobbyModelService.getStaredRepos(webUsername,page);
        }
    }
}
