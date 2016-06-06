package edu.nju.dao.impl;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.entity.UserCountryEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Harry on 2016/6/6.
 */
@Repository
public class LocationDaoImpl implements LocationDaoService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<UserCountryEntity> getStatCountry() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserCountryEntity ");
        List<UserCountryEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public void saveCountry(UserCountryEntity entity) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("INSERT INTO user_country (country, number) VALUES (:cou, :val) ");
        query.setString("cou",entity.getCountry());
        query.setInteger("val",entity.getNumber());
        query.executeUpdate();
        session.close();
    }

    @Override
    public void deleteCountry(String countryName) {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("DELETE FROM user_country WHERE country = :cou ");
        query.setString("cou",countryName);
        query.executeUpdate();
        session.close();
    }

    @Override
    public void deleteAllCountries() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("DELETE FROM user_country ");
        query.executeUpdate();
        session.close();
    }
}
