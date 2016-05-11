package edu.nju.controller;

import edu.nju.dao.service.LoginDaoService;
import edu.nju.model.WebUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by cuihao on 2016/5/4.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginDaoService loginImpl;

    public LoginDaoService getLoginImpl() {
        return loginImpl;
    }

    public void setLoginImpl(LoginDaoService loginImpl) {
        this.loginImpl = loginImpl;
    }



    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("webUser", new WebUser());
        return "register";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNewUser(@Valid WebUser webUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "register";
        }
        boolean registerResult = loginImpl.register(webUser.getUserName(), webUser.getPassword(), webUser.getEmail());
        if (registerResult){
            return "registersuccess";
        }
        else {
            return "registerfail";
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model){
        //todo protext the password from leaking? security?
        model.addAttribute("webUser", new WebUser());
        return "login";
    }

    /**
     * if login success, save current user information to session and redirect to the recommend page;<br>
     * else back to login page
     * @param webUser
     * @param model
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String login(@RequestParam(value = "webUser") WebUser webUser, Model model){
        boolean loginResult = loginImpl.login(webUser.getUserName(), webUser.getPassword());
        if (loginResult){
            //todo add user information to session scope for aop to use

            return "redirect:/repo/home";   //default to repo page
        }
        else {
            return "login";
        }
    }

}
