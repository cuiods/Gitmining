package org.GitServer.cacheinit.loader.listfactory;

public class CollaboratorsListReader extends ListJsonReader {

	public CollaboratorsListReader(String fullname) {
		super("http://www.gitmining.net/api/repository/"+fullname+"/collaborators/login");
	}

	public CollaboratorsListReader(String owner,String name) {
		this(owner+"/"+name);
	}
}
