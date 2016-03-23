package org.GitServer.radarstrategy.impl;

import org.GitServer.radarstrategy.service.RepoRadarService;

import java.util.Map;

/**
 * this class is an implementation of the repo radar strategy.
 * @author benchaodong
 */
public class SimpleRepoRadarCalculator implements RepoRadarService{

    private static SimpleRepoRadarCalculator uniqueInstance = null;

    public static SimpleRepoRadarCalculator instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SimpleRepoRadarCalculator();
        }
        return uniqueInstance;
    }

    private SimpleRepoRadarCalculator() {
    }
    // TODO: 2016/3/23 to be completed

    @Override
    public double calSize(int sizeValue, Map allSize) {
        return 0.5;
    }

    @Override
    public double calFork(int forkValue, Map allFork) {
        return 0.5;
    }

    @Override
    public double calPopular(double popularValue, Map allPopular) {
        return 0.5;
    }

    @Override
    public double calComplexity(double complexityValue, Map allComplexity) {
        return 0.5;
    }

    @Override
    public double calActivity(double activityValue, Map allActivity) {
        return 0.5;
    }
}
