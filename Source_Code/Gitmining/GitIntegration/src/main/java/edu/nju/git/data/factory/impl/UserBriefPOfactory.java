package edu.nju.git.data.factory.impl;

import java.util.Map;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.factory.service.POfactory;

public class UserBriefPOfactory implements POfactory<UserBriefPO> {

	@Override
	public UserBriefPO getPO(Map<String, Object> map) {
		UserBriefPO userBriefPO = new UserBriefPO();
		userBriefPO.setFollowingNum((Integer)map.get("following"));
		userBriefPO.setFollowNum((Integer)map.get("followers"));
		userBriefPO.setId((Integer)map.get("id"));
		userBriefPO.setLogin(map.get("login").toString());
		return userBriefPO;
	}


}
