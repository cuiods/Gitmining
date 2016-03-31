package edu.nju.git.VO;

import java.io.Serializable;

/**
 * <b>breif</b> info of a user.<br>
 * used as OwnerVO, ContributorVO and CollaboratorVO, etc.
 * @author cuihao
 * @date 2016-03-02 13:00:54
 */
public class UserBriefVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String login;//user name when login in github
	private int followers;//the number of people who follow this user
	private int following;//the number of people this user following
	private int public_repos;//the number of repositories of this user
	private String create_at;
	private String update_at;
	private String company;

	public UserBriefVO() {
	}

	public UserBriefVO(String login, int followers, int following, int public_repos, String create_at,
					   String update_at, String company) {
        this.followers = followers;
        this.following = following;
        this.create_at = create_at;
        this.update_at = update_at;
        this.company = company;
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

	public int getFollowing() {
		return following;
	}

	public void setFollowing(int following) {
		this.following = following;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
