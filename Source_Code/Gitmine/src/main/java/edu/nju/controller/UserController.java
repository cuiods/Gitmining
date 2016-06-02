package edu.nju.controller;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.*;
import edu.nju.model.service.HobbyModelService;
import edu.nju.model.service.UserModelService;
import edu.nju.model.service.UserStatsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/5/4.
 * this is the controller for user part
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserModelService userModelImpl;

    @Resource
    private UserStatsService userStatsImpl;

    @Resource
    private HobbyModelService hobbyModelImpl;

    public UserController() {
    }

    @RequestMapping(value = "/home")
    @ResponseBody
    public Map home(HttpSession session){
        //todo get current user from session scope and generate recommend content

        Map<String, Object> result = new HashMap<>();

        List<UserVO> recommend;
        if (session.getAttribute("webUsername") == null){
            recommend = userModelImpl.getPopularUser();
        }
        else {
            String webUsername = (String) session.getAttribute("webUsername");
            recommend = userModelImpl.getRecommendUser(webUsername);
        }
        List<UserVO> mainList = userModelImpl.getUsers(SortType.User_Name,false,0,Const.ITEMS_PER_PAGE);
        result.put("userList", mainList);
        result.put("recommend", recommend);

        return result;
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map list(@RequestParam int pageNum,
                    @RequestParam(required = false, defaultValue = "user_name") String sortType,
                    @RequestParam(required = false, defaultValue = "false") boolean isDesc){
        Map<String,Object> map = new HashMap<>();
        long totalPage = userModelImpl.getTotalPage();
        List<UserVO> userVOs = null;
        if (pageNum<=totalPage){
            SortType type = SortTypeBuilder.getSortType(sortType);
            if (type == null){
                type = SortType.User_Name;
            }
            if (pageNum<1)  pageNum=1;
            userVOs = userModelImpl.getUsers(type,isDesc,(pageNum-1)*Const.ITEMS_PER_PAGE,Const.ITEMS_PER_PAGE);
        }
        map.put("totalPage", 10);
        map.put("currentPage", pageNum);
        map.put("userList", userVOs);
        return map;
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public List<UserVO> getSearchResult(@RequestParam String keyword,
                                  @RequestParam(required = false, defaultValue = "user_name") String sortType,
                                  @RequestParam(required = false,defaultValue = "false") boolean reverse,
                                  @RequestParam int pageNum){
        List<UserVO> resultList = userModelImpl.getSearchResult(keyword,sortType,pageNum,reverse);

        //todo sort according to the web user's hobbies

        return resultList;
    }

    @RequestMapping(value = "/{username:.+}")
    @ResponseBody
    public Map getUserInfo(@PathVariable String username){
        Map<String,Object> map = new HashMap<>();
        UserVO userVO = userModelImpl.getUserBasicInfo(username);
        RadarChart radarChart = userModelImpl.getUserRadarChart(username);

        map.put("basicInfo",userVO);
        map.put("radarChart",radarChart);
        return map;
    }

//    @RequestMapping(value = "/{username:.+}/relatedUser")
//    @ResponseBody
//    public SimpleChart getRelatedUsers(@PathVariable("username") String username,
//                                       @RequestParam(required = false,defaultValue = "30")int limitResulsts){
//        return userModelImpl.getRelatedUser(username, limitResulsts);
//    }

    @RequestMapping(value = "/{username:.+}/contribute")
    @ResponseBody
    public List getUserContribute(@PathVariable("username") String username,
                                  @RequestParam(required = false,defaultValue = "5") int maxResults){
        return userModelImpl.getContributeRepo(username, maxResults);
    }

    @RequestMapping(value = "/{username:.+}/subscribe")
    @ResponseBody
    public List getUserSubscribe(@PathVariable("username") String username,
                                 @RequestParam(required = false,defaultValue = "5")int maxResults){
        return userModelImpl.getSubscribeRepo(username,maxResults);
    }

    @RequestMapping(value = "/{username:.+}/relationGraph")
    @ResponseBody
    public List<RelationVO> getRelationGraph(@PathVariable("username") String username,
                                             @RequestParam(required = false,defaultValue = "10") int maxResults){
        return userModelImpl.getRelatedUser(username,maxResults);
    }

    @RequestMapping(value = "/statistic/type")
    @ResponseBody
    public SimpleChart statUserType(){
        return userStatsImpl.statsUserType();
    }

    @RequestMapping(value = "/statistic/public_repo")
    @ResponseBody
    public SimpleChart statUserRepos(){
        return userStatsImpl.statsUserRepo();
    }

    @RequestMapping(value = "/statistic/public_gist")
    @ResponseBody
    public SimpleChart statUserGists(){
        return userStatsImpl.statsUserGist();
    }

    @RequestMapping("/statistic/follower")
    @ResponseBody
    public SimpleChart statUserFollower(){
        return userStatsImpl.statsUserFollower();
    }

    @RequestMapping(value = "/statistic/create_at")
    @ResponseBody
    public SimpleChart statUserCreateTime(){
        return userStatsImpl.statsUserCreateTime();
    }

    @RequestMapping(value = "/statistic/email")
    @ResponseBody
    public SimpleChart statUserEmail(){
        return userStatsImpl.statsUserEmail();
    }

    @RequestMapping(value = "/statistic/company")
    @ResponseBody
    public SimpleChart statUserCompany(){
        return userStatsImpl.statsUserCompany();
    }
    @RequestMapping(value = "/star")
    @ResponseBody
    public boolean starUser(@RequestParam String username, HttpSession session){
        if (session.getAttribute("webUsername") == null){
            return false;
        }
        else {
            String webUsername = session.getAttribute("webUsername").toString();
            return hobbyModelImpl.starUser(username,webUsername);
        }
    }

    @RequestMapping(value = "/unstar")
    @ResponseBody
    public boolean unstarUser(@RequestParam String username, HttpSession session){
        if (session.getAttribute("webUsername") == null){
            return false;
        }
        else {
            String webUsername = session.getAttribute("webUsername").toString();
            return hobbyModelImpl.unStarUser(username,webUsername);
        }
    }
}
