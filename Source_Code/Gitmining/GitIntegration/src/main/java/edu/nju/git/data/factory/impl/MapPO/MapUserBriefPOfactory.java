package edu.nju.git.data.factory.impl.MapPO;

import java.util.Map;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.centralization.UserMapReader;

public class MapUserBriefPOfactory extends AbstractMapPO<UserBriefPO> {


    public MapUserBriefPOfactory() {}
	
    public MapUserBriefPOfactory(String name){    this.mapService = new UserMapReader(name); }
    
    public void setName(String name){  this.mapService = new UserMapReader(name); }
	
    @Override
	public UserBriefPO getPO() {
		Map<String, Object> map = this.mapService.getMap();
		UserBriefPO userBriefPO = new UserBriefPO();
//		userBriefPO.setFollowingNum((Integer)map.get("following"));
//		userBriefPO.setFollowNum((Integer)map.get("followers"));
//		userBriefPO.setId((Integer)map.get("id"));
		userBriefPO.setLogin(map.get("login").toString());
		return userBriefPO;
	}


}
