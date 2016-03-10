package edu.nju.git.data.api.githubapi;

public class ContributorsReader extends ListDocumentReader {

	public ContributorsReader() {
	}
	private String url_string;
	public ContributorsReader(String fullname) {
		this.setNames(fullname);
	}

	
	public ContributorsReader(String owner,String name) {
		this.setNames(owner+"/"+name);
	}

	public void setNames(String owner,String name){
		this.setNames(owner, name);
	}
	public void setNames(String fullname){
		if(APIconfig.isGithub){
			this.url_string  = "https://api.github.com/repos/"+fullname+"/contributors";
		}else{
			this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/contributors";
		}
		init(url_string);
	}

}
