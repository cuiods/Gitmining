package org.GitServer.cacheinit.loader.api;

import org.GitServer.cacheinit.loader.service.AbstractMapGetter;

/**
 * read detail repository information by owner/repo_name pair
 * by read a map
 * @author daixinyan
 * @date 2016-03-06
 */
public class RepoMapReader extends AbstractMapGetter {

	@Override
	protected String getUrl() {
		if(APIconfig.isGithub){
			return url_location+ fullname+APIconfig.getClientidandsecret();
		}
		return url_location+ fullname;
	}
	
	
	private final String url_location = APIconfig.isGithub?
			"https://api.github.com/repos/" : "http://www.gitmining.net/api/repository/";
	
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
