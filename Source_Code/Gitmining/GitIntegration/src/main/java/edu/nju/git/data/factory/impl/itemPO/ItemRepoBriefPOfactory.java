package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.decentralization.ItemRepoPOReader;

public class ItemRepoBriefPOfactory extends AbstractItemPO<RepoBriefPO>{

	@Override
	public RepoBriefPO getPO() {
		RepoBriefPO po = new RepoBriefPO();
		po.setOwner(owner);
		po.setName(name);
		po.setDescription(this.basicItemService.getItem("description").toString());
		//2014-02-03T19:33:59Z
		po.setLastUpdate(this.basicItemService.getItem("updated_at").toString());
		po.setNum_forks(this.getInteger("forks"));
		po.setNum_stars(this.getInteger("stargazers_count"));
		po.setNum_subscribers(this.getInteger("subscribers_count"));
		System.out.println(i++);
		return po;
	}
	public static int i = 0;
	private String owner ;
	private String name;
	public ItemRepoBriefPOfactory(String owner,String name) {
		this.setNames(owner, name);
	}
	public void setNames(String owner,String name){
		this.owner = owner;
		this.name = name;
		this.basicItemService = new ItemRepoPOReader(owner, name);
	}
	public ItemRepoBriefPOfactory(String fullname) {
		this.setNames(fullname);
	}
	public void setNames(String fullname){
		String[] arrayName  = fullname.split("/");
		this.owner = arrayName[0];
		this.name = arrayName[1];
		this.basicItemService = new ItemRepoPOReader(fullname);
	}

	public ItemRepoBriefPOfactory() {}
}
