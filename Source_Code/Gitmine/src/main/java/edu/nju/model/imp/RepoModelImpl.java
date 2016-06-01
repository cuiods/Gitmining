package edu.nju.model.imp;

import com.fasterxml.jackson.databind.JsonNode;
import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.common.VOConvertor;
import edu.nju.common.json.JsonNodeParser;
import edu.nju.dao.service.SecRepoDaoService;
import edu.nju.entity.SecRepoEntity;
import edu.nju.model.pojo.*;
import edu.nju.model.service.RepoModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Harry on 2016/5/12.
 * this is the model part for repo. this class is invoked by controller
 */
@Service
public class RepoModelImpl implements RepoModelService {

    @Resource
    private SecRepoDaoService repoDaoImpl;

    @Resource
    private JsonNodeParser jsonNodeParser;

    @Resource
    private RepoRadarImpl repoRadarImpl;

    @Resource
    private VOConvertor voConvertor;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    public RepoModelImpl() {
    }

    public List<RepoVO> getRecommendRepo(String webUsername) {
        //todo
        return null;
    }

    public List<RepoVO> getPopularRepo() {
        List<SecRepoEntity> repoList = repoDaoImpl.getRepos(SortType.Repo_Star, true, 0, 5);
        List<RepoVO> voList = new ArrayList<>();
        for (SecRepoEntity repo: repoList){
            RepoVO vo = voConvertor.convert(repo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int getTotalPage() {
        long totalItem = repoDaoImpl.getTotalCount();
        int page = (int)totalItem/Const.ITEMS_PER_PAGE;
        if (totalItem%Const.ITEMS_PER_PAGE >0 ){
            page++;
        }
        return page;
    }

    public List<SimpleRepoVO> getRelatedRepo(String ownername, String reponame) {
        List<SimpleRepoVO> vos = new ArrayList<>();
        List<SecRepoEntity> entities = repoDaoImpl.getRelatedRepo(ownername,reponame);
        for (SecRepoEntity entity: entities){
            vos.add(voConvertor.simpleConvert(entity));
        }
        return vos;
    }

    @Override
    public List<RepoVO> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum) {
        List<SecRepoEntity> repoList = repoDaoImpl.getRepos(sortType, isDesc, offset, maxNum);
        List<RepoVO> voList = new ArrayList<>();
        for (SecRepoEntity repoEntity:repoList){
            RepoVO vo = voConvertor.convert(repoEntity);
            voList.add(vo);
        }
        return voList;
    }

    public List<RepoVO> getSearchResult(String keyword, String sortType, String filterType,
                                         String language, String createYear, int pageNum, boolean reverse) {

        if (filterType.toLowerCase().equals("all")){
            filterType = null;
        }

        if (language.toLowerCase().equals("all")){
            language = null;
        }

        int offset = (pageNum-1)* Const.ITEMS_PER_PAGE;
        if (offset<0){
            offset = 0;
        }
        int maxNum = Const.ITEMS_PER_PAGE;

        SortType sort = SortTypeBuilder.getSortType(sortType);
        List<SecRepoEntity> list = repoDaoImpl.getSearchResult(keyword, offset, maxNum, sort, reverse, filterType,
                language, createYear);

        List<RepoVO> voList = new ArrayList<>();

        for (SecRepoEntity repoEntity: list){
            RepoVO vo = voConvertor.convert(repoEntity);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int getSearchPage(String keyword, String filterType, String language, String createYear) {
        long count = repoDaoImpl.getSearchCount(keyword,filterType,language,createYear);
        long page = count/Const.ITEMS_PER_PAGE;
        if (count%Const.ITEMS_PER_PAGE != 0){
            page++;
        }
        return (int)page;
    }

    public RepoVO getRepoBasicInfo(String ownername, String reponame) {
        SecRepoEntity repoEntity = repoDaoImpl.getRepoBasicInfo(ownername, reponame);
        return voConvertor.convert(repoEntity);
    }

    public RadarChart getRepoRadarChart(String ownername, String reponame) {
        return repoRadarImpl.getRepoRadar(ownername, reponame);
    }

    @Override
    public Map<String, CommitChart> getCommitByContributor(String ownername, String reponame) {
        return jsonNodeParser.getCommitByContributors(ownername, reponame);
    }

    @Override
    public CodeFrequency getCodeFrequency(String ownername, String reponame) {
        return jsonNodeParser.getCodeFrequency(ownername, reponame);
    }

    @Override
    public JsonNode getPunchCard(String ownername, String reponame) {
        return jsonNodeParser.getPunchCard(ownername, reponame);
    }


}
