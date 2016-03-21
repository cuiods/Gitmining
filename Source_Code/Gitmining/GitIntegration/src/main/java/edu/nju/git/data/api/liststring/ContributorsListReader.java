package edu.nju.git.data.api.liststring;

/**
 * read all contrbutors of single one project/repository
 * @author daixinyan
 * @date 2016-03-09
 */
public class ContributorsListReader extends ListJsonReader {

	/**
	 * @param fullname "{owner}/{reponame}"
	 */
	public ContributorsListReader(String fullname) {
		super("http://www.gitmining.net/api/repository/"+fullname+"/contributors/login");
	}
	
	public ContributorsListReader(String owner,String name) {
		this(owner+"/"+name);
	}

	
}
