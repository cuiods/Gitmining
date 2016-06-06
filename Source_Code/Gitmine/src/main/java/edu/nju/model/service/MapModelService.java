package edu.nju.model.service;

import java.util.Map;

/**
 * Created by darxan on 2016/6/6.
 */
public interface MapModelService {
    /**
     * calculate the user's distribution.
     */
    public Map<String,Integer> recalculate();

    /**
     *
     * @return a map: key is country name,value is user's num.
     */
    public Map<String,Integer> getUserDistribution();
}
