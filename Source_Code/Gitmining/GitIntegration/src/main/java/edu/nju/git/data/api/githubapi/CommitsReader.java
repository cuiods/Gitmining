package edu.nju.git.data.api.githubapi;

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
		if(APIconfig.isGithub){
			this.url_string  = "https://api.github.com/repos/"+fullname+"/commits";
		}else {
			this.url_string = "http://www.gitmining.net/api/repository/"+fullname+"/commits";
		}
		init(url_string);
	}

}
