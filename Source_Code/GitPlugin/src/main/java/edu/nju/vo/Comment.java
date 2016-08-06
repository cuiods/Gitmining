package edu.nju.vo;

import edu.nju.entity.CommentsEntity;

import java.util.List;

/**
 * comment vo
 * @author cuihao
 */
public class Comment {
    private String owner;
    private String name;
    private List<CommentsEntity> entities;

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

    public List<CommentsEntity> getEntities() {
        return entities;
    }

    public void setEntities(List<CommentsEntity> entities) {
        this.entities = entities;
    }
}
