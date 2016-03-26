package org.GitServer.cacheinit.loader;

import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;

public class IssueLoader extends AbstractPullIssueCommitLoader{
	public IssueLoader(){
		super("created_at");
	}

	@Override
	protected String getURL(int page) {
//		return "https://api.github.com/repos/"+fullname+"/issues"+APIconfig.getClientidandsecret()+"&page="+page;
		return "http://gitmining.net/api/repository/"+fullname+"/issues?page="+page;
	}
}
