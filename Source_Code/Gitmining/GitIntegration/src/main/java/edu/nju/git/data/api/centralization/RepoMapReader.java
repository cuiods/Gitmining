package edu.nju.git.data.api.centralization;

import edu.nju.git.data.api.abstractservice.AbstractMapGetter;
import edu.nju.git.data.api.githubapi.APIconfig;

/**
 * read detail repository information by owner/repo_name pair
 * by read a map
 * @author daixinyan
 * @date 2016-03-06
 */
public class RepoMapReader extends AbstractMapGetter {

	@Override
	protected String getUrl() {
		return url_location+ fullname;
	}
	
	
	private final String url_location = APIconfig.isGithub?
			"https://api.github.com/repos/":"http://www.gitmining.net/api/repository/";
	
	private String fullname ;
	public RepoMapReader(){}
	
	public RepoMapReader(String owner, String repo) {
		super();
		this.set(owner, repo);
	}

	public void set(String owner, String repo) {
		this.fullname = owner +"/"+repo;
		this.init();
	}
	public RepoMapReader(String fullname) {
		super();
		this.set(fullname);
	}

	public void set(String fullname) {
		this.fullname = fullname;
		this.init();
	}
	

	
}
