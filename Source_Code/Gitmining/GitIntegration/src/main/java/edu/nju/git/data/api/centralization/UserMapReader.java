package edu.nju.git.data.api.centralization;

import edu.nju.git.data.api.abstractservice.AbstractMapGetter;
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
		return url_location+name;
	}
	/**
	 * user's login name
	 */
	private String name;
	private final String url_location = "http://www.gitmining.net/api/user/";
	
	
	
	public UserMapReader(){}
	public UserMapReader(String name) {
		this.setName(name);
	}
	public void setName(String name) {
		this.name = name;
		this.init();
	}


	
}
