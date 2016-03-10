package edu.nju.git.data.api.listdocument;

public class CollaboratorsReader extends ListDocumentReader {

	public CollaboratorsReader() {
	}
	private String url_string;
	public CollaboratorsReader(String fullname) {
		this.setNames(fullname);
	}

	
	public CollaboratorsReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/collaborators";
		init(url_string);
	}

}
