package edu.nju.model.imp;

import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblRepo;
import edu.nju.model.pojo.SimpleChart;
import edu.nju.model.service.RepoModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/12.
 * this is the model part for repo. this class is invoked by controller
 */
@Service
public class RepoModelImpl implements RepoModelService {

    @Resource
    private RepoDaoService repoDaoImpl;

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
        return null;
    }

    public List<TblRepo> getRelatedRepo(String owername, String reponame) {
        return null;
    }

    public List<TblRepo> getSearchResult(String keyword, String sortType, String filterType, String language, String createYear) {
        return null;
    }

    public TblRepo getRepoBasicInfo(String ownername, String reponame) {
        return null;
    }

    public SimpleChart getRepoRadarChart(String ownername, String reponame) {
        return null;
    }

    public SimpleChart[] getRepoCommitCharts(String ownername, String reponame) {
        return new SimpleChart[0];
    }
}
