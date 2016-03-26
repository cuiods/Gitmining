package org.GitServer.cacheinit.loader;

import org.GitServer.cacheinit.loader.api.APIconfig;
import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;

public class CommitLoader extends AbstractPullIssueCommitLoader{
	public CommitLoader(){
		super("date");
	}

	@Override
	protected String getURL(int page) {
		return "https://api.github.com/repos/"+fullname+"/commits"+APIconfig.getClientidandsecret()+"&page="+page;
		//return "http://gitmining.net/api/repository/"+fullname+"/commits?page="+page;
	}
}
