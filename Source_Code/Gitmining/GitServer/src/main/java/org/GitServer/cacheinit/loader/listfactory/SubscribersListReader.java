package org.GitServer.cacheinit.loader.listfactory;

public class SubscribersListReader extends ListJsonReader {

	/**
	 * @param fullname "{owner}/{reponame}"
	 */
	public SubscribersListReader(String fullname) {
		super("http://www.gitmining.net/api/user/"+fullname+"/subscribers/names");
	}
	
	public SubscribersListReader(String owner,String name) {
		this(owner+"/"+name);
	}
	
	public SubscribersListReader(String fullname,int page) {
		super("http://www.gitmining.net/api/user/"+fullname+"/subscribers/names?page="+page);
	}
	
	public SubscribersListReader(String owner,String name,int page) {
		this(owner+"/"+name,page);
	}

}
