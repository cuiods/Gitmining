package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.common.VOConvertor;
import edu.nju.common.json.JsonNodeParser;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.*;
import edu.nju.model.service.RepoModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Harry on 2016/5/12.
 * this is the model part for repo. this class is invoked by controller
 */
@Service
public class RepoModelImpl implements RepoModelService {

    @Resource
    private RepoDaoService repoDaoImpl;

    @Resource
    private JsonNodeParser jsonNodeParser;

    @Resource
    private RepoRadarImpl repoRadarImpl;

    @Resource
    private VOConvertor voConvertor;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    public RepoModelImpl() {
    }

    public RepoDaoService getRepoDaoImpl() {
        return repoDaoImpl;
    }

    public void setRepoDaoImpl(RepoDaoService repoDaoImpl) {
        this.repoDaoImpl = repoDaoImpl;
    }

    public List<RepoVO> getRecommendRepo(String webUsername) {
        //todo
        return null;
    }

    public List<RepoVO> getPopularRepo() {
        List<TblRepo> tblRepoList = repoDaoImpl.getRepos(SortType.Repo_Star, true, 0, 5);
        List<RepoVO> voList = new ArrayList<>();
        for (TblRepo tblRepo: tblRepoList){
            RepoVO vo = voConvertor.convert(tblRepo);
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

    public List<RepoVO> getRelatedRepo(String ownername, String reponame) {
        //todo
        return null;
    }

    @Override
    public List<RepoVO> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum) {
        List<TblRepo> tblRepoList = repoDaoImpl.getRepos(sortType, isDesc, offset, maxNum);
        List<RepoVO> voList = new ArrayList<>();
        for (TblRepo tblRepo:tblRepoList){
            RepoVO vo = voConvertor.convert(tblRepo);
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
        Calendar calendar = null;
        try {
            Date date = dateFormat.parse(createYear);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        } catch (ParseException e) {
            //e.printStackTrace();
            System.out.println("parse year error! the format of 'createYear' is not correct");
        }

        SortType sort = SortTypeBuilder.getSortType(sortType);
        List<TblRepo> list = repoDaoImpl.getSearchResult(keyword, offset, maxNum, sort, reverse, filterType,
                language, calendar);

        List<RepoVO> voList = new ArrayList<>();

        for (TblRepo tblRepo: list){
            RepoVO vo = voConvertor.convert(tblRepo);
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public int getSearchPage(String keyword, String filterType, String language, String createYear) {
        //todo
        return 0;
    }

    public RepoVO getRepoBasicInfo(String ownername, String reponame) {
        TblRepo tblRepo = repoDaoImpl.getRepoBasicInfo(ownername, reponame);
        return voConvertor.convert(tblRepo);
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
    public SimpleChart[] getPunchCard(String ownername, String reponame) {
        return jsonNodeParser.getPunchCard(ownername, reponame);
    }


}
