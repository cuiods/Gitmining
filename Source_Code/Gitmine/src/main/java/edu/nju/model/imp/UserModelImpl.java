package edu.nju.model.imp;

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
        return null;
    }

    public List<TblUser> getSearchResult(String keyword) {
        return null;
    }

    public TblUser getUserBasicInfo(String username) {
        return null;
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
