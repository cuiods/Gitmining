package edu.nju.git.data.api.listdocument;

public class ContributorsListReader extends ListDocumentReader {

	public ContributorsListReader() {
	}
	private String url_string;
	public ContributorsListReader(String fullname) {
		this.setNames(fullname);
	}

	
	public ContributorsListReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/contributors";
		init(url_string);
	}

}
