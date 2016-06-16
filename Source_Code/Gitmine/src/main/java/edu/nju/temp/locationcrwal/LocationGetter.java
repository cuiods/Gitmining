package edu.nju.temp.locationcrwal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.entity.UserLocationEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by darxan on 2016/6/16.
 */
public class LocationGetter {
    private static final String geocodeUrl =
            "http://dev.virtualearth.net/REST/v1/Locations?";

    private static final String[] keys = {
            "AsBW-ZxtTk3KvGxUQg7F6s7rom3FLdPcwZQnm54SjtxURGXQ2HK5dHWUYnQabCn-"
    };
    private static final int LIMIT = 2500;
    public static int getTimes(){
        return keys.length*LIMIT;
    }

    private static int i = -1;
    public static String _getKey(){
        i = (i+1)%keys.length;
        return keys[i];
    }
    private static String _getURL(String location){

        try{
            String urlEncode =  geocodeUrl+"&locality="+ URLEncoder.encode(location, "UTF-8")+"&key="+_getKey();
            return urlEncode;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }

    public static UserLocationEntity geoCode(Object name, Object location) {
        UserLocationEntity userLocationEntity = new UserLocationEntity();
        URL url;
        try{
            url = new URL(_getURL(location.toString()));
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(url);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        JsonNode status = jsonNode.get("statusDescription");


        if(status==null||!status.asText().equals("OK")){
            System.err.println("status is not okay!");
            return null;
        }
        JsonNode resources = jsonNode.get("resourceSets").get(0).get("resources");
        if(resources==null||resources.size()==0){
            System.err.println("no macth position");
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
