package edu.nju.git.rmi;

import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.data.service.RepoChartDataService;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.data.service.UserChartDataService;
import edu.nju.git.data.service.UserDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * this class is for init rmi bounding
 */
public class RMIClientLauncher {

	public static boolean initRMI() {
		try {
			RepoChartDataService repoChartDataService = (RepoChartDataService) Naming.lookup("rmi://127.0.0.1:1099/RepoChartDataService");
			UserChartDataService userChartDataService = (UserChartDataService) Naming.lookup("rmi://127.0.0.1:1099/UserChartDataService");
			RepoDataService repoDataService = (RepoDataService) Naming.lookup("rmi://127.0.0.1:1099/RepoDataService");
			UserDataService userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:1099/UserDataService");

			RepoChartBlImpl.instance().setRepoChartDataService(repoChartDataService);
			UserChartBlImpl.instance().setUserChartDataService(userChartDataService);
			RepoBlImpl.instance().setRepoDataService(repoDataService);
			UserBlImpl.instance().setUserDataService(userDataService);
			return true;
		} catch (NotBoundException e) {
			e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * this method is invoked when client has problem connecting to server
	 */
	public static void sendRMIWarning() {
		// TODO: 2016/3/31 count 5 seconds, send warnings to presentation and then initRMI
		do {

		}
		while (!initRMI());
	}
}
