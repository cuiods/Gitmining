package edu.nju.git.data.api.githubapi;

public class IssuesReader extends ListDocumentReader {

	public IssuesReader() {
	}
	private String url_string;
	public IssuesReader(String fullname) {
		this.setNames(fullname);
	}

	
	public IssuesReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "https://api.github.com/repos/"+fullname+"/issues";
		init(url_string);
	}

}
