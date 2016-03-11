package edu.nju.git.data.api.liststring;

public class IssuesListReader extends ListJsonReader {

	/**
	 * @param fullname "{owner}/{reponame}"
	 */
	public IssuesListReader(String fullname) {
		super("http://www.gitmining.net/api/repository/"+fullname+"/issues/numbers");
	}
	
	public IssuesListReader(String owner,String name) {
		this(owner+"/"+name);
	}

}
