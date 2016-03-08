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
 * read detail repository information by owner/repo_name pair
 * by read a map
 * @author daixinyan
 * @date 2016-03-06
 */
public class RepoMapReader implements MapService{

	
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap()
	{
		try{
			URL url = new URL(url_location+ owner +"/"+repo);
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
	
	private final String url_location = "http://www.gitmining.net/api/repository/";
	private String owner;
	private String repo;
	
	public RepoMapReader(){}
	
	public RepoMapReader(String owner, String repo) {
		super();
		this.owner = owner;
		this.repo = repo;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public void setRepo(String repo) {
		this.repo = repo;
	}

    /**
     * <br/><b>precondition</b>：str's formatter must be right
     * <br/><b>postcondition</b>：set fields of ReoReader
     * @param str must be like that "{owner}/{repository}" 
     * @date 2016-03-06
     */
	public void setNames(String str)
	{
		String[] names = str.split("/");
		owner = names[0];
		repo = names[1];
	}
	

}
