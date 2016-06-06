package edu.nju.model.imp;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.UserCountryEntity;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darxan on 2016/6/6.
 */
public class MapModelImpl {

    @Resource
    private LocationDaoService locationDaoService;
    @Resource
    private SecUserDaoService secUserDaoService;

    public Map<String,Integer> getUserDistribution(){
        if(distribution==null){
            _readDistribution();
        }
        return distribution;
    }

    private synchronized void  _readDistribution(){
        if(distribution==null){
            userCountryEntities = locationDaoService.getStatCountry();
            distribution = new HashMap<>(countries.length);
            userCountryEntities.forEach((userCountryEntity->
                distribution.put(userCountryEntity.getCountry(),userCountryEntity.getNumber())
            ));
        }
    }

    public void recalculate(){
        distribution = new HashMap<>(countries.length);
        userCountryEntities = new ArrayList<>(countries.length);
        final List<String> locations = secUserDaoService.getAllUserLocation();
        locations.forEach((location)->{match(location);});
    }


    private void match(final String location){
        assert distribution!=null;
        if(isChinese(location)){
        }else if(isAmerican(location)){
        }
    }


    private void increment(String location){

    }

    public static void main(String args[]){

    }


    private boolean isAmerican(String location){
        return false;
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private  static boolean _isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public  static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (_isChinese(c)) {
                return true;
            }
        }
        return false;
    }


    private List<UserCountryEntity> userCountryEntities = null;
    private static Map<String,Integer> distribution = null;
    private static final String stringChina = "china";
    private static final String stringAmerica = "United States of America";
    private final String[] countries = {
                 "Afghanistan",
                 "Angola",
                 "Albania",
                 "United Arab Emirates",
                 "Argentina",
                 "Armenia",
                 "French Southern and Antarctic Lands",
                 "Australia",
                 "Austria",
                 "Azerbaijan",
                 "Burundi",
                 "Belgium",
                 "Benin",
                 "Burkina Faso",
                 "Bangladesh",
                 "Bulgaria",
                 "The Bahamas",
                 "Bosnia and Herzegovina",
                 "Belarus",
                 "Belize",
                 "Bermuda",
                 "Bolivia",
                 "Brazil",
                 "Brunei",
                 "Bhutan",
                 "Botswana",
                 "Central African Republic",
                 "Canada",
                 "Switzerland",
                 "Chile",
                 "China",
                 "Ivory Coast",
                 "Cameroon",
                 "Democratic Republic of the Congo",
                 "Republic of the Congo",
                 "Colombia",
                 "Costa Rica",
                 "Cuba",
                 "Northern Cyprus",
                 "Cyprus",
                 "Czech Republic",
                 "Germany",
                 "Djibouti",
                 "Denmark",
                 "Dominican Republic",
                 "Algeria",
                 "Ecuador",
                 "Egypt",
                 "Eritrea",
                 "Spain",
                 "Estonia",
                 "Ethiopia",
                 "Finland",
                 "Fiji",
                 "Falkland Islands",
                 "France",
                 "Gabon",
                 "United Kingdom",
                 "Georgia",
                 "Ghana",
                 "Guinea",
                 "Gambia",
                 "Guinea Bissau",
                 "Equatorial Guinea",
                 "Greece",
                 "Greenland",
                 "Guatemala",
                 "French Guiana",
                 "Guyana",
                 "Honduras",
                 "Croatia",
                 "Haiti",
                 "Hungary",
                 "Indonesia",
                 "India",
                 "Ireland",
                 "Iran",
                 "Iraq",
                 "Iceland",
                 "Israel",
                 "Italy",
                 "Jamaica",
                 "Jordan",
                 "Japan",
                 "Kazakhstan",
                 "Kenya",
                 "Kyrgyzstan",
                 "Cambodia",
                 "South Korea",
                 "Kosovo",
                 "Kuwait",
                 "Laos",
                 "Lebanon",
                 "Liberia",
                 "Libya",
                 "Sri Lanka",
                 "Lesotho",
                 "Lithuania",
                 "Luxembourg",
                 "Latvia",
                 "Morocco",
                 "Moldova",
                 "Madagascar",
                 "Mexico",
                 "Macedonia",
                 "Mali",
                 "Myanmar",
                 "Montenegro",
                 "Mongolia",
                 "Mozambique",
                 "Mauritania",
                 "Malawi",
                 "Malaysia",
                 "Namibia",
                 "New Caledonia",
                 "Niger",
                 "Nigeria",
                 "Nicaragua",
                 "Netherlands",
                 "Norway",
                 "Nepal",
                 "New Zealand",
                 "Oman",
                 "Pakistan",
                 "Panama",
                 "Peru",
                 "Philippines",
                 "Papua New Guinea",
                 "Poland",
                 "Puerto Rico",
                 "North Korea",
                 "Portugal",
                 "Paraguay",
                 "Qatar",
                 "Romania",
                 "Russia",
                 "Rwanda",
                 "Western Sahara",
                 "Saudi Arabia",
                 "Sudan",
                 "South Sudan",
                 "Senegal",
                 "Solomon Islands",
                 "Sierra Leone",
                 "El Salvador",
                 "Somaliland",
                 "Somalia",
                 "Republic of Serbia",
                 "Suriname",
                 "Slovakia",
                 "Slovenia",
                 "Sweden",
                 "Swaziland",
                 "Syria",
                 "Chad",
                 "Togo",
                 "Thailand",
                 "Tajikistan",
                 "Turkmenistan",
                 "East Timor",
                 "Trinidad and Tobago",
                 "Tunisia",
                 "Turkey",
                 "United Republic of Tanzania",
                 "Uganda",
                 "Ukraine",
                 "Uruguay",
                 "United States of America",
                 "Uzbekistan",
                 "Venezuela",
                 "Vietnam",
                 "Vanuatu",
                 "West Bank",
                 "Yemen",
                 "South Africa",
                 "Zambia",
                 "Zimbabwe",
    };
}
