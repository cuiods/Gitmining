package edu.nju.model.statistic;


/**
 * star fork watchers service
 */
public interface StarRegressionService {
    RegressionLine getForkRegression();
    RegressionLine getWatcherRegression();
    RegressionLine getFollowerRegression();
}
