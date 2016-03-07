package edu.nju.git.data.api.centralization;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.data.api.JacksonConfig;
import edu.nju.git.data.factory.service.MapPOfactory;

/**
 * the father class of classed 
 * @author daixinyan
 * @date 2016-03-07
 */
class ListReader <T>{

	private MapPOfactory<T> pofactory;
	private String url_location;
	
	
	public ListReader(MapPOfactory<T> pOfactory, String url_location) {
		super();
		this.pofactory = pOfactory;
		this.url_location = url_location;
	}

	
	public  List<T> getPOList()
	{
		List<Map<String, Object>> listmap = getListMap();
		List<T> re = new ArrayList<T>();
		for (Map<String, Object> map : listmap) {
			re.add(pofactory.getPO(map));
		}
		return re;
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> getListMap()
	{
		try{
			URL url = new URL(url_location);
			return JacksonConfig.getObjectMapper().readValue(url.openStream(), List.class);
		}catch(MalformedURLException e){
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Map<String, Object>>();
	}

}
