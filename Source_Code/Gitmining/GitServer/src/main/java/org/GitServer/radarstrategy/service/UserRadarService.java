package org.GitServer.radarstrategy.service;

import java.util.Map;

/**
 * this is the strategy interface for computing the double values needed for user radar chart
 */
public interface UserRadarService {

    public double calRepoCount(int repoCount);

    public double calGistCount(int gistCount);

    public double calFollower(int follower);

    public double calActivity(double activity);

    public double calUserValue(double userValue);
}
