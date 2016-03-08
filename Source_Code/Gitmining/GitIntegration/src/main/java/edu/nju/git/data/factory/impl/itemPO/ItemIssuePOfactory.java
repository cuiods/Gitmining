package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.IssuePO;
import edu.nju.git.data.api.decentralization.ItemIssuePOReader;

public class ItemIssuePOfactory extends AbstractItemPO<IssuePO>{

	
	/**
	 *  <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/issue/{number}/item/{item}
     * 查询某个Issue的某项信息
     * item可接受的参数有：
     * state,locked,title,body
     * user,user_id,user_type
     * created_at,updates_at,closed_at,merged_at
     * </p>
	 */
	@Override
	public IssuePO getPO() {
		IssuePO po = new IssuePO();
		
		return po;
	}

	public ItemIssuePOfactory(String owner,String name,String sha) {
		this.setNames(owner, name, sha);
	}
	public ItemIssuePOfactory(String fullname,String sha) {
		this.setNames(fullname, sha);
	}
	public void setNames(String owner,String name,String sha){
		this.basicItemService = new ItemIssuePOReader(owner, name, sha);
	}
	public void setNames(String fullname,String sha){
		this.basicItemService = new ItemIssuePOReader(fullname, sha);
	}
}
