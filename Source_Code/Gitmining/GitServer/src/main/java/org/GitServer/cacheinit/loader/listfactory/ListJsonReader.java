package org.GitServer.cacheinit.loader.listfactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.api.JacksonConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class ListJsonReader {

	private List<String> names;
	/**
	 * generate list<String> in initial function
	 */
	@SuppressWarnings("unchecked")
	public ListJsonReader(String url_location) {
		try {
			URL url = new URL(url_location);
			List<String> list = JacksonConfig.getObjectMapper().readValue(url.openStream(), List.class);
			names =  list;
			return;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		names =  new ArrayList<String>();
	}

	public List<String> getNames(){
		return this.names;
	}
}
