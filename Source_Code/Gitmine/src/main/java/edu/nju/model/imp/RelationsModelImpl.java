package edu.nju.model.imp;

import edu.nju.common.SortType;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.RelationVO;
import edu.nju.model.service.RelationsModelService;
import edu.nju.model.service.RepoModelService;
import edu.nju.model.service.UserModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 2016/6/1.
 */
@Service
public class RelationsModelImpl implements RelationsModelService{

    @Resource
    private UserDaoService userDaoService;
    @Resource
    private RepoDaoService repoDaoService;
    @Resource
    private SecUserDaoService secUserDaoService;
    @Resource
    private SecRepoDaoService secRepoDaoService;

    public List<RelationVO> getRelationsByUser(String username){
        //get user own repos
        //ger user contribute repos
        List<RelationVO> result = new ArrayList<>();
        List<TblRepo> tblRepos = userDaoService.getUserOwnRepos(username, SortType.Repo_Star,0,100);
        tblRepos.forEach(tblRepo -> {
            result.add(new RelationVO(username,RelationVO.REPO,tblRepo.getName(),null));
        });

        //
        return result;
    }

    public List<RelationVO> getRelationsByRepo(String ownername, String reponame){
        return null;
    }
}
