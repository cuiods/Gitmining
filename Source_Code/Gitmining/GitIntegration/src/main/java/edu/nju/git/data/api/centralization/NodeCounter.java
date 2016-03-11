package edu.nju.git.data.api.centralization;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.nju.git.data.api.JacksonConfig;
/**
 * count the number of json's son node
 * @author daixinyan
 * @date 2016-03-06
 */
public class NodeCounter {

	private  URL url;
	public NodeCounter(String url) throws MalformedURLException {
		this.setUrl(url);
	}
	public void setUrl(String url) throws MalformedURLException{
		this.url = new URL(url);
	}
	public int count()
	{
		ObjectMapper objectMapper = JacksonConfig.getObjectMapper();
		try {
			JsonNode ndoe = objectMapper.readTree(url.openStream());
			return ndoe.size();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	/**
	 * count number of json's grandson node 
	 * <br/><b>precondition</b>：
	 * <br/><b>postcondition</b>：
	 * @param i
	 * @return
	 * @date 2016-03-06
	 */
	public int count_subson(int i)
	{
		ObjectMapper objectMapper = JacksonConfig.getObjectMapper();
		try {
			JsonNode ndoe = objectMapper.readTree(url.openStream());
			return ndoe.get(i).size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
}
