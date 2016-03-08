package edu.nju.git.data.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
/**
 * unit testing pass
 * get all repositories full name.
 * @author daixinyan
 * @date 2016-03-06
 */
public class RepositoriesReader {

	private final String url_location = "http://www.gitmining.net/api/repository/names";
	/**
	 * full name of repositories 
	 */
	private List<String> names;
	
	/**
	 * generate list<String> in initial function
	 */
	@SuppressWarnings("unchecked")
	public RepositoriesReader()
	{
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
