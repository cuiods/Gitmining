package edu.nju.controller;

import edu.nju.dao.service.RegisterDaoService;
import edu.nju.model.WebUser;
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
    private RegisterDaoService loginImpl;

    public RegisterDaoService getLoginImpl() {
        return loginImpl;
    }

    public void setLoginImpl(RegisterDaoService loginImpl) {
        this.loginImpl = loginImpl;
    }



    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("webUser", new WebUser());
        model.addAttribute("message", "");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addNewUser(@Valid WebUser webUser, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "register";
        }
        boolean registerResult = loginImpl.register(webUser.getUserName(), webUser.getPassword(), webUser.getEmail());
        if (registerResult){
            //todo add a new entry to hobby database

            return "registerSuccess";
        }
        else {
            model.addAttribute("message", "the username has exist");
            return "register";
        }
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
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password,
                        Model model, HttpSession session){
        if (!loginImpl.existName(username)){
            model.addAttribute("message", "the user do not exist");
        }
        boolean loginResult = loginImpl.login(username, password);
        if (loginResult){
            //add user information to session scope for aop to use
            session.setAttribute("username", username);
            return "redirect:/repo/home";   //default to repo page
        }
        else {
            model.addAttribute("message", "the password is wrong");
            return "login";
        }
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

}
