package edu.nju.model.pojo;

import java.util.List;

/**
 * Created by darxan on 2016/6/1.
 */
public class RelationVO {
    private String type;
    private String group;
    private String name;
    private List<String> depends;

    public static final String REPO = "repo";
    public static final String USER = "user";

    public RelationVO(String group, String type, String name, List<String> depends) {
        this.group = group;
        this.type = type;
        this.name = name;
        this.depends = depends;
    }
    public RelationVO(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDepends() {
        return depends;
    }

    public void setDepends(List<String> depends) {
        this.depends = depends;
    }
}
