package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.factory.service.POfactory;

public class UserBriefPOfactory implements POfactory<UserBriefPO> {

	private AbstractPOfactory itemHelper;
	public UserBriefPOfactory() {
	}
	public UserBriefPOfactory(String name) {
	}
	public void setName(String name){  }
	
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
