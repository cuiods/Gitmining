package edu.nju.git.data.api.githubapi;

public class BranchesReader extends ListDocumentReader {

	public BranchesReader() {
	}
	private String url_string;
	public BranchesReader(String fullname) {
		this.setNames(fullname);
	}

	
	public BranchesReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		if(APIconfig.isGithub){
			this.url_string  = "https://api.github.com/repos/"+fullname+"/branches";
		}else{
			this.url_string = "http://www.gitmining.net/api/repository/"+fullname+"/branches";
		}
		init(url_string);
	}

}
