package edu.nju.git.data.factory.impl.MapPO;

import java.util.Map;

import edu.nju.git.data.api.service.MapService;
import edu.nju.git.data.factory.impl.POfactory.AbstractPOfactory;

public abstract class AbstractMapPO<T> extends AbstractPOfactory{

	/**
	 * @see MapService
	 */
	protected MapService mapService;

	

    protected Map<String, Object> map;
    
    @Override
    protected int getInteger(String key)
    {
    	try{
    		return Integer.parseInt(map.get(key).toString());
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
    	return Boolean.parseBoolean(this.getString(item));
    }
}
