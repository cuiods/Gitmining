package edu.nju.git.data.api.centralization;

import edu.nju.git.data.api.abstractservice.AbstractMapGetter;
/**
 *  
 * @author daixinyan
 * @date 2016-03-09
 */
public class CommitMapReader extends AbstractMapGetter {

	public CommitMapReader() {
	}

	private String fullname;
	private String commit_sha;
	
	public CommitMapReader(String fullnames,String branchname) {
		this.setNames(fullnames, branchname);
	}

	private void setNames(String fullnames,String branchname){
		this.fullname = fullnames;
		this.commit_sha = branchname;
		this.init();
	}
	
	@Override
	protected String getUrl() {
		return "https://api.github.com/repos"+fullname+"/commit/"+commit_sha;
	//	return "http://www.gitmining.net/api/repository/"+fullname+"/commit/"+commit_sha;
	}
	/**
	 * exmaple:
	 * 
	 * {
  "sha": "7b645b426d046bb0a0ca3b923bd8cc74ca3723ff",
  "commit": {
    "author": {
      "name": "Eric Lindvall",
      "email": "eric@5stops.com",
      "date": "2015-10-19T19:26:24Z"
    },
    "committer": {
      "name": "Eric Lindvall",
      "email": "eric@5stops.com",
      "date": "2015-10-19T19:26:24Z"
    },
    "message": "Merge pull request #224 from DamirAinullin/master\n\nFixing of incorrect comment",
    "tree": {
      "sha": "74cd1b323e09f80ecf5ae98b773011f7f2eddba8",
      "url": "https://api.github.com/repos/mojombo/god/git/trees/74cd1b323e09f80ecf5ae98b773011f7f2eddba8"
    },
    "url": "https://api.github.com/repos/mojombo/god/git/commits/7b645b426d046bb0a0ca3b923bd8cc74ca3723ff",
    "comment_count": 0
  },
  "url": "https://api.github.com/repos/mojombo/god/commits/7b645b426d046bb0a0ca3b923bd8cc74ca3723ff",
  "html_url": "https://github.com/mojombo/god/commit/7b645b426d046bb0a0ca3b923bd8cc74ca3723ff",
  "comments_url": "https://api.github.com/repos/mojombo/god/commits/7b645b426d046bb0a0ca3b923bd8cc74ca3723ff/comments",
  "author": {
    "login": "eric",
    "id": 470,
    "avatar_url": "https://avatars.githubusercontent.com/u/470?v=3",
    "gravatar_id": "",
    "url": "https://api.github.com/users/eric",
    "html_url": "https://github.com/eric",
    "followers_url": "https://api.github.com/users/eric/followers",
    "following_url": "https://api.github.com/users/eric/following{/other_user}",
    "gists_url": "https://api.github.com/users/eric/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/eric/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/eric/subscriptions",
    "organizations_url": "https://api.github.com/users/eric/orgs",
    "repos_url": "https://api.github.com/users/eric/repos",
    "events_url": "https://api.github.com/users/eric/events{/privacy}",
    "received_events_url": "https://api.github.com/users/eric/received_events",
    "type": "User",
    "site_admin": false
  },
  "committer": {
    "login": "eric",
    "id": 470,
    "avatar_url": "https://avatars.githubusercontent.com/u/470?v=3",
    "gravatar_id": "",
    "url": "https://api.github.com/users/eric",
    "html_url": "https://github.com/eric",
    "followers_url": "https://api.github.com/users/eric/followers",
    "following_url": "https://api.github.com/users/eric/following{/other_user}",
    "gists_url": "https://api.github.com/users/eric/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/eric/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/eric/subscriptions",
    "organizations_url": "https://api.github.com/users/eric/orgs",
    "repos_url": "https://api.github.com/users/eric/repos",
    "events_url": "https://api.github.com/users/eric/events{/privacy}",
    "received_events_url": "https://api.github.com/users/eric/received_events",
    "type": "User",
    "site_admin": false
  },
  "parents": [
    {
      "sha": "d8b0e58f3394216c00df095c99ac1f191eacb56b",
      "url": "https://api.github.com/repos/mojombo/god/commits/d8b0e58f3394216c00df095c99ac1f191eacb56b",
      "html_url": "https://github.com/mojombo/god/commit/d8b0e58f3394216c00df095c99ac1f191eacb56b"
    },
    {
      "sha": "d2b0882eb3151000c6dd4b96f43811a1d48c54a1",
      "url": "https://api.github.com/repos/mojombo/god/commits/d2b0882eb3151000c6dd4b96f43811a1d48c54a1",
      "html_url": "https://github.com/mojombo/god/commit/d2b0882eb3151000c6dd4b96f43811a1d48c54a1"
    }
  ],
  "stats": {
    "total": 2,
    "additions": 1,
    "deletions": 1
  },
  "files": [
    {
      "sha": "a62f6ee22dd5cdcb057a7b7955606c68467ef1e9",
      "filename": "lib/god/conditions/socket_responding.rb",
      "status": "modified",
      "additions": 1,
      "deletions": 1,
      "changes": 2,
      "blob_url": "https://github.com/mojombo/god/blob/7b645b426d046bb0a0ca3b923bd8cc74ca3723ff/lib/god/conditions/socket_responding.rb",
      "raw_url": "https://github.com/mojombo/god/raw/7b645b426d046bb0a0ca3b923bd8cc74ca3723ff/lib/god/conditions/socket_responding.rb",
      "contents_url": "https://api.github.com/repos/mojombo/god/contents/lib/god/conditions/socket_responding.rb?ref=7b645b426d046bb0a0ca3b923bd8cc74ca3723ff",
      "patch": "@@ -52,7 +52,7 @@ module Conditions\n     #\n     # on.condition(:socket_responding) do |c|\n     #   c.family = 'unix'\n-    #   c.port = '/tmp/sock'\n+    #   c.path = '/tmp/sock'\n     # end\n     #\n     class SocketResponding < PollCondition"
    }
  ],
  "fn": "mojombo/god"
}
	 */

}
