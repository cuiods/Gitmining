package edu.nju.git.PO;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class is used in data layer to store <b>ALL</b> the indexes of user information.
 * <p>In order to save network expenditure, we save all the user's <b>brief</b> information<br>
 * to local disk in advance. Therefor when we search user information, we can refer to this class<br>
 * instead of database(Actually we want to user database but Professor Liu forbid us from using it.<br>
 * Holy Shit!)
 * @author benchaodong
 * @date 2016-03-04
 */
@JsonAutoDetect
public class UserBriefPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("login")
	private String login;//user name when login in github
	@JsonProperty("followers")
	private int followers;//the number of people who follow this user
	@JsonProperty("public_repos")
	private int public_repos;//the number of repositories of this user

	public UserBriefPO() {
	}

	public UserBriefPO(String login, int followers, int public_repos) {
		this.login = login;
		this.followers = followers;
		this.public_repos = public_repos;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}
    
    
    
    
}
