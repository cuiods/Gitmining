package edu.nju.git.data.api.listdocument;

public class IssuesListReader extends ListDocumentReader {

	public IssuesListReader() {
	}
	private String url_string;
	public IssuesListReader(String fullname) {
		this.setNames(fullname);
	}

	
	public IssuesListReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/issues";
		init(url_string);
	}

}
