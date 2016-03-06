package edu.nju.git.data.api;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;

public class UserReader {

	private String name;
	
	public UserReader() {
	}
	
	public UserReader(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public UserBriefPO getBriefPO()
	{
		return null;
	}
	
	public UserPO getPO()
	{
		return null;
	}

}
