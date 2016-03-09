package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.decentralization.UserItemReader;

public class ItemUserBriefPOfactory extends AbstractItemPO<UserBriefPO> {

	public ItemUserBriefPOfactory(){}
	public ItemUserBriefPOfactory(String name) 
	{
		this.basicItemService = new UserItemReader(name);
	}

	public void setName(String name)
	{
		this.basicItemService = new UserItemReader(name);
	}
	
	
	
}