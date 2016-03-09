package edu.nju.git.data.api.listdocument;

public class BranchesListReader extends ListDocumentReader {

	public BranchesListReader() {
	}
	private String url_string;
	public BranchesListReader(String fullname) {
		this.setNames(fullname);
	}

	
	public BranchesListReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/branches";
		init(url_string);
	}

}
