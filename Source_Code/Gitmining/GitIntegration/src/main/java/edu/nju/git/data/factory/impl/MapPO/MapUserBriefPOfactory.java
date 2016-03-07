package edu.nju.git.data.factory.impl.MapPO;

import java.util.Map;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.factory.service.MapPOfactory;

public class MapUserBriefPOfactory implements MapPOfactory<UserBriefPO> {

	@Override
	public UserBriefPO getPO(Map<String, Object> map) {
		UserBriefPO userBriefPO = new UserBriefPO();
//		userBriefPO.setFollowingNum((Integer)map.get("following"));
//		userBriefPO.setFollowNum((Integer)map.get("followers"));
//		userBriefPO.setId((Integer)map.get("id"));
		userBriefPO.setLogin(map.get("login").toString());
		return userBriefPO;
	}


}
