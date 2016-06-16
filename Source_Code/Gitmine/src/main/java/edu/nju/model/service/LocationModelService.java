package edu.nju.model.service;

import edu.nju.entity.UserLocationEntity;

import java.util.List;

/**
 * Created by darxan on 2016/6/16.
 */
public interface LocationModelService {
    /**
     * by user's country, or if it's too bigger,choose its province
     * get all the user's  in this country/province
     * calculate distance.
     * show in map
     * @param latitude
     * @param longtitude
     * @param name user name for update the user location
     * @return
     */
    public List<UserLocationEntity> getNeighbors(String name,double longtitude , double latitude);

    /**
     * by user's country, or if it's too bigger,choose its province
     * get all the user's  in this country/province
     * calculate distance.
     * show in map
     * @param name username
     * @return if not exists ,return null
     */
    public List<UserLocationEntity> getNeighbors(String name);
}
