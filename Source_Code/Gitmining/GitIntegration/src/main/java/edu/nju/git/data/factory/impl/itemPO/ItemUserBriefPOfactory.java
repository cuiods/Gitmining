package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.decentralization.ItemUserPOReader;

public class ItemUserBriefPOfactory extends AbstractItemPO<UserBriefPO> {

	public ItemUserBriefPOfactory(){}
	public ItemUserBriefPOfactory(String name) 
	{
		this.itemService = new ItemUserPOReader(name);
	}

	public void setName(String name)
	{
		this.itemService = new ItemUserPOReader(name);
	}
	
	
	/**
	 * <b>config</b>
     * <br/>id,login,type,name
     * <br/>company,blog,location,email,bio
     * <br/>public_repos,public_gists,followers,following
     * <br/>created_at,updated_at
     * <br/> login is loginname, name is full personal name
	 */
	@Override
	public UserBriefPO getPO() {
		UserBriefPO po = new UserBriefPO();
		po.setLogin(this.itemService.getItem("lgin").toString());
//		po.setType(this.itemService.getItem("type").toString());
		po.setPublic_repos((Integer)this.itemService.getItem("public_repos"));
		po.setFollowers((Integer)this.itemService.getItem("followers"));
		return po;
	}
}
