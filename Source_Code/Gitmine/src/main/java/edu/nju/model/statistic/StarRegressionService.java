package edu.nju.model.statistic;

import com.sun.org.apache.regexp.internal.RE;

/**
 * star fork watchers service
 */
public interface StarRegressionService {
    RegressionLine getForkRegression();
    RegressionLine getWatcherRegression();
    RegressionLine getFollowerRegression();
}
