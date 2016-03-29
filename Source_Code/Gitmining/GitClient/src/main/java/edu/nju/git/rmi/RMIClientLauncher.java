package edu.nju.git.rmi;

import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.data.service.RepoChartDataService;
import edu.nju.git.data.service.UserChartDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * this class is for init rmi bounding
 */
public class RMIClientLauncher {

	public static void initRMI() {
		try {
			RepoChartDataService repoChartDataService = (RepoChartDataService) Naming.lookup("rmi://127.0.0.1:1099/RepoChartDataService");
			UserChartDataService userChartDataService = (UserChartDataService) Naming.lookup("rmi://127.0.0.1:1099/UserChartDataService");

			RepoChartBlImpl.instance().setRepoChartDataService(repoChartDataService);
			UserChartBlImpl.instance().setUserChartDataService(userChartDataService);
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
