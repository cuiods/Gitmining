package edu.nju.git.data.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;

public class UserReader {

	/**
	 * user's login name
	 */
	private String name;
	private final String url_location = "http://www.gitmining.net/api/user/";
	public UserBriefPO getBriefPO()
	{
		Map<String, Object> map = getMap();
		UserBriefPO userBriefPO = new UserBriefPO();
		userBriefPO.setFollowingNum((Integer)map.get("following"));
		userBriefPO.setFollowNum((Integer)map.get("followers"));
		userBriefPO.setId((Integer)map.get("id"));
		userBriefPO.setLogin(map.get("login").toString());
		return userBriefPO;
	}
	
	public UserPO getPO()
	{
		//TODO
		//Map<String, Object> map = getMap();
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getMap()
	{
		try{
			URL url = new URL(url_location+name);
			return JacksonConfig.getObjectMapper().readValue(url.openStream(), Map.class);
		}catch(MalformedURLException e){
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}
	
	public UserReader() {
	}
	
	public UserReader(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
