package edu.nju.vo;

import edu.nju.entity.NewsEntity;
import edu.nju.entity.NewsOsEntity;

import java.util.List;

/**
 * news
 * @author cuihao
 */
public class News {
    private String owner;
    private String name;
    private List<NewsOsEntity> entities;

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

    public List<NewsOsEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<NewsOsEntity> entities) {
        this.entities = entities;
    }
}
