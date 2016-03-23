package org.GitServer.cacheinit.loader.listfactory;

public class ForksListReader extends ListJsonReader {

	/**
	 * @param fullname "{owner}/{reponame}"
	 */
	public ForksListReader(String fullname) {
		super("http://www.gitmining.net/api/repository/"+fullname+"/forks/names");
	}
	
	public ForksListReader(String owner,String name) {
		this(owner+"/"+name);
	}
}
