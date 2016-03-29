package edu.nju.git.bl.impl;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.service.RepoChartBlService;
import edu.nju.git.data.service.RepoChartDataService;

import java.rmi.RemoteException;

/**
 * Created by Harry on 2016/3/29.
 */
public class RepoChartBlImpl implements RepoChartBlService {

    private RepoChartDataService repoChartDataService;

    private static RepoChartBlImpl uniqueInstance;

    public static RepoChartBlImpl instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new RepoChartBlImpl();
        }
        return uniqueInstance;
    }

    private RepoChartBlImpl(){}

    @Override
    public MyChartVO statLanguage() throws RemoteException {
        return repoChartDataService.statLanguage();
    }

    @Override
    public MyChartVO statCreateTime() throws RemoteException {
        return repoChartDataService.statCreateTime();
    }

    @Override
    public MyChartVO statContributors() throws RemoteException {
        return repoChartDataService.statContributors();
    }

    @Override
    public MyChartVO statCollaborators() throws RemoteException {
        return repoChartDataService.statCollaborators();
    }

    @Override
    public MyChartVO statSize() throws RemoteException {
        return repoChartDataService.statSize();
    }

    @Override
    public MyChartVO statSubscriber() throws RemoteException {
        return repoChartDataService.statSubscriber();
    }

    @Override
    public MyChartVO statForks() throws RemoteException {
        return repoChartDataService.statForks();
    }

    @Override
    public MyChartVO statStars() throws RemoteException {
        return repoChartDataService.statStars();
    }

    public void setRepoChartDataService(RepoChartDataService repoChartDataService) {
        this.repoChartDataService = repoChartDataService;
    }
}
