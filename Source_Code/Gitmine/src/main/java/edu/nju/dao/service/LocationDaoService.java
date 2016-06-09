package edu.nju.dao.service;

import edu.nju.entity.UserCountryEntity;
import edu.nju.entity.UserLocationEntity;

import java.util.List;

/**
 * Created by Harry on 2016/6/6.
 * statistic for the location of users
 */
public interface LocationDaoService {

    /**
     * get the count of users in each country
     * @return
     */
    public List<UserCountryEntity> getStatCountry();

    /**
     * save or update a country's user count
     * @param entities
     * @return
     */
    public void saveCountry(List<UserCountryEntity> entities);

    /**
     * delete a country
     * @param countryName
     */
    public void deleteCountry(String countryName);

    /**
     * delete all countries
     */
    public void deleteAllCountries();

    //================以下是有经纬度的location接口===========================//

//    /**
//     * get all the location from sec_user table
//     * @return a list of object[], each object[] has 2 elements, object[0] is user
//     *          login, object[1] is location.
//     */
//    public List<Object[]> getAllUserLocation();

}
