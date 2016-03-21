package edu.nju.git.VO;

import java.io.Serializable;

import edu.nju.git.type.OwnerType;

/**
 * view info of a user
 * @author cuihao
 *
 */

public class UserVO implements Serializable{
	
	private static final long serialVersionUID = -2312532160677357247L;
	private String login;
	private OwnerType type;
	private String name;
	private String company;
	private String blog;
	private String location;
	private String email;
	private String bio;
	private int followNum = 0;
	private int followingNum = 0;
	private String create_at;
	private String update_at;
	private int public_repos;
	private int public_gists;
	private int num_subscriber;
	private int num_contributors;
	private int num_collabrator;
	
	private String avatar_url;
	
	/**
	 * the count of star/fork/subscriber of'''''''repos belongs or contriobuted or conllaborated by this user
	 */
	private float radar_value;//star subs
	private float radar_gist;
	private float radar_follower;
	private float radar_ownrepos;
    private float radar_enthusiasm;  //calculated by num of contributor\collabrator and subscriber.	

	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getPublic_gists() {
		return public_gists;
	}
	public void setPublic_gists(int public_gists) {
		this.public_gists = public_gists;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}
	public float getRadar_value() {
		return radar_value;
	}
	public void setRadar_value(float radar_value) {
		this.radar_value = radar_value;
	}
	public float getRadar_gist() {
		return radar_gist;
	}
	public void setRadar_gist(float radar_gist) {
		this.radar_gist = radar_gist;
	}
	public float getRadar_follower() {
		return radar_follower;
	}
	public void setRadar_follower(float radar_follower) {
		this.radar_follower = radar_follower;
	}
	public float getRadar_ownrepos() {
		return radar_ownrepos;
	}
	public void setRadar_ownrepos(float radar_ownrepos) {
		this.radar_ownrepos = radar_ownrepos;
	}
	public float getRadar_enthusiasm() {
		return radar_enthusiasm;
	}
	public void setRadar_enthusiasm(float radar_enthusiasm) {
		this.radar_enthusiasm = radar_enthusiasm;
	}
	public int getNum_subscriber() {
		return num_subscriber;
	}
	public void setNum_subscriber(int num_subscriber) {
		this.num_subscriber = num_subscriber;
	}
	public int getNum_contributors() {
		return num_contributors;
	}
	public void setNum_contributors(int num_contributors) {
		this.num_contributors = num_contributors;
	}
	public int getNum_collabrator() {
		return num_collabrator;
	}
	public void setNum_collabrator(int num_collabrator) {
		this.num_collabrator = num_collabrator;
	}

}
