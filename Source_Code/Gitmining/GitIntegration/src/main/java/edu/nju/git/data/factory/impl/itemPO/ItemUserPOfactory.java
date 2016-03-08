package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.UserPO;
import edu.nju.git.type.OwnerType;

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
		po.setLogin(this.basicItemService.getItem("login"));
		po.setType(OwnerType.getInstance(this.getString("type")));
		po.setName(this.basicItemService.getItem("name"));
		po.setCompany(this.basicItemService.getItem("company"));
		po.setBlog(this.basicItemService.getItem("blog").toString());
		po.setLocation(this.basicItemService.getItem("location").toString());
		po.setEmail(this.basicItemService.getItem("email").toString());
		po.setBio(this.basicItemService.getItem("bio").toString());
		po.setPublic_repos(this.getInteger("public_repos"));
		po.setPublicGists(this.getInteger("public_gists"));
		po.setFollowNum(this.getInteger("followers"));
		po.setFollowingNum(this.getInteger("following"));
		po.setCreate_at(this.basicItemService.getItem("created_at"));
		po.setUpdate_at(this.basicItemService.getItem("updated_at"));
		return po;
	}

}
