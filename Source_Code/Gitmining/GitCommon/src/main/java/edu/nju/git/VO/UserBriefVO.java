package edu.nju.git.VO;

/**
 * <b>breif</b> info of a user.<br>
 * used as OwnerVO, ContributorVO and CollaboratorVO, etc.
 * @author cuihao
 * @date 2016-03-02 13:00:54
 */
public class UserBriefVO {
	private String login;//user name when login in github
	private int followers;//the number of people who follow this user
	private int public_repos;//the number of repositories of this user

	public UserBriefVO(String login, int followers, int public_repos) {
		this.followers = followers;
		this.login = login;
		this.public_repos = public_repos;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getPublic_repos() {
		return public_repos;
	}

	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}
	
}
