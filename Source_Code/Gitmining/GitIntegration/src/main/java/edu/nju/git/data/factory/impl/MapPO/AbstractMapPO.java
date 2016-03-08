package edu.nju.git.data.factory.impl.MapPO;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.data.api.service.MapService;
import edu.nju.git.data.factory.service.POfactory;

public abstract class AbstractMapPO<T> implements POfactory<T>{

	/**
	 * @see MapService
	 */
	protected MapService mapService;

	/**
	 * keys in map,matches fields' name in T;
	 * values in map,matcher fields' value in T.<br/>
	 * map's size can only equal or large than fields number 
	 * <br/><b>precondition</b>：map must match with type T 
	 * <br/><b>postcondition</b>：create a new PO object
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public abstract T getPO() ;

    protected Map<String, Object> map;
    protected int getInteger(String key)
    {
    	try{
    		return Integer.parseInt(map.get(key).toString());
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    		return 0;
    	}
    }
    protected String getString(String key)
    {
    	Object value= map.get(key);
    	return value==null? "" : value.toString();
    }
}
