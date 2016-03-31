package org.GitServer.launch;

import edu.nju.git.data.service.RepoChartDataService;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.data.service.UserChartDataService;
import edu.nju.git.data.service.UserDataService;
import org.GitServer.dataimpl.RepoChartDataImpl;
import org.GitServer.dataimpl.RepoDataImpl;
import org.GitServer.dataimpl.UserChartDataImpl;
import org.GitServer.dataimpl.UserDataImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Harry on 2016/3/29.
 */
public class ServerLauncher {
    public static void main(String[] args) {
        try {
            RepoDataService repoDataService = RepoDataImpl.instance();
            UserDataService userDataService = UserDataImpl.instance();
            RepoChartDataService repoChartDataService = RepoChartDataImpl.instance();
            UserChartDataService userChartDataService = UserChartDataImpl.instance();
            //注册通讯端口
            LocateRegistry.createRegistry(1099);
            //注册通讯路径
            Naming.rebind("rmi://127.0.0.1:1099/RepoDataService", repoDataService);
            System.out.println("RepoDataService Start!");
            Naming.rebind("rmi://127.0.0.1:1099/UserDataService", userDataService);
            System.out.println("UserDataService Start!");
            Naming.rebind("rmi://127.0.0.1:1099/RepoChartDataService", repoChartDataService);
            System.out.println("RepoChartDataService Start!");
            Naming.rebind("rmi://127.0.0.1:1099/UserChartDataService", userChartDataService);
            System.out.println("UserChartDataService Start!");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
