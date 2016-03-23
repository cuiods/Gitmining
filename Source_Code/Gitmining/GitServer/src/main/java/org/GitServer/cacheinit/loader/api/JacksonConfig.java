package org.GitServer.cacheinit.loader.api;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * offer singleton ObjectMapper
 * @author daixinyan
 * @date 2016-03-06
 */
public class JacksonConfig {
	
    private static ObjectMapper objectMapper;
    public static ObjectMapper  getObjectMapper()
    {
    	if(objectMapper==null)
    	{
    		JacksonConfig.create();
    	}
    	return objectMapper;
    }
    
    public  static  synchronized void create()
    {
    	if(objectMapper==null)
		{
			objectMapper = new ObjectMapper();
		}
    }
}
