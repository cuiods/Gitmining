package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.common.VOConvertor;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecUserEntity;
import edu.nju.model.pojo.*;
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
    private SecUserDaoService userDao;

    @Resource
    private VOConvertor voConvertor;

    @Resource
    private UserRadarImpl userRadar;

    public UserModelImpl() {
    }

    public List<UserVO> getRecommendUser(String webUsername, int offset, int maxResults) {
        List<SecUserEntity> list = userDao.getRecommendUser(webUsername,offset,maxResults);
        List<UserVO> voList = new ArrayList<>();
        for (SecUserEntity entity:list){
            voList.add(voConvertor.convert(entity));
        }
        int size = list.size();
        if (size<Const.RECOMMEND_COUNT){
            List<UserVO> popUsers = getPopularUser(offset,maxResults-size);
            voList.addAll(popUsers);
        }
        return voList;
    }

    @Override
    public List<RelationVO> getRelatedUser(String username, int limitResults) {
        List<Object[]> list = userDao.getRelatedUser(username,limitResults);
        List<RelationVO> relationVOList = new ArrayList<>();
        RelationVO root = new RelationVO(username,"user",username,new ArrayList<>());
        relationVOList.add(root);
        List<String> userDepends = new ArrayList<>();
        userDepends.add(username);
        for (Object[] item:list){
            String group = item[0].toString();
            String [] repos = item[1].toString().split(",");

            List<String> repoDepends = new ArrayList<>();
            repoDepends.add(group);
            relationVOList.add(new RelationVO(group,"user",group,userDepends));
            for (String repo: repos){
                relationVOList.add(new RelationVO(group,"repo",repo,repoDepends));
            }
        }

        return relationVOList;
    }

    @Override
    public List<SimpleRepoVO> getContributeRepo(String username, int maxResults) {
        List<SecRepoEntity> contriRepos = userDao.getUserContributeRepos(username,maxResults);
        //todo use user's hobby to resort the result!!!!!

        List<SimpleRepoVO> repoVOs = new ArrayList<>();
        for (SecRepoEntity repoEntity: contriRepos){
            repoVOs.add(voConvertor.simpleConvert(repoEntity));
        }
        return repoVOs;
    }

    @Override
    public List<SimpleRepoVO> getSubscribeRepo(String username,int maxResults){
        List<SecRepoEntity> subscribeRepos = userDao.getUserSubscribeRepos(username,maxResults);
        List<SimpleRepoVO> vos = new ArrayList<>();
        for (SecRepoEntity repo:subscribeRepos){
            vos.add(voConvertor.simpleConvert(repo));
        }
        return vos;
    }

    @Override
    public List<UserVO> getPopularUser(int offset,int maxResults) {
        List<UserVO> result = new ArrayList<>();
        SortType sortType = SortType.User_Follored;
        boolean isDesc = true;
        List<SecUserEntity> userEntityList = userDao.getUsers(sortType, isDesc, offset, maxResults);
        for (SecUserEntity userEntity : userEntityList) {
            result.add(voConvertor.convert(userEntity));
        }
        return result;
    }

    @Override
    public int getTotalPage() {
        long totalCount = userDao.getTotalCount();
        int page = (int)totalCount/Const.ITEMS_PER_PAGE;
        if (totalCount%Const.ITEMS_PER_PAGE > 0){
            page++;
        }
        return page;
    }

    @Override
    public int getSearchPage(String keyword) {
        long cnt = userDao.getSearchCount(keyword);
        int page = (int)cnt/Const.ITEMS_PER_PAGE;
        if (cnt%Const.ITEMS_PER_PAGE > 0){
            page ++;
        }
        return page;
    }

    @Override
    public List<UserVO> getUsers(SortType sortType, boolean isDesc, int offset, int maxNum) {
        List<SecUserEntity> userEntityList = userDao.getUsers(sortType,isDesc,offset,maxNum);
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
        List<SecUserEntity> userEntityList = userDao.getSearchResult(keyword,type,reverse,offset,Const.ITEMS_PER_PAGE);
        List<UserVO> userVOs = new ArrayList<>();
        for (SecUserEntity userEntity:userEntityList){
            userVOs.add(voConvertor.convert(userEntity));
        }
        return userVOs;
    }

    @Override
    public UserVO getUserBasicInfo(String username) {
        SecUserEntity userEntity = userDao.getUserBasicInfo(username);
        return voConvertor.convert(userEntity);
    }

    @Override
    public RadarChart getUserRadarChart(String username) {
        return userRadar.getUserRadar(username);
    }

    @Override
    public SimpleChart getUserLanguage(String login) {
        List<Object []> list = userDao.getUserLanguage(login);
        String [] field = new String[list.size()];
        long [] value = new long[list.size()];
        for (int i=0;i<list.size();i++){
            Object [] item = list.get(i);
            field[i] = item[0].toString();
            value[i] = Long.valueOf(item[1].toString());
        }
        return new SimpleChart(field,value);
    }
}
