package org.GitServer.cacheinit.loader.listfactory;

public class BranchesListReader extends ListJsonReader {

	/**
	 * @param fullname "{owner}/{reponame}"
	 */
	public BranchesListReader(String fullname) {
		super("http://www.gitmining.net/api/repository/"+fullname+"/branches/names");
	}
	
	public BranchesListReader(String owner,String name) {
		this(owner+"/"+name);
	}

	
}