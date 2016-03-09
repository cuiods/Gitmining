package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.decentralization.RepoItemReader;

public class ItemRepoBriefPOfactory extends AbstractItemPO<RepoBriefPO>{

	public ItemRepoBriefPOfactory(String owner,String name) {
		this.setNames(owner, name);
	}
	public void setNames(String owner,String name){
		this.basicItemService = new RepoItemReader(owner, name);
	}
	public ItemRepoBriefPOfactory(String fullname) {
		this.setNames(fullname);
	}
	public void setNames(String fullname){
		this.basicItemService = new RepoItemReader(fullname);
	}

	public ItemRepoBriefPOfactory() {}
}
