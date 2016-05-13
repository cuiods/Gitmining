package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.UserModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/12.
 * this class is the model implementation for user part
 */
@Service
public class UserModelImpl implements UserModelService {

    @Resource
    private UserDaoService userDaoImpl;

    public UserModelImpl() {
    }

    public List<TblUser> getRecommendUser(String webUsername) {
        return null;
    }

    public List<TblUser> getRelatedUser(String username) {
        return null;
    }

    public List<TblUser> getPopularUser() {
        List<TblUser> result;
        SortType sortType = SortType.User_Follored;
        boolean isDesc = true;
        int offset = 0;//todo change recommend each time
        int maxNum = Const.ITEMS_PER_PAGE;
        result = userDaoImpl.getUsers(sortType, isDesc, offset, maxNum);
        return result;
    }

    public List<TblUser> getSearchResult(String keyword, int pageNum) {
        int offset = (pageNum-1)* Const.ITEMS_PER_PAGE;
        if (offset<0){
            offset = 0;
        }
        List<TblUser> list = userDaoImpl.searchUserByLoginName(keyword, offset, Const.ITEMS_PER_PAGE);
        return list;
    }

    public TblUser getUserBasicInfo(String username) {
        return userDaoImpl.findUserByLoginName(username);
    }

    public SimpleChart getUserRadarChart(String username) {
        return null;
    }

    public UserDaoService getUserDaoImpl() {
        return userDaoImpl;
    }

    public void setUserDaoImpl(UserDaoService userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }
}
