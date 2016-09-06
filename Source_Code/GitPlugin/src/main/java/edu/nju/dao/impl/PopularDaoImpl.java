package edu.nju.dao.impl;

import edu.nju.dao.PopularDao;
import edu.nju.entity.SecRepoEntity;

import java.util.List;

/**
 * @author cuihao
 */
public class PopularDaoImpl implements PopularDao {
    @Override
    public double getPopularRate(String owner, String name) {
        return 0;
    }

    @Override
    public List<SecRepoEntity> getSimilarRepos(String owner, String name) {
        return null;
    }
}
