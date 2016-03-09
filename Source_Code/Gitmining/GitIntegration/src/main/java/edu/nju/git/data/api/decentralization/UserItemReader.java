package edu.nju.git.data.api.decentralization;

import edu.nju.git.data.api.URLreader;
import edu.nju.git.data.api.service.ItemService;

public class UserItemReader implements ItemService{

	private String name;
	public UserItemReader(String name) {
		this.name = name;
	}


	
	/**
	 * <p>http://www.gitmining.net/api/user/{user}/item/{item}
     * <br/>id,login,type,name
     * <br/>company,blog,location,email,bio
     * <br/>public_repos,public_gists,followers,following
     * <br/>created_at,updated_at
     * </p>
	 */
	@Override
	public String getItem(String item)
	{
		String string = "http://www.gitmining.net/api/user/"+this.name+"/item/"+item;
		return URLreader.getInstance().reader(string);
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
