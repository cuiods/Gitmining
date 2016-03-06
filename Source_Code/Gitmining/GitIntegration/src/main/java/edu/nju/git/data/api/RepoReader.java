package edu.nju.git.data.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;

/**
 * read detail repository information by owner/repo_name pair
 * @author daixinyan
 * @date 2016-03-06
 */
public class RepoReader {

	/**
	 * <br/><b>precondition</b>：this.owner this.repo must be set
	 * <br/><b>postcondition</b>：return a PO
	 * @return 
	 * @date 2016-03-06
	 */
	public RepoBriefPO getBriefPO(){
		RepoBriefPO repoBriefPO = new RepoBriefPO();
		Map<String, Object> map = this.getMap();
		
		int num_contributors = 0;
		try {
			NodeCounter nodeCounter = new NodeCounter(map.get("contributors_url").toString());
			num_contributors = nodeCounter.count();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}repoBriefPO.setNum_contributors(num_contributors);
		
		repoBriefPO.setDescription(map.get("description").toString());
		repoBriefPO.setName(map.get("name").toString());

		repoBriefPO.setNum_forks((Integer)map.get("forks_count"));
		repoBriefPO.setNum_stars((Integer)map.get("stargazers_count"));
		repoBriefPO.setOwer(map.get("owner").toString());
		repoBriefPO.setUrl(map.get("html_url").toString());
		// "html_url": "https://github.com/huerlisi/CyDoc",
		// "url": "https://api.github.com/repos/huerlisi/CyDoc",
		return repoBriefPO;
	}
	/**
	 * <br/><b>precondition</b>：this.owner this.repo must be set
	 * <br/><b>postcondition</b>：return a PO
	 * @return
	 * @date 2016-03-06
	 */
	public RepoPO getPO(){
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getMap()
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
	
	public RepoReader(){}
	
	public RepoReader(String owner, String repo) {
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
