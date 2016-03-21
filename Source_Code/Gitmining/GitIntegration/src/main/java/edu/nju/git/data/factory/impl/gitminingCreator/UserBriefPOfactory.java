package edu.nju.git.data.factory.impl.gitminingCreator;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.abstractservice.FieldsGetterService;
import edu.nju.git.data.factory.service.POfactory;

public class UserBriefPOfactory implements POfactory<UserBriefPO> {

	private FieldsGetterService itemHelper;
	public UserBriefPOfactory() {
	}
	
	public UserBriefPOfactory(FieldsGetterService itemHelper) {
		super();
		this.itemHelper = itemHelper;
	}
	
	
	public void setItemHelper(FieldsGetterService itemHelper) {
		this.itemHelper = itemHelper;
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
		UserBriefPO userBriefPO = new UserBriefPO();
//		userBriefPO.setFollowingNum((Integer)map.get("following"));
//		userBriefPO.setFollowNum((Integer)map.get("followers"));
//		userBriefPO.setId((Integer)map.get("id"));
		userBriefPO.setLogin(itemHelper.getString("login"));
		userBriefPO.setFollowers((itemHelper.getInteger("followers")));
		userBriefPO.setPublic_repos((itemHelper.getInteger("public_repos")));
		return userBriefPO;
	}

}
