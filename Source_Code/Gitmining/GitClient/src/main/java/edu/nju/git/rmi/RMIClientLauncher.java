package edu.nju.git.rmi;

import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.constant.Consts;
import edu.nju.git.data.service.RepoChartDataService;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.data.service.UserChartDataService;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.ui.utils.CloseOperation;
import edu.nju.git.ui.utils.UtilDialog;

import java.io.*;
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
			File file = new File("support_files/ServerIPAddress.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String ip = bufferedReader.readLine();

			RepoChartDataService repoChartDataService = (RepoChartDataService) Naming.lookup(ip+"/RepoChartDataService");
			UserChartDataService userChartDataService = (UserChartDataService) Naming.lookup(ip+"/UserChartDataService");
			RepoDataService repoDataService = (RepoDataService) Naming.lookup(ip+"/RepoDataService");
			UserDataService userDataService = (UserDataService) Naming.lookup(ip+"/UserDataService");

			RepoChartBlImpl.instance().setRepoChartDataService(repoChartDataService);
			UserChartBlImpl.instance().setUserChartDataService(userChartDataService);
			RepoBlImpl.instance().setRepoDataService(repoDataService);
			UserBlImpl.instance().setUserDataService(userDataService);
			return true;
		} catch (NotBoundException e) {
			//e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			//e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

	/**
	 * this method is invoked when client has problem connecting to server
	 */
	public static void sendRMIWarning() {
		// TODO: 2016/3/31 count 5 seconds, send warnings to presentation and then initRMI
		int counts = 0;
		UtilDialog.ShowMessage("连接服务器失败，正在尝试重连，请稍后...");
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while ((!initRMI())&&(Consts.CONNECT_TIMES > ++counts));
		if (counts >= Consts.CONNECT_TIMES) {	//can not reconnect to server
			UtilDialog.ShowConfirm("无法连接至服务器，请尝试检查ip并重启客户端", new CloseOperation());
		}
		else {
			UtilDialog.hideDialog();
		}
	}
}
