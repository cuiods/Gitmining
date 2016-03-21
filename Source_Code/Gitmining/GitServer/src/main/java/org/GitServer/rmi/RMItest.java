package org.GitServer.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.GitServer.dataimpl.RepoDataImpl;

import edu.nju.git.data.rmiservice.RepoDataRMIService;

public class RMItest {

	public static void main(String[] args)
	{
		try {
			RepoDataRMIService repoDataRMIService = new RepoDataImpl();
			//注册通讯端口
			LocateRegistry.createRegistry(6600);
			//注册通讯路径
			Naming.rebind("rmi://127.0.0.1:6600/RepoDataRMIService", repoDataRMIService);
			System.out.println("Service Start!");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
