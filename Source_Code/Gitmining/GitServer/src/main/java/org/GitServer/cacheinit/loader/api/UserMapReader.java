package org.GitServer.cacheinit.loader.api;

import org.GitServer.cacheinit.loader.service.AbstractMapGetter;

import edu.nju.git.tools.APIconfig;

/**
 * firstly,please set name's value
 * reader userPO or UserBriefPO from a hashmap 
 * by the api "http://www.gitmining.net/api/user/{name}"
 * @author daixinyan
 * @date 2016-03-07
 */
public class UserMapReader extends AbstractMapGetter{

	
	@Override
	protected String getUrl() {
		if(APIconfig.isGithub){
			return url_location+name+APIconfig.getClientidandsecret();
		}
		return url_location+name;
	}
	/**
	 * user's login name
	 */
	private String name;
	private final String url_location = APIconfig.isGithub?
			"https://api.github.com/users/":"http://www.gitmining.net/api/user/";
	
	
	
	public UserMapReader(){}
	public UserMapReader(String name) {
		this.setName(name);
	}
	public void setName(String name) {
		this.name = name;
		this.init();
	}


	
}
