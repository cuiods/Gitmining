package edu.nju.model.imp;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.UserLocationEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by darxan on 2016/6/8.
 */
@Service
public class LocationModelImpl {

    @Resource
    private LocationDaoService locationDaoService;

    @Resource
    private SecUserDaoService secUserDaoService;


    private List<UserLocationEntity> entityList;

    public void generateLocations(){
        //select user list<> whoes location is still null
        List<Object[]> userNameLocations
                = locationDaoService.getAllUserLocation();
        entityList = new ArrayList<>(userNameLocations.size());
        //be careful the limit rate.
        //for each element of the list,
            //get location and save.
        try{
            for(Object[] array: userNameLocations){
                _generateAndSave(array);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void _generateAndSave(Object[] array) throws Exception{
        UserLocationEntity userLocationEntity
                = LocationGetter.geoCode(array[0],array[1]);
        entityList.add(userLocationEntity);
    }

    public void getNeighbors(String user){
        double latitudeUpper = 0;
        double latitudeDown = 0;
        double longtitudeUpper = 0;
        double longtitudeDown = 0;
        List<UserLocationEntity> neighbors = null;
        //by user's country, or if it's too bigger,choose its province
        //get all the user's  in this country/province
        //calculate distance.
        //show in map
    }


    private static class LocationGetter{
        private static final String geocodeUrl =
                "http://maps.google.com/maps/api/geocode/json?sensor=false&address=langtang&language=en";
        private static final String language = "en";
        private static final String form = "json";
        private static final String apiKey = "";

        private static String _getURL(String location){
            return null;
        }

        public static UserLocationEntity geoCode(Object name,Object location){
            UserLocationEntity userLocationEntity = new UserLocationEntity();
            return userLocationEntity;
        }
    }

    private class Calculator {
        private static final double EARTH_RADIUS = 6378.137;//地球半径
        private  double _rad(double d)
        {
            return d * Math.PI / 180.0;
        }

        public   double GetDistance(double lat1, double lng1, double lat2, double lng2)
        {
            double _radLat1 = _rad(lat1);
            double _radLat2 = _rad(lat2);
            double a = _radLat1 - _radLat2;
            double b = _rad(lng1) - _rad(lng2);

            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                    Math.cos(_radLat1)*Math.cos(_radLat2)*Math.pow(Math.sin(b/2),2)));
            s = s * EARTH_RADIUS;
            s = Math.round(s * 10000) / 10000;
            return s;
        }
    }

}
