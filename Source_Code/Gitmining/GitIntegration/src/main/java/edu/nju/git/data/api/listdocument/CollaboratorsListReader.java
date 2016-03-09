package edu.nju.git.data.api.listdocument;

public class CollaboratorsListReader extends ListDocumentReader {

	public CollaboratorsListReader() {
	}
	private String url_string;
	public CollaboratorsListReader(String fullname) {
		this.setNames(fullname);
	}

	
	public CollaboratorsListReader(String owner,String name) {
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
