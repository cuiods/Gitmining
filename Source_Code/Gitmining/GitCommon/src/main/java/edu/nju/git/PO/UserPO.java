package edu.nju.git.PO;

import edu.nju.git.type.OwnerType;

/**
 * info of user
 * 
 * @author cuihao
 * @date 2016-03-01 23:01:49
 */
public class UserPO {
	private String login;
	private OwnerType type;
	private String name;
	private String company;
	private String blog;
	private String location;
	private String email;
	private String bio;

	private int followNum;
	private int followingNum;
	private String create_at;
	private String update_at;

	private int public_repos;
	private int public_gists;

	private int num_subscribe;
	private int num_contribute;
	private int num_collaborate;

	private String avatar_url;

	private double userValue;	//this field is compute by outer class and set to this class

	public UserPO() {

	}

	//for radar chart to use
	public double getUserActivity() {
		//the formula can be changed
		return num_collaborate*0.5 + num_contribute*0.2 + num_subscribe*0.3;
	}

	//for radar chart to use
	public double getUserValue() {
		return userValue;
	}

	//for radar chart to use
	public int getPublic_repos() {
		return public_repos;
	}

	//for radar chart to use
	public int getPublic_gists() {
		return public_gists;
	}

	//for radar chart to use
	public int getFollowNum() {
		return followNum;
	}



	public void setUserValue(double userValue) {
		this.userValue = userValue;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public OwnerType getType() {
		return type;
	}

	public void setType(OwnerType type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
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

	public void setPublic_gists(int public_gists) {
		this.public_gists = public_gists;
	}

	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}


	public void setNum_subscribe(int num_subscribe) {
		this.num_subscribe = num_subscribe;
	}

	public void setNum_contribute(int num_contribute) {
		this.num_contribute = num_contribute;
	}

	public void setNum_collaborate(int num_collaborate) {
		this.num_collaborate = num_collaborate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
