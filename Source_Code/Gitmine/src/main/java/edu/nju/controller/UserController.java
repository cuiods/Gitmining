package edu.nju.controller;

import edu.nju.entity.TblUser;
import edu.nju.model.service.UserModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cuihao on 2016/5/4.
 * this is the controller for user part
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserModelService userModelImpl;

    public UserController() {
    }

    @RequestMapping("/home")
    public String home(Model model){
        //todo recommend

        return "user/recommend";
    }

    @RequestMapping("/search")
    public String getSearchResult(@RequestParam String keyword, @RequestParam String sortType,
                                  @RequestParam int pageNum, Model model){
        List<TblUser> resultList = userModelImpl.getSearchResult(keyword, pageNum);

        //todo sort according to the web user's hobbies

        model.addAttribute("list", resultList);
        return "user/list";
    }

    @RequestMapping("/{username}")
    public String getUserBasicInfo(@PathVariable String username, Model model){
        model.addAttribute("basicInfo", userModelImpl.getUserBasicInfo(username));
        return "user/detail";
    }

    public UserModelService getUserModelImpl() {
        return userModelImpl;
    }

    public void setUserModelImpl(UserModelService userModelImpl) {
        this.userModelImpl = userModelImpl;
    }
}
