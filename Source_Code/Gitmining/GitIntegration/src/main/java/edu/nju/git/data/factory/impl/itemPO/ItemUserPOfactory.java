package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.UserPO;

public class ItemUserPOfactory extends AbstractItemPO<UserPO>{

	public ItemUserPOfactory() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * <b>config</b>
     * <br/>id,login,type,name
     * <br/>company,blog,location,email,bio
     * <br/>public_repos,public_gists,followers,following
     * <br/>created_at,updated_at
	 */
	@Override
	public UserPO getPO() {
		UserPO po = new UserPO();
		po.setLogin(this.itemService.getItem("login").toString());
//		po.setType(this.itemService.getItem("type").toString());
		po.setName(this.itemService.getItem("name").toString());
		po.setCompany(this.itemService.getItem("company").toString());
		po.setBlog(this.itemService.getItem("blog").toString());
		po.setLocation(this.itemService.getItem("location").toString());
		po.setEmail(this.itemService.getItem("email").toString());
		po.setBio(this.itemService.getItem("bio").toString());
		po.setPublic_repos((Integer)this.itemService.getItem("public_repos"));
		po.setPublicGists((Integer)this.itemService.getItem("public_gists"));
		po.setFollowNum((Integer)this.itemService.getItem("followers"));
		po.setFollowingNum((Integer)this.itemService.getItem("following"));
		po.setCreate_at(this.itemService.getItem("created_at").toString());
		po.setUpdate_at(this.itemService.getItem("updated_at").toString());
		return po;
	}

}
