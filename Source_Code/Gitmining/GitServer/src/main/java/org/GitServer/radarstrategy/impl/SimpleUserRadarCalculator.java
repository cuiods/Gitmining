package org.GitServer.radarstrategy.impl;

import edu.nju.git.PO.UserPO;
import org.GitServer.radarstrategy.service.UserRadarService;

import java.util.List;


/**
 * Created by Harry on 2016/3/23.
 */
public class SimpleUserRadarCalculator implements UserRadarService{
    private static SimpleUserRadarCalculator uniqueInstance = null;

    public static SimpleUserRadarCalculator instance(List<UserPO> userList) {
        if (uniqueInstance == null){
            uniqueInstance = new SimpleUserRadarCalculator(userList);
        }
        return uniqueInstance;
    }

    private int RepoCountMax=0;
    private int RepoCountMin=Integer.MAX_VALUE;
    private int GistCountMax=0;
    private int GistCountMin=Integer.MAX_VALUE;
    private int FollowerMax=0;
    private int FollowerMin=Integer.MAX_VALUE;
    private double ActivityMax=0;
    private double ActivityMin=Double.MAX_VALUE;
    private double UserValueMax=0;
    private double UserValueMin=Double.MAX_VALUE;

    private SimpleUserRadarCalculator(List<UserPO> userList) {
        setMaxAndMin(userList);
    }

    private void setMaxAndMin(List<UserPO> userList) {
        for (UserPO po : userList) {
            if (po.getPublic_repos()>RepoCountMax) {
                RepoCountMax = po.getPublic_repos();
            }
            if (po.getPublic_repos()<RepoCountMin) {
                RepoCountMin = po.getPublic_repos();
            }
            if (po.getPublic_gists()>GistCountMax) {
                GistCountMax = po.getPublic_gists();
            }
            if (po.getPublic_gists()<GistCountMin) {
                GistCountMin = po.getPublic_gists();
            }
            if (po.getFollowNum()>FollowerMax) {
                FollowerMax = po.getFollowNum();
            }
            if (po.getFollowNum()<FollowerMin) {
                FollowerMin = po.getFollowNum();
            }
            if (po.getUserActivity()>ActivityMax) {
                ActivityMax = po.getUserActivity();
            }
            if (po.getUserActivity()<ActivityMin) {
                ActivityMin = po.getUserActivity();
            }
            if (po.getUserValue()>UserValueMax) {
                UserValueMax = po.getUserValue();
            }
            if (po.getUserValue()<UserValueMin) {
                UserValueMin = po.getUserValue();
            }
        }
    }

    @Override
    public double calRepoCount(int repoCount) {
        if (RepoCountMin >= RepoCountMax) {
            return 1.0;
        }
        return (double) (repoCount-RepoCountMin)/(double) (RepoCountMax - RepoCountMin);
    }

    @Override
    public double calGistCount(int gistCount) {
        if (GistCountMin >= GistCountMax) {
            return 1.0;
        }
        return (double) (gistCount-GistCountMin)/(double) (GistCountMax - GistCountMin);
    }

    @Override
    public double calFollower(int follower) {
        if (FollowerMin >= FollowerMax) {
            return 1.0;
        }
        return (double) (follower-FollowerMin)/(double) (FollowerMax - FollowerMin);
    }

    @Override
    public double calActivity(double activity) {
        if (ActivityMin >= ActivityMax) {
            return 1.0;
        }
        return (activity-ActivityMin)/ (ActivityMax - ActivityMin);
    }

    @Override
    public double calUserValue(double userValue) {
        if (UserValueMin >= UserValueMax) {
            return 1.0;
        }
        return (userValue-UserValueMin)/ (UserValueMax - UserValueMin);
    }
}
