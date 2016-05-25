package edu.nju.model.imp;

import edu.nju.dao.service.SecUserDaoService;
import edu.nju.dao.service.UserDaoService;
import edu.nju.entity.SecUserEntity;
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

    private SecUserDaoService secUserDaoService;

    private double maxRepo;
    private double minRepo;
    private double maxGist;
    private double minGist;
    private double maxFollower;
    private double minFollower;
    private double maxActive;
    private double minActive;
    private double maxFollowing;
    private double minFollowing;

    @Autowired
    public UserRadarImpl(SecUserDaoService secUserDaoService) {
        this.secUserDaoService = secUserDaoService;

        maxRepo = Math.log(secUserDaoService.getMaxRepos()+1);
        minRepo=0;
        maxGist = Math.log(secUserDaoService.getMaxGists()+1);
        minGist = 0;
        maxFollower = Math.log(secUserDaoService.getMaxFollower()+1);
        minFollower = 0;
        maxActive = Math.log(secUserDaoService.getMaxUserContribute()+1);
        minActive = 0;
        maxFollowing = Math.log(secUserDaoService.getMaxUserFollowing()+1);
        minFollowing = 0;
    }

    public RadarChart getUserRadar(String username){
        String [] field = {"repository", "gist", "follower", "active", "following"};
        double [] value = new double[field.length];
        SecUserEntity user = secUserDaoService.getUserBasicInfo(username);
        if (user!=null){
            value[0] = (Math.log(user.getPublicRepos()+1)-minRepo)/(maxRepo-minRepo);
            value[1] = (Math.log(user.getPublicGists()+1)-minGist)/(maxGist-minGist);
            value[2] = (Math.log(user.getFollowers()+1)-minFollower)/(maxFollower-minFollower);
            value[3] = (Math.log(secUserDaoService.getUserContribute(username)+1)-minActive)/(maxActive-minActive);
            value[4] = (Math.log(user.getFollowing()+1)-minFollowing)/(maxFollowing-minFollowing);
        }
        else {  //return all 0.0

        }
        return new RadarChart(field, value);
    }
}
