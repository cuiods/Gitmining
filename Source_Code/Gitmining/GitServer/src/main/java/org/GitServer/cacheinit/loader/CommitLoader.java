package org.GitServer.cacheinit.loader;

import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;

public class CommitLoader extends AbstractPullIssueCommitLoader{
	public CommitLoader(){
		super("Date");
	}

	@Override
	protected String getURL(int page) {
		return "http://gitmining.net/api/repository/"+fullname+"/commits?page="+page;
	}
}
