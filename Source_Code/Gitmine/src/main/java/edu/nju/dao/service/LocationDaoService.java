package edu.nju.dao.service;

import edu.nju.entity.UserCountryEntity;

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
     * @param entity
     * @return
     */
    public void saveCountry(UserCountryEntity entity);

    /**
     * delete a country
     * @param countryName
     */
    public void deleteCountry(String countryName);

    /**
     * delete all countries
     */
    public void deleteAllCountries();
}
