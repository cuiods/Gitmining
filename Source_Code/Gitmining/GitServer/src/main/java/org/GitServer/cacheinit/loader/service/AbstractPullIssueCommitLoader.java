package org.GitServer.cacheinit.loader.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.api.JacksonConfig;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractPullIssueCommitLoader {

	protected String fullname;

	public void setName(String name) {
		this.fullname = name;
	}

	protected String key;
	protected AbstractPullIssueCommitLoader(String key){
		this.key =key;
	}
	/**
	 * <br/><b>precondition</b>： the fullname of repo must be set.
	 * <br/><b>postcondition</b>：read out a list of date.
	 * @return
	 * @date 2016-03-24
	 */
	public List<String> read(){
		
		List<String> pullDateList = new ArrayList<String>();
		for(int i = 1; true; i++){
			List<String> temp = this.read(i);
			if(temp!=null){
				pullDateList.addAll(temp);
			}
			if(temp.isEmpty()){
				break;
			}
		}
		return pullDateList;
	}
	
	/**
	 * read out all information at page {page}
	 */
	protected List<String> read(int page){
		List<String> listPerPage = new ArrayList<String>();
		try {
			JsonNode jsonNode = JacksonConfig.getObjectMapper().readTree(new URL(this.getURL(page)));
		    for (JsonNode jsonNode2 : jsonNode) {
				listPerPage.add(jsonNode2.get("").asText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listPerPage;
	}
	
	/**
	 * @return return url 
	 */
	protected abstract String getURL(int page);
}
