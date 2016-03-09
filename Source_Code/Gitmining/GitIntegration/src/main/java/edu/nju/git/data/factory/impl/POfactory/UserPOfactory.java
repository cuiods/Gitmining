package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.UserPO;
import edu.nju.git.data.factory.service.POfactory;
import edu.nju.git.type.OwnerType;

public class UserPOfactory implements POfactory<UserPO> {

	private AbstractPOfactory itemHelper;
	public UserPOfactory() {
		// TODO Auto-generated constructor stub
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
	public UserPO getPO() {

		UserPO po = new UserPO();
		
		po.setBio(itemHelper.getString("bio"));
		po.setBlog(itemHelper.getString("blog"));
		po.setCompany(itemHelper.getString("company"));
		po.setCreate_at(itemHelper.getString("created_at"));
		po.setEmail(itemHelper.getString("email"));
		
		po.setFollowingNum(itemHelper.getInteger("following"));
		po.setFollowNum(itemHelper.getInteger("followers"));
		
		po.setLocation(itemHelper.getString("location"));
		po.setLogin(itemHelper.getString("login"));
		po.setName(itemHelper.getString("name"));
		
		po.setPublic_repos(itemHelper.getInteger("public_repos"));
		po.setPublicGists(itemHelper.getInteger("public_gists"));
		
		po.setType(OwnerType.getInstance(itemHelper.getString("type")));
		po.setUpdate_at(itemHelper.getString("updated_at"));
		
		return po;
	}

}
