package edu.nju.vo;

import edu.nju.entity.NewsEntity;

import java.util.List;

/**
 * news
 * @author cuihao
 */
public class News {
    private String owner;
    private String name;
    private List<NewsEntity> entities;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<NewsEntity> entities) {
        this.entities = entities;
    }
}
