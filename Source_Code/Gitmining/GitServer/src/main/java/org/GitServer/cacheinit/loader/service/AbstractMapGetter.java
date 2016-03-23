package org.GitServer.cacheinit.loader.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.GitServer.cacheinit.loader.api.JacksonConfig;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * define the inteface api provide for POfactory
 * @author daixinyan
 * @date 2016-03-23
 */
public abstract class AbstractMapGetter{

    protected JsonNode map;
    
    public AbstractMapGetter() {
	}

    
    public int getInteger(String key)
    {
    	try{
    		return (map.findValue(key).asInt());
    	}catch(Exception e){
    		e.printStackTrace();
    		return 0;
    	}
    }
    public String getString(String key)
    {
    	Object value= map.get(key);
    	return value==null? "" : value.toString().replace("\"", "");
    }
    public boolean getBoolean(String item)
    {
    	try {
    		return map.get(item).asBoolean();
		} catch (Exception e) {
			return false;
		}
    	
    }
    
    protected abstract String getUrl();
    
	protected void init(){
		try{
			URL url = new URL(getUrl());
			this.map = JacksonConfig.getObjectMapper().readTree(url);
		}catch(MalformedURLException e){
			e.printStackTrace();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
