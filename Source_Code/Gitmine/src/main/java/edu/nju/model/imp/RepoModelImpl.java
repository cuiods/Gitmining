package edu.nju.model.imp;

import edu.nju.common.Const;
import edu.nju.common.SortType;
import edu.nju.common.SortTypeBuilder;
import edu.nju.common.json.JsonNodeParser;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RadarChart;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    public RepoModelImpl() {
    }

    public RepoDaoService getRepoDaoImpl() {
        return repoDaoImpl;
    }

    public void setRepoDaoImpl(RepoDaoService repoDaoImpl) {
        this.repoDaoImpl = repoDaoImpl;
    }

    public List<TblRepo> getRecommendRepo(String webUsername) {
        return null;
    }

    public List<TblRepo> getPopularRepo() {
        List<TblUser> result;

        return null;
    }

    public List<TblRepo> getRelatedRepo(String ownername, String reponame) {
        return null;
    }

    @Override
    public List<TblRepo> getRepos(SortType sortType, boolean isDesc, int offset, int maxNum) {
        return repoDaoImpl.getRepos(sortType, isDesc, offset, maxNum);
    }

    public List<TblRepo> getSearchResult(String keyword, String sortType, String filterType,
                                         String language, String createYear, int pageNum, boolean reverse) {
        int offset = (pageNum-1)* Const.ITEMS_PER_PAGE;
        if (offset<0){
            offset = 0;
        }
        int maxNum = Const.ITEMS_PER_PAGE;
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        try {
            date = dateFormat.parse(createYear);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("parse year error! the format of 'createYear' is not correct");
        }
        SortType sort = SortTypeBuilder.getSortType(sortType);
        List<TblRepo> list = repoDaoImpl.getSearchResult(keyword, offset, maxNum, sort, reverse, filterType,
                language, calendar);
        return list;
    }

    public TblRepo getRepoBasicInfo(String ownername, String reponame) {
        return repoDaoImpl.getRepoBasicInfo(ownername, reponame);
    }

    public RadarChart getRepoRadarChart(String ownername, String reponame) {
        String [] field = {"size", "fork", "popular", "complex", "active"};
        double [] value = new double[5];
        value[0] = repoRadarImpl.getRadarSize(ownername, reponame);
        value[1] = repoRadarImpl.getRadarFork(ownername, reponame);
        value[2] = repoRadarImpl.getRadarPopular(ownername, reponame);
        value[3] = repoRadarImpl.getRadarComplex(ownername, reponame);
        value[4] = repoRadarImpl.getRadarActive(ownername, reponame);

        return new RadarChart(field, value);
    }

    public SimpleChart[] getRepoCommitCharts(String ownername, String reponame) {
        return null;
    }
}
