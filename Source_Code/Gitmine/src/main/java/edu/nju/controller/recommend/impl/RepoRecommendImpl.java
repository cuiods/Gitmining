package edu.nju.controller.recommend.impl;

import edu.nju.controller.recommend.service.RepoRecommendService;
import edu.nju.dao.service.RepoDaoService;
import edu.nju.entity.TblRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/5/11.
 */
@Service
public class RepoRecommendImpl implements RepoRecommendService {

    @Resource
    private RepoDaoService repoDaoImpl;

    public List<TblRepo> getRecommendRepo(String username) {
        return null;
    }

    public List<TblRepo> getPopularRepo() {
        return null;
    }

    public List<TblRepo> getRelatedRepo(String owername, String reponame) {
        return null;
    }
}
