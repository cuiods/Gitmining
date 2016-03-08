package edu.nju.git.data.api.centralization;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.data.api.JacksonConfig;
import edu.nju.git.data.api.service.MapService;
/**
 * firstly,please set name's value
 * reader userPO or UserBriefPO from a hashmap 
 * by the api "http://www.gitmining.net/api/user/{name}"
 * @author daixinyan
 * @date 2016-03-07
 */
public class UserMapReader implements MapService{

	/**
	 * user's login name
	 */
	private String name;
	private final String url_location = "http://www.gitmining.net/api/user/";
	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap()
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
	
	public UserMapReader() {
	}
	
	public UserMapReader(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
