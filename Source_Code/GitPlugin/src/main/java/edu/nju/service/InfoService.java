package edu.nju.service;

import edu.nju.vo.Comment;
import edu.nju.vo.News;

import java.util.List;

/**
 * get comments and news
 */
public interface InfoService {
    Comment getCommentsByName(String owner, String name,int size, int page);
    News getNewsByName(String owner, String name, int size, int page);
}
