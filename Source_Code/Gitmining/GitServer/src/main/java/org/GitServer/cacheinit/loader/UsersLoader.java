package org.GitServer.cacheinit.loader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.GitServer.cacheinit.loader.POfactory.UserPOfactory;

import edu.nju.git.PO.UserPO;

public class UsersLoader {
	private Iterable<String> repofullName;
	private List<UserPO> pos ;
	
	public UsersLoader(Iterable<String> reader) {
		this.repofullName = reader;
		init();
	}
	
	private void init() {
		// get details information,one by one
		pos = new ArrayList<UserPO>(3000);
		Set<String> stringSet = new HashSet<String>();
		
		UserPOfactory userPOfactory = new UserPOfactory();
		for (String string : repofullName) {
			String tempString = string.split("/")[0];
			if(!stringSet.contains(tempString))
			{
				stringSet.add(tempString);
				userPOfactory.setName(tempString);
				UserPO po = userPOfactory.getPO();
				System.out.println("done withing reading user: "+tempString);
				if(po!=null){
					pos.add(po);
				}
			}
		}
		
	}
	
	/**
	 * <br/><b>precondition</b>：the object has been created at the environment with network.
	 * <br/><b>postcondition</b>：return the intial not null list.
	 * @return
	 * @date 2016-03-23
	 */
	public List<UserPO> getPos() {return pos;}
}
