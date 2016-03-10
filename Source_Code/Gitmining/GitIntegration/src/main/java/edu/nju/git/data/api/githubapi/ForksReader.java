package edu.nju.git.data.api.githubapi;

public class ForksReader extends ListDocumentReader {

	public ForksReader() {
	}
	private String url_string;
	public ForksReader(String fullname) {
		this.setNames(fullname);
	}

	
	public ForksReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "https://api.github.com/repos/"+fullname+"/forks";
		init(url_string);
	}

}
