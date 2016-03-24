package org.GitServer.cacheinit.loader;

import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;

public class IssueLoader extends AbstractPullIssueCommitLoader{
	public IssueLoader(){
		super("created_at");
	}

	@Override
	protected String getURL(int page) {
		return "http://gitmining.net/api/repository/"+fullname+"/commits?page="+page;
	}
}
