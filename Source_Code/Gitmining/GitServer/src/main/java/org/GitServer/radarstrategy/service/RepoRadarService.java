package org.GitServer.radarstrategy.service;

import java.util.Map;

/**
 * this strategy is for calculate the double values needed for the radar chart of a repo
 * @author benchaodong
 */
public interface RepoRadarService {

    public double calSize(int sizeValue, Map allSize);

    public double calFork(int forkValue, Map allFork);

    public double calPopular(double popularValue, Map allPopular);

    public double calComplexity(double complexityValue, Map allComplexity);

    public double calActivity(double activityValue, Map allActivity);
}
