package org.GitServer.cacheinit.loader;

import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;

/**
 * read out all pull for one repo.
 * @author daixinyan
 * @date 2016-03-24
 */
public class PullsLoader extends AbstractPullIssueCommitLoader{

	public PullsLoader() {
		super("created_at");
	}

	@Override
	protected String getURL(int page) {
		//return "https://api.github.com/repos/"+fullname+"/pulls"+APIconfig.getClientidandsecret()+"&page="+page;
		return "http://gitmining.net/api/repository/"+fullname+"/pulls?page="+page;
	}

	
}
