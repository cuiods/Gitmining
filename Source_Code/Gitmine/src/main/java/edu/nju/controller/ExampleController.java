package edu.nju.controller;

import edu.nju.service.ExampleService;
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

    @RequestMapping("/example")
    public String login(@RequestParam String userName) {
        if (exampleService.findUserByName(userName) == null) {
            return "error";
        }
        return "success";
    }
}
