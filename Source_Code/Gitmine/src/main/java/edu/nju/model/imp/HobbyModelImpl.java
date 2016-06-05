package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.VOConvertor;
import edu.nju.dao.service.RegisterDaoService;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.entity.SecRegisterLabelEntity;
import edu.nju.entity.SecRepoEntity;
import edu.nju.entity.SecRepoLabelEntity;
import edu.nju.entity.SecUserEntity;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleRepoVO;
import edu.nju.model.pojo.UserVO;
import edu.nju.model.service.HobbyModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Harry on 2016/5/29.
 */
@Service
public class HobbyModelImpl implements HobbyModelService {

    @Resource
    private RegisterDaoService registerDaoService;

    @Resource
    private SecRepoDaoService repoDaoService;

    @Resource
    private VOConvertor voConvertor;

    @Resource
    private LabelCalculator calculator;

    @Override
    public boolean starRepo(String ownername, String reponame, String webUsername) {
        boolean result = registerDaoService.starRepo(webUsername,ownername,reponame);
        if (result){
            SecRepoLabelEntity repoLabelEntity = repoDaoService.getRepoLabel(ownername,reponame);
            SecRegisterLabelEntity registerLabelEntity = registerDaoService.getRegisterInterest(webUsername);
            if (repoLabelEntity != null){
                registerDaoService.saveOrUpdateRegisterInterest(calculator.starRepoCompute(registerLabelEntity,repoLabelEntity));
            }
        }
        return result;
    }

    @Override
    public boolean unstarRepo(String ownername, String reponame, String webUsername) {
        boolean result = registerDaoService.unStarRepo(webUsername,ownername,reponame);
        if (result){
            SecRepoLabelEntity repoLabelEntity = repoDaoService.getRepoLabel(ownername,reponame);
            SecRegisterLabelEntity registerLabelEntity = registerDaoService.getRegisterInterest(webUsername);
            if (repoLabelEntity != null){
                registerDaoService.saveOrUpdateRegisterInterest(calculator.unstarRepoCompute(registerLabelEntity,repoLabelEntity));
            }
        }
        return result;
    }

    @Override
    public int getTotalRepoStarPage(String webUsername) {
        long cnt = registerDaoService.getStaredRepoCount(webUsername);
        int page = (int)cnt/Const.ITEMS_PER_PAGE;
        if (cnt%Const.ITEMS_PER_PAGE > 0){
            page ++;
        }
        return page;
    }

    @Override
    public boolean starUser(String username, String webUsername) {
        return registerDaoService.starUser(username,webUsername);
    }

    @Override
    public boolean unStarUser(String username, String webUsername) {
        return registerDaoService.unStarUser(username,webUsername);
    }

    @Override
    public List<UserVO> getStaredUsers(String webUsername, int page) {
        List<SecUserEntity> list = registerDaoService.getStaredUsers(webUsername,(page-1)* Const.ITEMS_PER_PAGE,Const.ITEMS_PER_PAGE);
        List<UserVO> vos = new ArrayList<>();
        for (SecUserEntity userEntity: list){
            vos.add(voConvertor.convert(userEntity));
        }
        return vos;
    }

    @Override
    public int getTotalUserStarPage(String webUsername) {
        long cnt = registerDaoService.getStaredUserCount(webUsername);
        int page = (int)cnt/Const.ITEMS_PER_PAGE;
        if (cnt%Const.ITEMS_PER_PAGE > 0){
            page ++;
        }
        return page;
    }

    @Override
    public List<RepoVO> getStaredRepos(String webUsername, int page) {
        List<SecRepoEntity> list = registerDaoService.getStaredRepos(webUsername,(page-1)* Const.ITEMS_PER_PAGE,Const.ITEMS_PER_PAGE);
        List<RepoVO> vos = new ArrayList<>();
        for (SecRepoEntity repoEntity: list){
            vos.add(voConvertor.convert(repoEntity));
        }
        return vos;
    }

    @Override
    public HashSet<String> getStaredReponame(String webUsername) {
        return null;
    }

    @Override
    public HashSet<String> getStaredUsername(String webUsername) {
        return null;
    }
}
