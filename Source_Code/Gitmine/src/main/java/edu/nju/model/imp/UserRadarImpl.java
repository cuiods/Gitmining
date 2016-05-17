package edu.nju.model.imp;

import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.TblUser;
import edu.nju.model.pojo.RadarChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Harry on 2016/5/16.
 * compute user radar chart
 */
@Service
public class UserRadarImpl {

    private UserDaoService userDaoImpl;


    private double maxRepo;
    private double minRepo;
    private double maxGist;
    private double minGist;
    private double maxFollower;
    private double minFollower;
    private double maxActive;
    private double minActive;
    private double maxValue;
    private double minValue;

    @Autowired
    public UserRadarImpl(UserDaoService userDaoImpl) {
        this.userDaoImpl = userDaoImpl;

        maxRepo = Math.log(userDaoImpl.getMaxRepos()+1);
        minRepo=0;
        maxGist = Math.log(userDaoImpl.getMaxGists()+1);
        minGist = 0;
        maxFollower = Math.log(userDaoImpl.getMaxFollower()+1);
        minFollower = 0;
        maxActive = Math.log(userDaoImpl.getMaxUserContribute()+1);
        minActive = 0;
        maxValue = Math.log(userDaoImpl.getMaxUserValue()+1);
        minValue = 0;
    }

    public RadarChart getUserRadar(String username){
        String [] field = {"repository", "gist", "follower", "active", "value"};
        double [] value = new double[field.length];
        TblUser user = userDaoImpl.findUserByLoginName(username);
        if (user!=null){
            value[0] = (Math.log(user.getPublicRepo()+1)-minRepo)/(maxRepo-minRepo);
            value[1] = (Math.log(user.getPublicGist()+1)-minGist)/(maxGist-minGist);
            value[2] = (Math.log(user.getFollower()+1)-minFollower)/(maxFollower-minFollower);
            value[3] = (Math.log(userDaoImpl.getUserContribute(username)+1)-minActive)/(maxActive-minActive);
            value[4] = (Math.log(userDaoImpl.getUserValue(username)+1)-minValue)/(maxValue-minValue);
        }
        else {  //return all 0.0

        }
        return new RadarChart(field, value);
    }
}
