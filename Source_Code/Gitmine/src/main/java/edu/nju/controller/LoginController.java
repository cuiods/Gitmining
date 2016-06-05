package edu.nju.controller;

import edu.nju.common.Const;
import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.model.service.HobbyModelService;
import edu.nju.model.service.LoginModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

/**
 * Created by cuihao on 2016/5/4.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginModelService loginModelImpl;

    @Resource
    private HobbyModelService hobbyModelService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public boolean register(@RequestParam String username, @RequestParam String password,
                            @RequestParam String email,
                            HttpSession session){
        boolean result = loginModelImpl.register(username, password, email);
        if (result){
            session.setAttribute("webUsername",username);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * if login success, save current user information to session and redirect to the recommend page;<br>
     * else back to login page
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public boolean login(@RequestParam String username, @RequestParam String password,
                        HttpSession session){
        boolean loginResult = loginModelImpl.login(username, password);
        if (loginResult){
            //add user information to session scope for aop to use
            session.setAttribute("webUsername", username);
            HashSet<String> staredRepo = hobbyModelService.getStaredReponame(username);
            HashSet<String> staredUser = hobbyModelService.getStaredUsername(username);
            int staredRepoPage = staredRepo.size()/ Const.ITEMS_PER_PAGE;
            if (staredRepo.size()%Const.ITEMS_PER_PAGE > 0){
                staredRepoPage++;
            }
            int staredUserPage = staredUser.size()/Const.ITEMS_PER_PAGE;
            if (staredUser.size()%Const.ITEMS_PER_PAGE > 0){
                staredUserPage++;
            }
            session.setAttribute("staredRepo",staredRepo);
            session.setAttribute("staredUser",staredUser);
            session.setAttribute("staredRepoPage",staredRepoPage);
            session.setAttribute("staredUserPage",staredUserPage);
        }
        return loginResult;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public boolean logout(HttpSession session){
        if (session.getAttribute("webUsername") != null){
            session.removeAttribute("webUsername");
            return true;
        }
        else {
            return false;
        }
    }

    @RequestMapping("/name")
    @ResponseBody
    public String getLoginName(HttpSession session){
        Object user = session.getAttribute("webUsername");
        if (user!=null) return (String) user;
        else return "";
    }

//    @RequestMapping(value = "/interest", method = RequestMethod.GET)
//    @ResponseBody
//    public String selectHobby(){
//
//        return "selectHobby";
//    }

    @RequestMapping(value = "/hobby", method = RequestMethod.POST)
    @ResponseBody
    public boolean submitHobby(@RequestBody SecRegisterLabelEntity labelEntity,
                               HttpSession session){

        if (session.getAttribute("webUsername") == null){
            return  false;
        }
        else {
            String webUsername = session.getAttribute("webUsername").toString();
            labelEntity.setRegisterLogin(webUsername);
            return loginModelImpl.initHobby(labelEntity);
        }
    }

}
