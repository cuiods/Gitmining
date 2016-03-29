package org.GitServer.radarstrategy.service;

import java.util.Map;

/**
 * this strategy is for calculate the double values needed for the radar chart of a repo
 * @author benchaodong
 */
public interface RepoRadarService {

    public double calSize(int sizeValue);

    public double calFork(int forkValue);

    public double calPopular(double popularValue);

    public double calComplexity(double complexityValue);

    public double calActivity(double activityValue);
}
