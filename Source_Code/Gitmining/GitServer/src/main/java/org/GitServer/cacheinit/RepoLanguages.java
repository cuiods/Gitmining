package org.GitServer.cacheinit;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.nju.git.tools.APIconfig;

/**
 * the class for getting all languages in a repository.
 * @author daixinyan
 * @date 2016-04-12
 */
public class RepoLanguages {
	private  String url ;
	public RepoLanguages(){
	}

	public void setName(String fullname){
		url = "https://api.github.com/repos/"+fullname+"/languages"+APIconfig.getClientidandsecret();
	}
	/**
	 * <br/><b>precondition</b>：the repo full name is valueable, can connect to https://api.github.com
	 * <br/><b>postcondition</b>：
	 * @return  if anything wrong,return null; or else return map,key of map is languages and value is code lines.
	 * @date 2016-04-12
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Integer> getLaguages(){
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.print(url);
			Map<String, Integer> map = objectMapper.readValue(new URL(url), Map.class);
			System.out.println(map);
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
