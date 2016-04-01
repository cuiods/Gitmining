package org.GitServer.cacheinit.loader;

import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.listfactory.ListJsonReader;

public class UserNamesLoader {

	private final String url = "http://gitmining.net/api/user/names?page=";
	private ListJsonReader listJsonReader;
	
	private List<String> usernames;
	public List<String> getUsernames() {
		return usernames;
	}
	public UserNamesLoader(){
		listJsonReader = new ListJsonReader();
		initNames();
	}
	
	private void initNames(){
		usernames = new ArrayList<>();
		for(int i=1; true; i++){
			listJsonReader.setUrl(url+i);
			List<String> tempUserNames = listJsonReader.getNames();
			usernames.addAll(tempUserNames);
			if(tempUserNames.size()<50){
				break;
			}
			System.out.println("done reading page ……"+i);
		}
		
		System.out.println("done reading users' name");
	}
}
