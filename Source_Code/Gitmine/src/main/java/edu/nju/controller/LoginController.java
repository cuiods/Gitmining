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
    public String addNewUser(@Validated WebUser webUser, BindingResult bindingResult){
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

}
