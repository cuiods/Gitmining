package org.GitServer.dataimpl;

import edu.nju.git.VO.chartvo.MyChartVO;
import edu.nju.git.data.service.RepoChartDataService;
import org.GitServer.dataread.ReaderAndCount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Harry on 2016/3/23.
 */
public class RepoChartDataImpl extends UnicastRemoteObject implements RepoChartDataService {

    private static RepoChartDataImpl uniqueInstance = null;

    public static RepoChartDataImpl instance() throws RemoteException {
        if (uniqueInstance == null) {
            uniqueInstance = new RepoChartDataImpl();
        }
        return uniqueInstance;
    }

    private ReaderAndCount readerAndCount;

    private RepoChartDataImpl() throws RemoteException {
        super();
        readerAndCount = ReaderAndCount.instance();
    }

    @Override
    public MyChartVO statLanguage() throws RemoteException {
        return readerAndCount.getLanguage();
    }

    @Override
    public MyChartVO statCreateTime() throws RemoteException {
        return readerAndCount.getRepoCreateTime();
    }

    @Override
    public MyChartVO statContributors() throws RemoteException {
        return readerAndCount.getRepoContributors();
    }

    @Override
    public MyChartVO statCollaborators() throws RemoteException {
        return readerAndCount.getRepoCollabrators();
    }

    @Override
    public MyChartVO statSize() throws RemoteException {
        return readerAndCount.getRepoSize();
    }

    @Override
    public MyChartVO statSubscriber() throws RemoteException {
        return readerAndCount.getRepoSubscribers();
    }

    @Override
    public MyChartVO statForks() throws RemoteException {
        return readerAndCount.getForkCount();
    }

    @Override
    public MyChartVO statStars() throws RemoteException {
        return readerAndCount.getStarCount();
    }
}
