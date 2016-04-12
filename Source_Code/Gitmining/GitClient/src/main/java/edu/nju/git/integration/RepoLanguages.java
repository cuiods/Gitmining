package edu.nju.git.integration;

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
	private final String url ;
	public RepoLanguages(String fullname){
		url = "https://api.github.com/repos/"+fullname+"/languages"+APIconfig.getClientidandsecret();
	}
	public RepoLanguages(String owner,String name){
		this(owner+"/"+name);
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
//			System.out.println(url);
//			System.out.println(objectMapper.readValue(new URL(url), Map.class));
			return objectMapper.readValue(new URL(url), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		RepoLanguages repoLanguages = new RepoLanguages("mojombo/grit");
		Map<String, Integer> map = repoLanguages.getLaguages();
		if(map!=null){
			for (String string : map.keySet()) {
				System.out.println(string+" : "+map.get(string));
			}
		}
	}
	
}
