package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.VOConvertor;
import edu.nju.dao.service.RegisterDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleRepoVO;
import edu.nju.model.service.HobbyModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2016/5/29.
 */
@Service
public class HobbyModelImpl implements HobbyModelService {

    @Resource
    private RegisterDaoService registerDaoService;

    @Resource
    private VOConvertor voConvertor;

    @Override
    public boolean starRepo(String ownername, String reponame, String webUsername) {
        boolean result = registerDaoService.starRepo(webUsername,ownername,reponame);
        if (result){
            //todo change register label value in database
        }
        return result;
    }

    @Override
    public boolean unstarRepo(String ownername, String reponame, String webUsername) {
        boolean result = registerDaoService.unstarRepo(webUsername,ownername,reponame);
        if (result){
            //todo change register label value in database
        }
        return result;
    }

    @Override
    public int getTotalStarPage(String webUsername) {
        long cnt = registerDaoService.getSaredRepoCount(webUsername);
        int page = (int)cnt/Const.ITEMS_PER_PAGE;
        if (cnt%Const.ITEMS_PER_PAGE > 0){
            page ++;
        }
        return page;
    }

    @Override
    public List<SimpleRepoVO> getStaredRepos(String webUsername, int page) {
        List<SecRepoEntity> list = registerDaoService.getStaredRepos(webUsername,(page-1)* Const.ITEMS_PER_PAGE,Const.ITEMS_PER_PAGE);
        List<SimpleRepoVO> vos = new ArrayList<>();
        for (SecRepoEntity repoEntity: list){
            vos.add(voConvertor.simpleConvert(repoEntity));
        }
        return vos;
    }
}
