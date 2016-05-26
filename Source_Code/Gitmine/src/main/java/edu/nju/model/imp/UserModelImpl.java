package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.common.VOConvertor;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.UserVO;
import edu.nju.model.service.UserModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2016/5/12.
 * this class is the model implementation for user part
 */
@Service
public class UserModelImpl implements UserModelService {

    @Resource
    private SecUserDaoService userDaoImpl;

    @Resource
    private VOConvertor voConvertor;

    @Resource
    private UserRadarImpl userRadar;

    public UserModelImpl() {
    }

    public List<UserVO> getRecommendUser(String webUsername) {
        //todo
        return null;
    }

    public List<UserVO> getRelatedUser(String username) {
        //todo
        return null;
    }

    @Override
    public List<RepoVO> getContributeRepo(String username) {
        // at most 10 repos, order by star
        List<SecRepoEntity> contriRepos = userDaoImpl.getUserContributerRepos(username);
        //todo use user's hobby to resort the result!!!!!



        List<RepoVO> repoVOs = new ArrayList<>();
        for (SecRepoEntity repoEntity: contriRepos){
            repoVOs.add(voConvertor.convert(repoEntity));
        }
        return repoVOs;
    }

    @Override
    public List<RepoVO> getSubscribeRepo(String username){
        //todo
        return null;
    }

    public List<UserVO> getPopularUser() {
        List<UserVO> result = new ArrayList<>();
        SortType sortType = SortType.User_Follored;
        boolean isDesc = true;
        int offset = 0;//todo change recommend each time
        int maxNum = Const.ITEMS_PER_PAGE;
        List<SecUserEntity> userEntityList = userDaoImpl.getUsers(sortType, isDesc, offset, maxNum);
        for (SecUserEntity userEntity: userEntityList){
            result.add(voConvertor.convert(userEntity));
        }
        return result;
    }

    @Override
    public int getTotalPage() {
        long totalCount = userDaoImpl.getTotalCount();
        int page = (int)totalCount/Const.ITEMS_PER_PAGE;
        if (totalCount%Const.ITEMS_PER_PAGE > 0){
            page++;
        }
        return page;
    }

    @Override
    public List<UserVO> getUsers(SortType sortType, boolean isDesc, int offset, int maxNum) {
        List<SecUserEntity> userEntityList = userDaoImpl.getUsers(sortType,isDesc,offset,maxNum);
        List<UserVO> userVOs = new ArrayList<>();
        for (SecUserEntity userEntity: userEntityList){
            userVOs.add(voConvertor.convert(userEntity));
        }
        return userVOs;
    }

    @Override
    public List<UserVO> getSearchResult(String keyword, String sortType, int pageNum, boolean reverse) {
        int offset = (pageNum-1)* Const.ITEMS_PER_PAGE;
        if (offset<0){
            offset = 0;
        }
        SortType type = SortTypeBuilder.getSortType(sortType);
        List<SecUserEntity> userEntityList = userDaoImpl.getSearchResult(keyword,type,reverse,offset,Const.ITEMS_PER_PAGE);
        List<UserVO> userVOs = new ArrayList<>();
        for (SecUserEntity userEntity:userEntityList){
            userVOs.add(voConvertor.convert(userEntity));
        }
        return userVOs;
    }

    public UserVO getUserBasicInfo(String username) {
        SecUserEntity userEntity = userDaoImpl.getUserBasicInfo(username);
        return voConvertor.convert(userEntity);
    }

    public RadarChart getUserRadarChart(String username) {
        return userRadar.getUserRadar(username);
    }

}
