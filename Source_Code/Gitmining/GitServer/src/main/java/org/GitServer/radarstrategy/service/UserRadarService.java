package org.GitServer.radarstrategy.service;

import java.util.Map;

/**
 * this is the strategy interface for computing the double values needed for user radar chart
 */
public interface UserRadarService {

    public double calRepoCount(int repoCount, Map allUser);

    public double calGistCount(int gistCount, Map allUser);

    public double calFollower(int follower, Map allUser);

    public double calActivity(double activity, Map allUser);

    public double calUserValue(double userValue, Map allUser);
}
