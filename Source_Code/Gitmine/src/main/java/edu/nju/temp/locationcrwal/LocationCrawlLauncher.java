package edu.nju.temp.locationcrwal;

import edu.nju.dao.service.LocationDaoService;
import edu.nju.entity.UserLocationEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darxan on 2016/6/16.
 */
@Service
public class LocationCrawlLauncher {
    @Resource
    private LocationDaoService locationDaoService;



    private List<UserLocationEntity> entityList;

    public void generateLocations(){
        //select user list<> whoes location is still null
        List<Object[]> userNameLocations
                = locationDaoService.getAllUserLocation();
        System.out.println("get all locations");
        entityList = new ArrayList<>(userNameLocations.size());
        //be careful the limit rate.
        //for each element of the list,
        //get location and save.
        int i = 0;
        int times = LocationGetter.getTimes();
        for(Object[] array: userNameLocations){

            boolean result = _generateAndSave(array);
            System.out.println(array[0]);
            System.out.println(array[1]);
            System.out.println(i);
            if(!result){
                System.err.println("failed");
            }
            if(i>=times){
                break;
            }
            i++;
        }

    }

    private boolean _generateAndSave(Object[] array){
        UserLocationEntity userLocationEntity
                = LocationGetter.geoCode(array[0],array[1]);
        if(userLocationEntity==null){
            return false;
        }
        entityList.add(userLocationEntity);
        return locationDaoService.insert(userLocationEntity);
    }





    public static void main(String[] args){

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:/META-INF/applicationContext.xml");
        LocationCrawlLauncher handler = context.getBean(LocationCrawlLauncher.class);
        handler.generateLocations();

    }



}
