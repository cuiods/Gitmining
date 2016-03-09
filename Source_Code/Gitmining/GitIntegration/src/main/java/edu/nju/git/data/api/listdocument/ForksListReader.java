package edu.nju.git.data.api.listdocument;

public class ForksListReader extends ListDocumentReader {

	public ForksListReader() {
	}
	private String url_string;
	public ForksListReader(String fullname) {
		this.setNames(fullname);
	}

	
	public ForksListReader(String owner,String name) {
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
