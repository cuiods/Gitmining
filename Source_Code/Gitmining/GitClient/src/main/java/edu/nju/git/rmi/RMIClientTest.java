package edu.nju.git.rmi;

import java.rmi.Naming;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.rmiservice.RepoDataRMIService;
import edu.nju.git.type.SortType;

public class RMIClientTest {

	public static void main(String[] args){
		try{
		    long a = System.currentTimeMillis();
			//调用远程对象，注意RMI路径与接口必须与服务器配置一致
			RepoDataRMIService personService=(RepoDataRMIService)Naming.lookup("rmi://127.0.0.1:6600/RepoDataRMIService");
			List<RepoBriefPO> list = personService.getRepoBriefPOs(SortType.FOLLOWER_NUM);
		
			for (RepoBriefPO repoBriefPO : list) {
				System.out.println(repoBriefPO.getDescription());
			}
			System.out.println(System.currentTimeMillis()-a);
		}catch(Exception ex){
				ex.printStackTrace();
			}
	}
}
