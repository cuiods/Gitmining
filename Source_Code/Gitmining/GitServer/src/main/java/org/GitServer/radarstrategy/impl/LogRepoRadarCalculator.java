package org.GitServer.radarstrategy.impl;

import edu.nju.git.PO.RepoPO;
import org.GitServer.radarstrategy.service.RepoRadarService;

import java.util.List;

/**
 * Created by Harry on 2016/4/5.
 */
public class LogRepoRadarCalculator implements RepoRadarService {
    private static LogRepoRadarCalculator uniqueInstance = null;

    public static LogRepoRadarCalculator instance(List<RepoPO> repoList) {
        if (uniqueInstance == null) {
            uniqueInstance = new LogRepoRadarCalculator(repoList);
        }
        return uniqueInstance;
    }

    private double SizeMax=Double.MIN_VALUE;
    private double SizeMin=Double.MAX_VALUE;
    private double ForkMax=Double.MIN_VALUE;
    private double ForkMin=Double.MAX_VALUE;
    private double PopularMax=Double.MIN_VALUE;
    private double PopularMin=Double.MAX_VALUE;
    private double ComplexityMax=Double.MIN_VALUE;
    private double ComplexityMin=Double.MAX_VALUE;
    private double ActivityMax=Double.MIN_VALUE;
    private double ActivityMin=Double.MAX_VALUE;

    private LogRepoRadarCalculator(List<RepoPO> repoList) {
        setMaxAndMin(repoList);
    }

    private void setMaxAndMin(List<RepoPO> repoList) {
        for (RepoPO po : repoList) {
            double logSize = Math.log(po.getSize());
            if (logSize>SizeMax) {
                SizeMax = logSize;
            }
            if (logSize<SizeMin) {
                SizeMin = logSize;
            }

            double logFork = Math.log(po.getNum_forks());
            if (logFork>ForkMax) {
                ForkMax = logFork;
            }
            if (logFork<ForkMin) {
                ForkMin = logFork;
            }

            double logPopular = Math.log(po.getPopular());
            if (logPopular>PopularMax) {
                PopularMax = logPopular;
            }
            if (logPopular<PopularMin) {
                PopularMin = logPopular;
            }

            double logComplexity = Math.log(po.getComplexity());
            if (logComplexity>ComplexityMax) {
                ComplexityMax = logComplexity;
            }
            if (logComplexity<ComplexityMin) {
                ComplexityMin = logComplexity;
            }

            double logActivity = Math.log(po.getRepoActivity());
            if (logActivity>ActivityMax) {
                ActivityMax = logActivity;
            }
            if (logActivity<ActivityMin) {
                ActivityMin = logActivity;
            }
        }
    }

    @Override
    public double calSize(int sizeValue) {
        return (Math.log(sizeValue)-SizeMin)/(SizeMax-SizeMin);
    }

    @Override
    public double calFork(int forkValue) {
        return (Math.log(forkValue)-ForkMin)/(ForkMax-ForkMin);
    }

    @Override
    public double calPopular(double popularValue) {
        return (Math.log(popularValue)-PopularMin)/(PopularMax-PopularMin);
    }

    @Override
    public double calComplexity(double complexityValue) {
        return (Math.log(complexityValue)-ComplexityMin)/(ComplexityMax-ComplexityMin);
    }

    @Override
    public double calActivity(double activityValue) {
        return (Math.log(activityValue)-ActivityMin)/(ActivityMax-ActivityMin);
    }
}
