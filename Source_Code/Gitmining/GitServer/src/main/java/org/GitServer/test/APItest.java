package org.GitServer.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.api.JacksonConfig;


import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.tools.APIconfig;


public class APItest {

	private List<JsonNode> commits;
	private static final int ARRAY_SIZE = 100;
	private final String prefix = "https://api.github.com/repos/daixinyan/logistics/commits"; 
	public static void main(String[] args){
		
		APItest test = new APItest();
		long start = System.currentTimeMillis();
		System.out.println(start);
		test.start();
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println(end-start);
	}
	
	public void start(){
		int page = 1;
		commits = new ArrayList<>(ARRAY_SIZE);
		do{
			String location = prefix+APIconfig.getClientidandsecret()+"page="+page;
			System.out.println("locaton:"+location);
			JsonNode onePageNodes = read(location);
			System.out.println("size:"+onePageNodes.size());
			if(onePageNodes.size()==0){
				break;
			}
			for (JsonNode jsonNode : onePageNodes) {
				commits.add(jsonNode);
			}
			page++;
		}while(true);
		System.out.println(page);
		System.out.println(commits.size());
	}
	
	private JsonNode read(String location){
		try {
			URL url = new URL(location);
			return JacksonConfig.getObjectMapper().readTree(url.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
