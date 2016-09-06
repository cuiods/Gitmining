package edu.nju.dao;

import edu.nju.entity.SecRepoEntity;

import java.util.List;

/**
 *
 * @author cuihao
 */
public interface PopularDao {

    /**
     * get popular rate
     * @param owner owner of repo
     * @param name name of repo
     * @return new popular rate
     */
    double getPopularRate(String owner, String name);

    /**
     * get repos which have similar charts to this repo
     * @param owner owner of repo
     * @param name name of repo
     * @return list of similar repositories
     */
    List<SecRepoEntity> getSimilarRepos(String owner, String name);
}
