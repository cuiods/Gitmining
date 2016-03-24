package org.GitServer.cacheinit.loader.api;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;


public class NodeReader {

	protected JsonNode init(String url){
		try {
			return JacksonConfig.getObjectMapper().readTree(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
