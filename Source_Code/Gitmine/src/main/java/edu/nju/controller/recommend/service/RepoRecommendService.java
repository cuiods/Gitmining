package edu.nju.controller.recommend.service;

import edu.nju.entity.TblRepo;

import java.util.List;

/**
 * Created by Harry on 2016/5/11.
 */
public interface RepoRecommendService {

    /**
     * get the recommend repository for the given username
     * @param username
     * @return
     */
    public List<TblRepo> getRecommendRepo(String username);

    /**
     * if there is no user logined user, recommend the most popular repos
     * @return
     */
    public List<TblRepo> getPopularRepo();

    /**
     * get the related repository for the given repository
     * @param owername
     * @param reponame
     * @return
     */
    public List<TblRepo> getRelatedRepo(String owername, String reponame);
}
