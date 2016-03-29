package edu.nju.git.bl.impl;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.bl.service.UserChartBlService;
import edu.nju.git.data.service.UserChartDataService;

import java.rmi.RemoteException;

/**
 * Created by Harry on 2016/3/29.
 */
public class UserChartBlImpl implements UserChartBlService {

    private UserChartDataService userChartDataService;

    private static UserChartBlImpl uniqueInstance;

    public static UserChartBlImpl instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UserChartBlImpl();
        }
        return uniqueInstance;
    }

    private UserChartBlImpl() {}

    @Override
    public MyChartVO statUserType() throws RemoteException {
        return userChartDataService.statUserType();
    }

    @Override
    public MyChartVO statCompanyUser() throws RemoteException {
        return userChartDataService.statCompanyUser();
    }

    @Override
    public MyChartVO statUserEmail() throws RemoteException {
        return userChartDataService.statUserEmail();
    }

    @Override
    public MyChartVO statUserCreateTime() throws RemoteException {
        return userChartDataService.statUserCreateTime();
    }

    @Override
    public MyChartVO statUserOwnRepo() throws RemoteException {
        return userChartDataService.statUserOwnRepo();
    }

    @Override
    public MyChartVO statUserGist() throws RemoteException {
        return userChartDataService.statUserGist();
    }

    @Override
    public MyChartVO statUserCollabRepo() throws RemoteException {
        return userChartDataService.statUserCollabRepo();
    }

    @Override
    public MyChartVO statUserContriRepo() throws RemoteException {
        return userChartDataService.statUserContriRepo();
    }

    @Override
    public MyChartVO statUserSubscrRepo() throws RemoteException {
        return userChartDataService.statUserSubscrRepo();
    }

    @Override
    public MyChartVO statUserFolllowers() throws RemoteException {
        return userChartDataService.statUserFolllowers();
    }

    public void setUserChartDataService(UserChartDataService userChartDataService) {
        this.userChartDataService = userChartDataService;
    }
}
