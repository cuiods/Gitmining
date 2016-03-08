package edu.nju.git.data.factory.impl.MapPO;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.centralization.UserMapReader;

public class MapUserBriefPOfactory extends AbstractMapPO<UserBriefPO> {


    public MapUserBriefPOfactory() {}
	
    public MapUserBriefPOfactory(String name){    this.mapService = new UserMapReader(name); }
    
    public void setName(String name){  this.mapService = new UserMapReader(name); }
	/**
	 * id,login,type,name
     * company,blog,location,email,bio
     * public_repos,public_gists,followers,following
     * created_at,updated_at
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
    @Override
	public UserBriefPO getPO() {
		Map<String, Object> map = this.mapService.getMap();
		if(map==null){
			return null;
		}
		UserBriefPO userBriefPO = new UserBriefPO();
//		userBriefPO.setFollowingNum((Integer)map.get("following"));
//		userBriefPO.setFollowNum((Integer)map.get("followers"));
//		userBriefPO.setId((Integer)map.get("id"));
		userBriefPO.setLogin(map.get("login").toString());
		userBriefPO.setFollowers(((Integer)map.get("followers")));
		userBriefPO.setPublic_repos(((Integer)map.get("public_repos")));
		return userBriefPO;
	}


}
