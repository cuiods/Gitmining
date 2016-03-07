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
import edu.nju.git.data.factory.impl.UserBriefPOfactory;
import edu.nju.git.data.factory.impl.UserPOfactory;

public class UserReader {

	/**
	 * user's login name
	 */
	private String name;
	private final String url_location = "http://www.gitmining.net/api/user/";
	public UserBriefPO getBriefPO()
	{
		return new UserBriefPOfactory().getPO(getMap());
	}
	
	public UserPO getPO()
	{
		return new UserPOfactory().getPO(getMap());
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
