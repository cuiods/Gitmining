package edu.nju.git.data.api.listdocument;

public class CommitsListReader extends ListDocumentReader {

	public CommitsListReader() {
	}
	private String url_string;
	public CommitsListReader(String fullname) {
		this.setNames(fullname);
	}

	
	public CommitsListReader(String owner,String name) {
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
