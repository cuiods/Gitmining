package org.GitServer.cacheinit.loader.service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.api.JacksonConfig;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class AbstractPullIssueCommitLoader {

	protected String fullname;

	public void setName(String name) {
		this.fullname = name;
	}

	protected String key;
	protected AbstractPullIssueCommitLoader(String key){
		this.key =key;
	}
	/**
	 * read out all Infomation page by page, 
	 * <br/>if one page's size smaller than   default page size , stop reading and break to return.
	 * <br/>if one page's value(or one string element in List) equals the last page's, 
	 * stop reading and break to return
	 * <br/><b>precondition</b>： the fullname of repo must be set.
	 * <br/><b>postcondition</b>：read out a list of date.
	 * @return if no exception throw, return list not null
	 * @date 2016-03-24
	 */
	public List<String> read(){
		
		List<String> pullDateList = new ArrayList<String>();
		List<String> lastone = null;
		for(int page = 1; true; page++){
			List<String> temp = this.read(page);
			pullDateList.addAll(temp);
			if(temp.size()<50){
				//it means the next page is empty
				break;
			}
			if(lastone!=null){
				//it means this page's content is totally the same as last page's
				if(lastone.get(0).equals(temp.get(0))){
					break;
				}
			}
			lastone = temp;
		}
		return pullDateList;
	}
	
	/**
	 * <br/><b>postcondition</b>： return list not null,which can be safely used
	 * @param page 
	 * @return read out all information at page {page}
	 * @date 2016-03-25
	 */
	protected List<String> read(int page){
		List<String> listPerPage = new ArrayList<String>();
		try {
			URLConnection urlConnection =new URL(getURL(page)).openConnection();
			urlConnection.setConnectTimeout(10000);
			urlConnection.setReadTimeout(10000);
			JsonNode jsonNode = 
					JacksonConfig.getObjectMapper().readTree(urlConnection.getInputStream());
			for (JsonNode jsonNode2 : jsonNode) {
				JsonNode node = jsonNode2.findValue(key);
				if(node!=null){
					listPerPage.add(node.toString());
				}
				
			}
		}catch (SocketTimeoutException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return listPerPage;
	}
	
	/**
	 * @return return url 
	 */
	protected abstract String getURL(int page);
}
