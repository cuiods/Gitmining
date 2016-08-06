package edu.nju.dao;

import edu.nju.entity.CommentsEntity;
import edu.nju.entity.NewsEntity;

import java.util.List;

/**
 * news and comments dao
 * @author cuihao
 */
public interface InfoDao {
    long getRepoId(String owner, String name);
    List<CommentsEntity> getCommentsByName(String owner, String name);
    List<NewsEntity> getNewsByName(String owner, String name);
}
