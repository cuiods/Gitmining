package edu.nju.controller;

import edu.nju.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * just for test
 */
@Controller
@RequestMapping("/user")
public class HelloController {
    @RequestMapping(value="/add1.do")
    public String add(HttpServletRequest request){

        String userNumber = request.getParameter("userNumber");
        String passWord = request.getParameter("passWord");

        System.out.println("userNumber:"+userNumber+" passWord:"+passWord);

        return "user_add1";
    }

    @RequestMapping(value="/add2.do")
    public String add(@RequestParam(value="number")String userNumber, Integer passWord){

        System.out.println("userNumber:"+userNumber+" passWord:"+passWord);

        return "user_add2";
    }

    @RequestMapping(value="/add3.do")
    public String add3(UserInfo user){

        System.out.println(user);

        return "user_add3";
    }

    @RequestMapping(value="/add4.do")
    public String add4(Integer userId,Date userTime){

        System.out.println(userId);
        System.out.println(userTime);

        return "user_add4";
    }

    /**
     * 时间属性编辑器
     */
/*	@InitBinder
	public void initBinder(ServletRequestDataBinder bin){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		CustomDateEditor cust = new CustomDateEditor(sdf, true);


		bin.registerCustomEditor(Date.class,cust);
	}*/
}
