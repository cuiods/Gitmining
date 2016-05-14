package edu.nju.controller;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.model.pojo.WebUser;
import edu.nju.model.service.LoginModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihao on 2016/5/4.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginModelService loginModelImpl;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestParam String webUsername, @RequestParam String password,
                            @RequestParam String eamil){
        return loginModelImpl.register(webUsername, password, eamil);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("message", "");
        return "login";
    }

    /**
     * if login success, save current user information to session and redirect to the recommend page;<br>
     * else back to login page
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public boolean login(@RequestParam String username, @RequestParam String password,
                        HttpSession session){
        if (!loginModelImpl.existName(username)){
            return false;
        }
        boolean loginResult = loginModelImpl.login(username, password);
        if (loginResult){
            //add user information to session scope for aop to use
            session.setAttribute("username", username);
        }
        return loginResult;
    }

    @RequestMapping(value = "/interest", method = RequestMethod.GET)
    public String selectHobby(Model model){
        List<String> list = new ArrayList<String>();
        model.addAttribute("list", list);
        return "selectHobby";
    }

    @RequestMapping(value = "/interest", method = RequestMethod.POST)
    public String submitHobby(@RequestParam List list, Model model){
        //todo set hobby to database

        return "redirect:/repo/home";
    }

    public LoginModelService getLoginModelImpl() {
        return loginModelImpl;
    }

    public void setLoginModelImpl(LoginModelService loginModelImpl) {
        this.loginModelImpl = loginModelImpl;
    }
}
