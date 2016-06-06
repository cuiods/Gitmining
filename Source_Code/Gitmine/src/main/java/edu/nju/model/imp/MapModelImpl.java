package edu.nju.model.imp;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.dao.service.SecUserDaoService;
import edu.nju.entity.UserCountryEntity;
import edu.nju.model.pojo.MapVO;
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

    private List<MapVO> countriesDistributionVOS;

    public List<MapVO> getUserDistribution(){
        if(userCountryEntities==null){
            _readDistribution();
        }
        if(countriesDistributionVOS==null){
            synchronized (this){
                if(countriesDistributionVOS==null){
                    countriesDistributionVOS = new ArrayList<>(countries.length);
                    userCountryEntities.forEach(po->countriesDistributionVOS.add(new MapVO(po)));
                }
            }
        }
        return countriesDistributionVOS;
    }


    private synchronized void  _readDistribution(){
        if(userCountryEntities==null){
            userCountryEntities = locationDaoService.getStatCountry();
        }
    }

    public void recalculate(){
        distribution = new HashMap<>(countries.length);
        final List<String> locations = secUserDaoService.getAllUserLocation();
        locations.forEach( location->match(location) );
        //transfer map to entity
        userCountryEntities = new ArrayList<>(countries.length);
        for ( String location:countries) {
            UserCountryEntity entity = new UserCountryEntity();
            entity.setCountry(location);
            Integer number = distribution.get(location);
            entity.setNumber(number==null?0:number);
            userCountryEntities.add(entity);
        }
        locationDaoService.deleteAllCountries();
        locationDaoService.saveCountry(userCountryEntities);
        return;
    }


    /**
     *
     * @param location location must be lower case.
     */
    private void match(final String location){

        String lowerCase = location.toLowerCase();
        if(isChinese(lowerCase)){
            _increment(stringChina);
            System.out.print("China: ");
        }else if(isAmerican(lowerCase)){
            _increment(stringAmerica);
            System.out.print("America: ");
        }else{
            String country = _gerMostMatchCountry(lowerCase);
            _increment(country);
            System.out.print(country+": ");
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
     * @param location must be lower case
     * @return if exists one and only one country match,return the country's name,or reutn null.
     * return String case sensitive.
     */
    private String _gerMostMatchCountry(String location){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < countries.length; i++) {
            if(location.contains(countriesLowerCase[i])){
                result.add(i);
            }
        }
        return result.size()==1?countries[result.get(0)]:null;
    }

    /**
     *
     * @param location lowercase please.
     * @return
     */
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

    /**
     * @param location lowercase please.
     * @return
     */
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
    private static final String stringChina = "China";
    private static final String stringChinaLowerCase = stringChina.toLowerCase();
    private static final String stringAmerica = "United States of America";
    private static final String stringAmericaLowerCase = stringAmerica.toLowerCase();
    private static final String[] countries = {
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

    private static final   String[] countriesLowerCase;
    static {
        countriesLowerCase = new String[countries.length];
        for (int i = 0; i < countries.length; i++) {
            countriesLowerCase[i] = countries[i].toLowerCase();
        }
    }
    private static final String[] statesAmerica = {
            "america",
            "alabama",
            "alaska",
            "arizona",
            "arkansas",
            "california",
            "colorado",
            "connectict",
            "delaware",
            "florida",
            "georgia",
            "hawaii",
            "idaho",
            "illinois",
            "indiana",
            "iowa",
            "kansas",
            "kentucky",
            "lousiana",
            "maine",
            "maryland",
            "massachusetts",
            "michigan",
            "minnesota",
            "mississippi",
            "missouri",
            "montana",
            "nebraska",
            "nevada",
            "new hampshire",
            "new jersey",
            "new mexico",
            "new york",
            "north carolina",
            "north dakota",
            "ohio",
            "oklahoma",
            "oregon",
            "pennsylvania",
            "rhode island",
            "south carolina",
            "south dakota",
            "tennessee",
            "texas",
            "utah",
            "vermont",
            "virginia",
            "washington",
            "west virginia",
            "wisconsin",
            "wyoming",
    };


    private static final String[] provinceChina = {
            "china",
            "anhui",
            "beijing",
            "chongqing",
            "fujian",
            "gansu",
            "guangdong",
            "guangxi",
            "guizhou",
            "hainan",
            "hebei",
            "heilongjiang",
            "henan",
            "hong kong",
            "hubei",
            "hunan",
            "jiangsu",
            "jiangxi",
            "jilin",
            "liaoning",
            "macau",
            "inner mongol (neimenggu)",
            "ningxia",
            "qinghai",
            "shandong",
            "shanxi",
            "shanxi",
            "shanghai",
            "sichuan",
            "taiwan",
            "tianjin",
            "tibet      (xizang)",
            "sinkiang(xinjiang)",
            "yunnan",
            "zhejiang",
    };
}
