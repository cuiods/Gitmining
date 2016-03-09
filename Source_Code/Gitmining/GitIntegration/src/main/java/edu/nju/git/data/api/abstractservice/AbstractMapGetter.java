package edu.nju.git.data.api.abstractservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.data.api.JacksonConfig;
import edu.nju.git.data.factory.impl.POfactory.AbstractFieldsGetter;

public abstract class AbstractMapGetter extends AbstractFieldsGetter{

    protected Map<String, Object> map;
    
    public AbstractMapGetter() {
	}

    
    @Override
    protected int getInteger(String key)
    {
    	try{
    		return (Integer)(map.get(key));
    	}catch(NumberFormatException e){
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
    		return (Boolean)map.get(item);
		} catch (Exception e) {
			return false;
		}
    	
    }
    
    protected abstract String getUrl();
    
    @SuppressWarnings("unchecked")
	protected void init(){
		try{
			URL url = new URL(getUrl());
			this.map = JacksonConfig.getObjectMapper().readValue(url.openStream(), Map.class);
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
