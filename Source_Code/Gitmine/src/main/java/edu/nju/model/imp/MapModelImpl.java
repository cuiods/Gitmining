package edu.nju.model.imp;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.UserCountryEntity;
import edu.nju.model.service.MapModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by darxan on 2016/6/6.
 */
@Service
public class MapModelImpl implements MapModelService{

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

    public Map<String,Integer> recalculate(){
        distribution = new HashMap<>(countries.length);

        final List<String> locations = secUserDaoService.getAllUserLocation();
        locations.forEach((location)->{match(location);});


        //transfer map to entity
        userCountryEntities = new ArrayList<>(countries.length);
        for (String location:countries) {
            UserCountryEntity entity = new UserCountryEntity();
            entity.setCountry(location);
            Integer number = distribution.get(location);
            entity.setNumber(number==null?0:number);
        }
        locationDaoService.saveCountry(userCountryEntities);
        return distribution;
    }


    private void match(final String location){
        assert distribution!=null;
        if(isChinese(location)){
            _increment(stringChina);
            System.out.print("China: ");
        }else if(isAmerican(location)){
            _increment(stringAmerica);
            System.out.print("America: ");
        }else{
            _increment(_gerMostMatchCountry(location));
            System.out.print("other: ");
        }
        System.out.println(location);
    }


    /**
     * if location==null no operation
     * or else increment the value correspondingly to the {location} in map:disctribution.
     * @param location
     */
    private void _increment(String location){
        if(location!=null){
            Integer number = distribution.get(location);
            distribution.put(location,number==null?1:number +1);
        }
        
    }



    /**
     * 
     * @param location
     * @return if exists one and only one country match,return the country's name,or reutn null.  
     */
    private String _gerMostMatchCountry(String location){
        ArrayList<String> result = new ArrayList<>();
        for(String country:countries){
            if(location.contains(country)){
                result.add(country);
            }
        }
        return result.size()==0?null:result.get(0);
    }

    private boolean isAmerican(String location){
        for(String state:statesAmerica){
            if(location.contains(state)){
                return true;
            }
        }
        return false;
    }

    // 根据Unicode编码完美的判断中文汉字和符号
    private   boolean _isChinese(char c) {
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
    private    boolean isChinese(String location) {
        for(String state:provinceChina){
            if(location.contains(state)){
                return true;
            }
        }
        char[] ch = location.toCharArray();
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

    private static final String[] statesAmerica = {
            "America",
            "Alabama",
            "Alaska",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connectict",
            "Delaware",
            "Florida",
            "Georgia",
            "Hawaii",
            "Idaho",
            "Illinois",
            "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Lousiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Mississippi",
            "Missouri",
            "Montana",
            "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming",
    };


    private static final String[] provinceChina = {
            "China",
            "Anhui",
            "Beijing",
            "Chongqing",
            "Fujian",
            "Gansu",
            "Guangdong",
            "Guangxi",
            "Guizhou",
            "Hainan",
            "Hebei",
            "Heilongjiang",
            "Henan",
            "Hong Kong",
            "Hubei",
            "Hunan",
            "Jiangsu",
            "Jiangxi",
            "Jilin",
            "Liaoning",
            "Macau",
            "Inner Mongol (Neimenggu)",
            "Ningxia",
            "Qinghai",
            "Shandong",
            "Shanxi",
            "Shanxi",
            "Shanghai",
            "Sichuan",
            "Taiwan",
            "Tianjin",
            "Tibet      (Xizang)",
            "Sinkiang(Xinjiang)",
            "Yunnan",
            "Zhejiang",
    };
}
