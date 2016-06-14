package edu.nju.model.imp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.dao.service.LocationDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.UserLocationEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

        for(Object[] array: userNameLocations){
            _generateAndSave(array);
        }

    }

    private boolean _generateAndSave(Object[] array){
        UserLocationEntity userLocationEntity
                = LocationGetter.geoCode(array[0],array[1]);
        if(userLocationEntity==null){
            return false;
        }
        return entityList.add(userLocationEntity);
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
                "http://dev.virtualearth.net/REST/v1/Locations?";

        private static final String[] keys = {
            "AsBW-ZxtTk3KvGxUQg7F6s7rom3FLdPcwZQnm54SjtxURGXQ2HK5dHWUYnQabCn-"
        };
        private static int i = -1;
        public static String _getKey(){
            i = (i+1)%keys.length;
            return keys[i];
        }
        private static String _getURL(String location){
            return geocodeUrl+"&locality="+location+"&key="+_getKey();
        }

        public static UserLocationEntity geoCode(Object name,Object location) {
            UserLocationEntity userLocationEntity = new UserLocationEntity();

            URL url;
            try{
                url = new URL(_getURL(location.toString()));
            }catch (IOException e){
                return null;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode;
            try {
                jsonNode = objectMapper.readTree(url);
            }catch (IOException e){
                return null;
            }
            JsonNode status = jsonNode.get("statusDescription");


            if(status==null||!status.asText().equals("OK")){
                System.out.println("status is not okay!");
                return null;
            }
            JsonNode resources = jsonNode.get("resourceSets").get(0).get("resources");
            if(resources==null||resources.size()==0){
                System.out.println("no macth position");
                return null;
            }
            else if(resources.size()>1){
                System.out.println("conflict position.");
                return null;
            }

            JsonNode resource = resources.get(0);
            userLocationEntity.setCountry(resource.get("address").get("countryRegion").asText());
            userLocationEntity.setLocation(resource.get("name").asText());

            userLocationEntity.setLogin(name.toString());

            JsonNode coordinates = resource.get("point").get("coordinates");

            userLocationEntity.setLatitude(coordinates.get(1).asDouble());
            userLocationEntity.setLongitude(coordinates.get(0).asDouble());
            return userLocationEntity;
        }
    }

    public static void main(String[] args){

        UserLocationEntity userLocationEntity =
                LocationModelImpl.LocationGetter.geoCode("","jiangsu,nanjing");
        if(userLocationEntity==null){
            return;
        }
        System.out.println(userLocationEntity.getCountry());
        System.out.println(userLocationEntity.getLocation());
        System.out.println(userLocationEntity.getLongitude());
        System.out.println(userLocationEntity.getLatitude());

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
