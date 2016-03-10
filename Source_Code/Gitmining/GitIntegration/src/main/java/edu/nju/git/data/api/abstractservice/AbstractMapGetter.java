package edu.nju.git.data.api.abstractservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import edu.nju.git.data.api.JacksonConfig;
import edu.nju.git.data.factory.impl.POfactory.AbstractFieldsGetter;

public abstract class AbstractMapGetter extends AbstractFieldsGetter{

    protected JsonNode map;
    
    public AbstractMapGetter() {
	}

    
    @Override
    protected int getInteger(String key)
    {
    	try{
    		return (map.findValue(key).asInt());
    	}catch(Exception e){
    		e.printStackTrace();
    		return 0;
    	}
    }
    @Override
    protected String getString(String key)
    {
    	Object value= map.get(key);
    	return value==null? "" : value.toString();
    }
    @Override
    protected boolean getBoolean(String item)
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
