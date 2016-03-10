package edu.nju.git.data.api.listdocument;

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
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/forks";
		init(url_string);
	}

}
