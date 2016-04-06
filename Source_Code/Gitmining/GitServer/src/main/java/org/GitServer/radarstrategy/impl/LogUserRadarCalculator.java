package org.GitServer.radarstrategy.impl;

import edu.nju.git.PO.UserPO;
import org.GitServer.radarstrategy.service.UserRadarService;

import java.util.List;

/**
 * Created by Harry on 2016/4/5.
 */
public class LogUserRadarCalculator implements UserRadarService {
    private static LogUserRadarCalculator uniqueInstance = null;

    public static LogUserRadarCalculator instance(List<UserPO> userList) {
        if (uniqueInstance == null){
            uniqueInstance = new LogUserRadarCalculator(userList);
        }
        return uniqueInstance;
    }

    private double RepoCountMax=Double.MIN_VALUE;
    private double RepoCountMin=Double.MAX_VALUE;
    private double GistCountMax=Double.MIN_VALUE;
    private double GistCountMin=Double.MAX_VALUE;
    private double FollowerMax=Double.MIN_VALUE;
    private double FollowerMin=Double.MAX_VALUE;
    private double ActivityMax=Double.MIN_VALUE;
    private double ActivityMin=Double.MAX_VALUE;
    private double UserValueMax=Double.MIN_VALUE;
    private double UserValueMin=Double.MAX_VALUE;

    private LogUserRadarCalculator(List<UserPO> userList) {
        setMaxAndMin(userList);
    }

    private void setMaxAndMin(List<UserPO> userList) {
        for (UserPO po : userList) {
            double logRepos = Math.log(po.getPublic_repos()+1);
            if (logRepos>RepoCountMax) {
                RepoCountMax = logRepos;
            }
            if (logRepos<RepoCountMin) {
                RepoCountMin = logRepos;
            }

            double logGists = Math.log(po.getPublic_gists()+1);
            if (logGists>GistCountMax) {
                GistCountMax = logGists;
            }
            if (logGists<GistCountMin) {
                GistCountMin = logGists;
            }

            double logFollower = Math.log(po.getFollowNum()+1);
            if (logFollower>FollowerMax) {
                FollowerMax = logFollower;
            }
            if (logFollower<FollowerMin) {
                FollowerMin = logFollower;
            }

            double logActivity = Math.log(po.getUserActivity()+1);
            if (logActivity>ActivityMax) {
                ActivityMax = logActivity;
            }
            if (logActivity<ActivityMin) {
                ActivityMin = logActivity;
            }

            double logValue = Math.log(po.getUserValue()+1);
            if (logValue>UserValueMax) {
                UserValueMax = logValue;
            }
            if (logValue<UserValueMin) {
                UserValueMin = logValue;
            }
        }
    }

    @Override
    public double calRepoCount(int repoCount) {
        return (Math.log(repoCount+1)-RepoCountMin)/(RepoCountMax - RepoCountMin);
    }

    @Override
    public double calGistCount(int gistCount) {
        return (Math.log(gistCount+1)-GistCountMin)/(GistCountMax - GistCountMin);
    }

    @Override
    public double calFollower(int follower) {
        return (Math.log(follower+1)-FollowerMin)/(FollowerMax - FollowerMin);
    }

    @Override
    public double calActivity(double activity) {
        return (Math.log(activity+1)-ActivityMin)/ (ActivityMax - ActivityMin);
    }

    @Override
    public double calUserValue(double userValue) {
        return (Math.log(userValue+1)-UserValueMin)/ (UserValueMax - UserValueMin);
    }
}
