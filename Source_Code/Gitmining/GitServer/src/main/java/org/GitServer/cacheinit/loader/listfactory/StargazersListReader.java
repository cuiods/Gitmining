package org.GitServer.cacheinit.loader.listfactory;

public class StargazersListReader extends ListJsonReader {

	/**
	 * @param fullname "{owner}/{reponame}"
	 */
	public StargazersListReader(String fullname) {
		super("http://www.gitmining.net/api/user/"+fullname+"/stargazers/names");
	}
	
	public StargazersListReader(String owner,String name) {
		this(owner+"/"+name);
	}


	public StargazersListReader(String fullname,int page) {
		super("http://www.gitmining.net/api/user/"+fullname+"/stargazers/names?page="+page);
	}
	
	public StargazersListReader(String owner,String name,int page) {
		this(owner+"/"+name,page);
	}

}
