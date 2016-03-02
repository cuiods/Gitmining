package edu.nju.git.VO;

/**
 * <b>breif</b> info of a user.<br>
 * used as OwnerVO, ContributorVO and CollaberatorVO, etc.
 * @author cuihao
 * @date 2016-03-02 13:00:54
 */
public class UserBriefVO {
	private String id;
	private String login;
	private int followNum = 0;
	private int followingNum = 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getFollowNum() {
		return followNum;
	}
	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}
	public int getFollowingNum() {
		return followingNum;
	}
	public void setFollowingNum(int followingNum) {
		this.followingNum = followingNum;
	}
	
}
