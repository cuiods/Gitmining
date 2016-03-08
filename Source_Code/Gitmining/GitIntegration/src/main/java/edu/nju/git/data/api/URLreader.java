package edu.nju.git.data.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLreader {

	private URLreader() {
	}
	private static URLreader instance;
	public static URLreader getInstance()
	{
		if(instance==null)
		{
			createInstance();
		}
		return instance;
	}
    private static synchronized void  createInstance(){
    	if(instance==null)
    	{
    		instance = new URLreader();
    	}
    }
    
    
	public String reader(String string)
	{
		try {
			URL url = new URL(string);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String tempString;
			while((tempString=bReader.readLine())!=null){
				stringBuffer.append(tempString);
				stringBuffer.append('\n');
			}
			String result;
			try {
				result = stringBuffer.substring(0, stringBuffer.length()-1);
			} catch (Exception e) {
				result ="";
			}
			//System.out.println(result+"/");
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
}
