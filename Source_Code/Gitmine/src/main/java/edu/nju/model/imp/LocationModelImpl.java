package edu.nju.model.imp;

import java.awt.*;

/**
 * Created by darxan on 2016/6/8.
 */
public class LocationModelImpl {


    public void geoCode(String location){

    }

    public void calculateDistance(){
    }


    private class LocationGetter{
        private static final String geocodeUrl =
                "http://maps.google.com/maps/api/geocode/json?sensor=false&address=langtang&language=en";
        private static final String language = "en";
        private static final String form = "json";
        private static final String apiKey = "";

        public String getURL(String location){
            return null;
        }
    }

    private class Calculator{
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
