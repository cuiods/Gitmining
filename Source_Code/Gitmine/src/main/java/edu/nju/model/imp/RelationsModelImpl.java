package edu.nju.model.imp;

import edu.nju.common.SortType;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RelationVO;
import edu.nju.model.service.RelationsModelService;
import edu.nju.model.service.RepoModelService;
import edu.nju.model.service.UserModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private List<RelationVO> result;
    private Map<String,RelationVO> userRelations;
    private Map<String,RelationVO> repoRelations;

    public List<RelationVO> getRelationsByUser(final String username){

        result = new ArrayList<>();
        userRelations = new HashMap<>();
        repoRelations = new HashMap<>();

        List<String> userDepend = new ArrayList<>();
        RelationVO userRelation = new RelationVO(username,RelationVO.USER,username,userDepend);
        result.add(userRelation);
        userRelations.put(username,userRelation);

        //user owner repositories
        List<TblRepo> userOwnRepos = userDaoService.getUserOwnRepos(username, SortType.Repo_Star,0,100);
        if(userOwnRepos.size()==0){
            throw  new NullPointerException();
        }
        userOwnRepos.forEach(tblRepo -> {
            List<String> depend = new ArrayList<>(1);
            final String fullname = tblRepo.getOwnerName()+"/"+tblRepo.getName();
            depend.add(username);
            RelationVO repoRelation = new RelationVO(username,RelationVO.REPO,fullname,depend);
             if(!repoRelations.containsKey(fullname)){
                 result.add(repoRelation);
                 repoRelations.put(fullname,repoRelation);
             }
            List<String> repoContributors =
                    repoDaoService.getRepoContributor(tblRepo.getOwnerName(),tblRepo.getName());
            repoContributors.forEach(contributor->{
                if(!userRelations.containsKey(contributor)){
                    List<String> contributorDepend = new ArrayList<>(1);
                    contributorDepend.add(fullname);
                    RelationVO userRelationVO = new RelationVO(contributor,RelationVO.USER,contributor,contributorDepend);
                    result.add(userRelationVO);
                    userRelations.put(contributor,userRelationVO);
                }

            });
        });

        //
        return result;
    }







    public List<RelationVO> getRelationsByRepo(final String ownername,final String reponame){
        return null;
    }
}
