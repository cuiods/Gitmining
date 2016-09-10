package edu.nju.dao;

import edu.nju.entity.*;

import java.util.List;

/**
 * news and comments dao
 * @author cuihao
 */
public interface InfoDao {

    List<CommentsOsEntity> getCommentsByName(String repoName, int size, int page);

    List<NewsOsEntity> getNewsByName(String owner, String name, int size, int page);

    long getLastNewsTime(String owner, String name);

    SecRepoEntity getRepoByName(String owner, String name);
}
