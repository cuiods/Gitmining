package edu.nju.controller;

import edu.nju.dao.service.UserDaoService;
import edu.nju.model.service.ExampleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by cuihao on 2016/5/8.
 */
@Controller
public class ExampleController {
    @Resource
    ExampleService exampleService;
    @Resource
    UserDaoService userDaoService;

    @RequestMapping("/example")
    public String login(@RequestParam String userName) {
        userDaoService.getUserTotalCount();
        if (exampleService.findUserByName(userName) == null) {
            return "userInfo";
        }
        return "success";
    }
}
