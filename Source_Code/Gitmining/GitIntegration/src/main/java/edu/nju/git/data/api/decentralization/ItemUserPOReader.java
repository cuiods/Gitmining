package edu.nju.git.data.api.decentralization;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.nju.git.data.api.service.ItemService;

public class ItemUserPOReader implements ItemService{

	private String name;
	public ItemUserPOReader(String name) {
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
	public Object getItem(String item)
	{
		try {
			URL url = new URL("http://www.gitmining.net/api/user/"+this.name+"/item/"+item);
			return url.getContent();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
