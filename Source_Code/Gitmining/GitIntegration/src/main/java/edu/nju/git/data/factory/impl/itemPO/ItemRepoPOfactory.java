package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.data.api.decentralization.RepoItemReader;

public class ItemRepoPOfactory extends AbstractItemPO<RepoPO>{

	

	
	private String owner ;
	private String name;
	public ItemRepoPOfactory(String owner,String name) {
		this.setNames(owner, name);
	}
	public void setNames(String owner,String name){
		this.owner = owner;
		this.name = name;
		this.basicItemService = new RepoItemReader(owner, name);
	}
	public ItemRepoPOfactory(String fullname) {
		this.setNames(fullname);
	}
	public void setNames(String fullname){
		String[] arrayName  = fullname.split("/");
		this.owner = arrayName[0];
		this.name = arrayName[1];
		this.basicItemService = new RepoItemReader(fullname);
	}
	
}
