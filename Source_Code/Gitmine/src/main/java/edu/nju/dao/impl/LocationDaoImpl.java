package edu.nju.dao.impl;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.entity.UserCountryEntity;
import edu.nju.entity.UserLocationEntity;
import org.hibernate.*;
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
    public void saveCountry(List<UserCountryEntity> entities) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            for (UserCountryEntity entity:entities){
                SQLQuery query = session.createSQLQuery("INSERT INTO user_country (country, number) VALUES (:cou, :val) ");
                query.setString("cou",entity.getCountry());
                query.setInteger("val",entity.getNumber());
                query.executeUpdate();
            }
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        } finally {
            session.close();
        }
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



    @Override
    public List<Object[]> getAllUserLocation() {
        Session session = sessionFactory.openSession();
        SQLQuery query = session.createSQLQuery("SELECT login, location FROM sec_user A WHERE login NOT IN (SELECT login FROM user_location ) ");
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public UserLocationEntity getUserLocationEntity(String login) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserLocationEntity where login = :log");
        query.setString("log",login);
        List<UserLocationEntity> list = query.list();
        if (list.size() > 0){
            return list.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean insert(UserLocationEntity entity) {
        Session session = sessionFactory.openSession();
        boolean result = false;
        SQLQuery query = session.createSQLQuery("INSERT INTO user_location (login,country,location,longitude,latitude) VALUES (:log, :cou, :loc, :lon, :lat)");
        query.setString("log",entity.getLogin());
        query.setString("cou",entity.getCountry());
        query.setString("loc",entity.getLocation());
        query.setDouble("lon",entity.getLongitude());
        query.setDouble("lat",entity.getLatitude());
        try{
            query.executeUpdate();
            result = true;
        }catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean update(UserLocationEntity entity) {
        Session session = sessionFactory.openSession();
        boolean result = false;
        SQLQuery query = session.createSQLQuery("UPDATE user_location SET country = :cou, location = :loc, longitude = :lon, latitude = :lat WHERE login = :log");
        query.setString("log",entity.getLogin());
        query.setString("cou",entity.getCountry());
        query.setString("loc",entity.getLocation());
        query.setDouble("lon",entity.getLongitude());
        query.setDouble("lat",entity.getLatitude());
        try{
            query.executeUpdate();
            result = true;
        }catch (HibernateException e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<UserLocationEntity> getEntityByCountry(String country) {
        Session session =sessionFactory.openSession();
        Query query = session.createQuery("from UserLocationEntity  where country = :cou ");
        query.setString("cou",country);
        List<UserLocationEntity> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<UserLocationEntity> filterByArea(double longitudeLow, double longitudeHigh, double latitudeLow, double latitudeHigh) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserLocationEntity where longitude >= :longlow and longitude <= :longhigh and latitude >= :latlow and latitude <= :lathigh ");
        query.setDouble("longlow",longitudeLow);
        query.setDouble("longhigh",longitudeHigh);
        query.setDouble("latlow",latitudeLow);
        query.setDouble("lathigh",latitudeHigh);
        List<UserLocationEntity> list = query.list();
        session.close();
        return list;
    }
}
