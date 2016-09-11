package edu.nju.dao;

import edu.nju.entity.SecRepoEntity;

import java.util.List;
import java.util.Map;

/**
 * basic info of a repository
 * @author cuihao
 */
public interface RepoDao {
    Map<String,Integer> getRepoFollowers(String owner, String name);
    int getFollowerNum(String loginName);
    SecRepoEntity getRepo(String owner, String name);
    int[] getForkandSize(String owner,String name);
}
