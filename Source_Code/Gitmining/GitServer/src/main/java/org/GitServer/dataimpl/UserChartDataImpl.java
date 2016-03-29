package org.GitServer.dataimpl;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.data.service.UserChartDataService;
import org.GitServer.dataread.ReaderAndCount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Harry on 2016/3/23.
 */
public class UserChartDataImpl extends UnicastRemoteObject implements UserChartDataService {

    private static UserChartDataImpl uniqueInstance = null;

    public static UserChartDataImpl instance() throws RemoteException {
        if (uniqueInstance == null) {
            uniqueInstance = new UserChartDataImpl();
        }
        return uniqueInstance;
    }

    private ReaderAndCount readerAndCount;

    private UserChartDataImpl() throws RemoteException {
        super();
        readerAndCount = ReaderAndCount.instance();
    }

    @Override
    public MyChartVO statUserType() throws RemoteException {
        return readerAndCount.getStatUserType();
    }

    @Override
    public MyChartVO statCompanyUser() throws RemoteException {
        return readerAndCount.getStatCompanyUser();
    }

    @Override
    public MyChartVO statUserEmail() throws RemoteException{
        return readerAndCount.getStatUserEmail();
    }

    @Override
    public MyChartVO statUserCreateTime()throws RemoteException {
        return readerAndCount.getStatUserCreateTime();
    }

    @Override
    public MyChartVO statUserOwnRepo()throws RemoteException {
        return readerAndCount.getStatUserOwnRepo();
    }

    @Override
    public MyChartVO statUserGist()throws RemoteException {
        return readerAndCount.getStatUserGist();
    }

    @Override
    public MyChartVO statUserCollabRepo()throws RemoteException {
        return readerAndCount.getStatUserCollaborateRepo();
    }

    @Override
    public MyChartVO statUserContriRepo()throws RemoteException {
        return readerAndCount.getStatUserContributorRepo();
    }

    @Override
    public MyChartVO statUserSubscrRepo()throws RemoteException {
        return readerAndCount.getStatUserSubscriberRepo();
    }

    @Override
    public MyChartVO statUserFolllowers()throws RemoteException {
        return readerAndCount.getStatUserFolllowers();
    }
}
