package edu.nju.git.data.api.githubapi;

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
		this.url_string  = "https://api.github.com/repos/"+fullname+"/collaborators";
		init(url_string);
	}

}
