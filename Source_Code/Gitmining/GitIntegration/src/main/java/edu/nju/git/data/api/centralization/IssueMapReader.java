package edu.nju.git.data.api.centralization;

import edu.nju.git.data.api.abstractservice.AbstractMapGetter;
/**
 * no enough item.
 * @author daixinyan
 * @date 2016-03-09
 */
public class IssueMapReader extends AbstractMapGetter {

	public IssueMapReader() {
	}

	private String fullname;
	private String issue;
	
	public IssueMapReader(String fullnames,String number ) {
		this.setNames(fullnames, number );
	}

	private void setNames(String fullnames,String number ){
		this.fullname = fullnames;
		this.issue = number ;
		this.init();
	}
	
	@Override
	protected String getUrl() {
		return "https://api.github.com/repos"+fullname+"/issue/"+issue;
	//	return "http://www.gitmining.net/api/repository/"+fullname+"/issue/"+issue;
	}
/**
 * json example:
 * {
  "url": "https://api.github.com/repos/ctran/annotate_models/issues/302",
  "labels_url": "https://api.github.com/repos/ctran/annotate_models/issues/302/labels{/name}",
  "comments_url": "https://api.github.com/repos/ctran/annotate_models/issues/302/comments",
  "events_url": "https://api.github.com/repos/ctran/annotate_models/issues/302/events",
  "html_url": "https://github.com/ctran/annotate_models/pull/302",
  "id": 121573605,
  "number": 302,
  "title": "Ruby 2.3 magic comments support",
  "user": {
    "login": "b264",
    "id": 7057665,
    "avatar_url": "https://avatars.githubusercontent.com/u/7057665?v=3",
    "gravatar_id": "",
    "url": "https://api.github.com/users/b264",
    "html_url": "https://github.com/b264",
    "followers_url": "https://api.github.com/users/b264/followers",
    "following_url": "https://api.github.com/users/b264/following{/other_user}",
    "gists_url": "https://api.github.com/users/b264/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/b264/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/b264/subscriptions",
    "organizations_url": "https://api.github.com/users/b264/orgs",
    "repos_url": "https://api.github.com/users/b264/repos",
    "events_url": "https://api.github.com/users/b264/events{/privacy}",
    "received_events_url": "https://api.github.com/users/b264/received_events",
    "type": "User",
    "site_admin": false
  },
  "labels": [],
  "state": "open",
  "locked": false,
  "comments": 5,
  "created_at": "2015-12-10T20:46:09Z",
  "updated_at": "2015-12-13T20:48:31Z",
  "pull_request": {
    "url": "https://api.github.com/repos/ctran/annotate_models/pulls/302",
    "html_url": "https://github.com/ctran/annotate_models/pull/302",
    "diff_url": "https://github.com/ctran/annotate_models/pull/302.diff",
    "patch_url": "https://github.com/ctran/annotate_models/pull/302.patch"
  },
  "body": "My attempt to support Ruby 2.3 magic comments",
  "fn": "ctran/annotate_models"
}
 */
}
