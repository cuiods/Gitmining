package org.GitServer.radarstrategy.impl;

import org.GitServer.radarstrategy.service.UserRadarService;

import java.util.Map;

/**
 * Created by Harry on 2016/3/23.
 */
public class SimpleUserRadarCalculator implements UserRadarService{
    private static SimpleUserRadarCalculator uniqueInstance = null;

    public static SimpleUserRadarCalculator instance() {
        if (uniqueInstance == null){
            uniqueInstance = new SimpleUserRadarCalculator();
        }
        return uniqueInstance;
    }

    private SimpleUserRadarCalculator() {
    }

    // TODO: 2016/3/23 to be completed
    @Override
    public double calRepoCount(int repoCount, Map allUser) {
        return 0.5;
    }

    @Override
    public double calGistCount(int gistCount, Map allUser) {
        return 0.5;
    }

    @Override
    public double calFollower(int follower, Map allUser) {
        return 0.5;
    }

    @Override
    public double calActivity(double activity, Map allUser) {
        return 0.5;
    }

    @Override
    public double calUserValue(double userValue, Map allUser) {
        return 0.5;
    }
}
