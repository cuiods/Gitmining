package edu.nju.git.data.api.listdocument;

public class CommitsReader extends ListDocumentReader {

	public CommitsReader() {
	}
	private String url_string;
	public CommitsReader(String fullname) {
		this.setNames(fullname);
	}

	
	public CommitsReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/commits";
		init(url_string);
	}

}
