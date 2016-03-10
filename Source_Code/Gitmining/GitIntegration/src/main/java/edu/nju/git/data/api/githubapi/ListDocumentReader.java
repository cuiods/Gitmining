package edu.nju.git.data.api.githubapi;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.data.api.JacksonConfig;

public abstract class ListDocumentReader {

	protected JsonNode jsonNode;
	public ListDocumentReader() {
	}
	public void setUrl(String url){
		init(url);
	}
	protected void init(String url){
		try {
			jsonNode = JacksonConfig.getObjectMapper().readTree(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public JsonNode getNode(){
		return jsonNode;
	}
}
