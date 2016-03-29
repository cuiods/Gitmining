package org.GitServer.radarstrategy.impl;

import edu.nju.git.PO.RepoPO;
import org.GitServer.radarstrategy.service.RepoRadarService;

import java.util.List;
import java.util.Map;

/**
 * this class is an implementation of the repo radar strategy.
 * @author benchaodong
 */
public class SimpleRepoRadarCalculator implements RepoRadarService{

    private static SimpleRepoRadarCalculator uniqueInstance = null;

    public static SimpleRepoRadarCalculator instance(List<RepoPO> repoList) {
        if (uniqueInstance == null) {
            uniqueInstance = new SimpleRepoRadarCalculator(repoList);
        }
        return uniqueInstance;
    }

    private int SizeMax=0;
    private int SizeMin=Integer.MAX_VALUE;
    private int ForkMax=0;
    private int ForkMin=Integer.MAX_VALUE;
    private double PopularMax=0.0;
    private double PopularMin=Double.MAX_VALUE;
    private double ComplexityMax=0.0;
    private double ComplexityMin=Double.MAX_VALUE;
    private double ActivityMax=0.0;
    private double ActivityMin=Double.MAX_VALUE;

    private SimpleRepoRadarCalculator(List<RepoPO> repoList) {
        setMaxAndMin(repoList);
    }

    private void setMaxAndMin(List<RepoPO> repoList) {
        for (RepoPO po : repoList) {
            if (po.getSize()>SizeMax) {
                SizeMax = po.getSize();
            }
            if (po.getSize()<SizeMin) {
                SizeMin = po.getSize();
            }
            if (po.getNum_forks()>ForkMax) {
                ForkMax = po.getNum_forks();
            }
            if (po.getNum_forks()<ForkMin) {
                ForkMin = po.getNum_forks();
            }
            if (po.getPopular()>PopularMax) {
                PopularMax = po.getPopular();
            }
            if (po.getPopular()<PopularMin) {
                PopularMin = po.getPopular();
            }
            if (po.getComplexity()>ComplexityMax) {
                ComplexityMax = po.getComplexity();
            }
            if (po.getComplexity()<ComplexityMin) {
                ComplexityMin = po.getComplexity();
            }
            if (po.getRepoActivity()>ActivityMax) {
                ActivityMax = po.getRepoActivity();
            }
            if (po.getRepoActivity()<ActivityMin) {
                ActivityMin = po.getRepoActivity();
            }
        }
    }

    @Override
    public double calSize(int sizeValue) {
        if (SizeMin>=SizeMax) {
            return 1.0;
        }
        return (double)(sizeValue-SizeMin)/(double) (SizeMax-SizeMin);
    }

    @Override
    public double calFork(int forkValue) {
        if (ForkMin>=ForkMax) {
            return 1.0;
        }
        return (double)(forkValue-ForkMin)/(double) (ForkMax-ForkMin);
    }

    @Override
    public double calPopular(double popularValue) {
        if (PopularMin>=PopularMax) {
            return 1.0;
        }
        return (popularValue-PopularMin)/(PopularMax-PopularMin);
    }

    @Override
    public double calComplexity(double complexityValue) {
        if (ComplexityMin>=ComplexityMax) {
            return 1.0;
        }
        return (complexityValue-ComplexityMin)/(ComplexityMax-ComplexityMin);
    }

    @Override
    public double calActivity(double activityValue) {
        if (ActivityMin>=ActivityMax) {
            return 1.0;
        }
        return (activityValue-ActivityMin)/(ActivityMax-ActivityMin);
    }
}
