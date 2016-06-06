package edu.nju.model.pojo;

import edu.nju.entity.UserCountryEntity;

/**
 * Created by darxan on 2016/6/6.
 */
public class MapVO {

    private String name;
    private int value;

    public MapVO(){}

    public MapVO(UserCountryEntity po){
        this(po.getNumber(),po.getCountry());
    }

    public MapVO(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
