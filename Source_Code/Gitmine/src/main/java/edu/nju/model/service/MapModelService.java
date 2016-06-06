package edu.nju.model.service;

import edu.nju.model.pojo.MapVO;

import java.util.List;

/**
 * Created by darxan on 2016/6/6.
 */
public interface MapModelService {
    /**
     * calculate the user's distribution.
     */
    public void recalculate();

    /**
     *
     * @return a map: key is country name,value is user's num.
     */
    public List<MapVO> getUserDistribution();
}
