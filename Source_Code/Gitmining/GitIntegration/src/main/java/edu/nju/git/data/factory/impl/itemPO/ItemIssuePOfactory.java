package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.IssuePO;
import edu.nju.git.data.api.decentralization.IssueItemReader;

public class ItemIssuePOfactory extends AbstractItemPO<IssuePO>{

	
	

//	private String owner ;
//	private String name;
	public ItemIssuePOfactory(String owner,String name,String sha) {
		this.setNames(owner, name, sha);
	}
	public ItemIssuePOfactory(String fullname,String sha) {
		this.setNames(fullname, sha);
	}
	public void setNames(String owner,String name,String sha){
//		this.name = name;
//		this.owner = owner;
		this.basicItemService = new IssueItemReader(owner, name, sha);
	}
	public void setNames(String fullname,String sha){
		String[] fullnames = fullname.split("/");
		this.setNames(fullnames[0],fullnames[1], sha);
	}
}
