package edu.nju.git.data.api.liststring;

public class CollaboratorsListReader extends ListJsonReader {

	public CollaboratorsListReader(String fullname) {
		super("http://www.gitmining.net/api/repository/"+fullname+"/collaborators/login");
	}

	public CollaboratorsListReader(String owner,String name) {
		this(owner+"/"+name);
	}
}
