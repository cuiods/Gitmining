package edu.nju.serviceImpl;

import edu.nju.dao.InfoDao;
import edu.nju.entity.CommentsEntity;
import edu.nju.entity.NewsEntity;
import edu.nju.entity.NewsOsEntity;
import edu.nju.service.InfoService;
import edu.nju.vo.Comment;
import edu.nju.vo.News;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * get comments and news vo
 */
@Service
public class InfoServiceImpl implements InfoService {

    @Resource
    private InfoDao infoDao;

    @Override
    public Comment getCommentsByName(String owner, String name, int size, int page) {
        List<CommentsEntity> entities = infoDao.getCommentsByName(owner, name, size, page);
        Comment comment = new Comment();
        comment.setName(name);
        comment.setOwner(owner);
        comment.setEntities(entities);
        return comment;
    }

    @Override
    public News getNewsByName(String owner, String name, int size, int page) {
        List<NewsOsEntity> entities = infoDao.getNewsByName(owner, name, size, page);
        News news = new News();
        news.setName(name);
        news.setOwner(owner);
        news.setEntities(entities);
        return news;
    }

    @Override
    public long getLastNewsTime(String owner, String name) {
        return infoDao.getLastNewsTime(owner, name);
    }
}
