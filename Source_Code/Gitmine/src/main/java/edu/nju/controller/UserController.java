package edu.nju.controller;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.pojo.UserVO;
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
        List<UserVO> relatedUser = userModelImpl.getRelatedUser(username);
        List<RepoVO> relatedRepo = userModelImpl.getRelatedRepo(username);

        map.put("basicInfo",userVO);
        map.put("radarChart",radarChart);
        map.put("relatedUser",relatedUser);
        map.put("relatedRepo", relatedRepo);
        return map;
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

    public UserModelService getUserModelImpl() {
        return userModelImpl;
    }

    public void setUserModelImpl(UserModelService userModelImpl) {
        this.userModelImpl = userModelImpl;
    }
}
