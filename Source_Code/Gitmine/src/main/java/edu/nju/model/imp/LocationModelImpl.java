package edu.nju.model.imp;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.UserLocationEntity;
import edu.nju.model.service.LocationModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by darxan on 2016/6/8.
 */
@Service
public class LocationModelImpl implements LocationModelService{

    @Resource
    private LocationDaoService locationDaoService;

    private static final double WIDTH = 5;


    private List<UserLocationEntity> _getNeibors(double longtitude ,double latitude){
        return locationDaoService.filterByArea(longtitude-WIDTH,longtitude+WIDTH,latitude-WIDTH,latitude+WIDTH);
    }

    public List<UserLocationEntity>  getNeighbors(String name,double longtitude ,double latitude){
       return _getNeibors(longtitude,latitude);
    }


    public List<UserLocationEntity>  getNeighbors(String name){
        UserLocationEntity userLocationEntity = null;
        if(userLocationEntity==null){
            return null;
        }
        return _getNeibors(userLocationEntity.getLongitude(),userLocationEntity.getLatitude());
    }

}
