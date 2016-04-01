package org.GitServer.cacheinit.loader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.GitServer.cacheinit.loader.POfactory.UserPOfactory;

import edu.nju.git.PO.UserPO;
/**
 * this object can can be created with a array of strings,which is the name of users.
 * <br/>once you created a object, reading started.  
 * @author daixinyan
 * @date 2016-03-31
 */
public class UsersLoader {
	private Collection<String> userNames;
	private List<UserPO> pos ;
	private final int DEFAULT_ARRAYLIT_SIZE;
	
	/**
	 * 
	 * @param reader reader cannot have the same elements
	 */
	public UsersLoader(Collection<String> reader) {
		this.userNames = reader;
		DEFAULT_ARRAYLIT_SIZE = reader.size();
		init();
	}
	public UsersLoader(Collection<String> names,int size) {
		this.userNames = names;
		DEFAULT_ARRAYLIT_SIZE = size;
		init();
	}
	private void init() {
		// get details information,one by one
		pos = new ArrayList<UserPO>(DEFAULT_ARRAYLIT_SIZE);
		UserPOfactory userPOfactory = new UserPOfactory();
		for (String tempString : userNames) {
			
				userPOfactory.setName(tempString);
				UserPO po = userPOfactory.getPO();
				System.out.println("done withing reading user: "+tempString);
				if(po!=null){
					System.out.println(po.toString());
					pos.add(po);
				}
			
		}
		System.out.println("done with reading userPO list");
		
	}
	
	/**
	 * <br/><b>precondition</b>：the object has been created at the environment with network.
	 * <br/><b>postcondition</b>：return the initial not null list.
	 * @return
	 * @date 2016-03-23
	 */
	public List<UserPO> getPos() {return pos;}
}
