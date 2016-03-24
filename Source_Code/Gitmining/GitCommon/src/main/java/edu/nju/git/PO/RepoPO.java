package edu.nju.git.PO;

import java.io.Serializable;

/**
 * info of repository<br>
 * <b>Note:</b><br>Lists don't have a set method, use add method.<br>
 * For example:<br>
 * <code>repo.addIssue(name)</code>;
 * @author cuihao
 * @date 2016-03-01 22:52:22
 */
public class RepoPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8733186214627773302L;
	private String name;
	private String ownerName;
	private int size;
	private String language;
	//	private OwnerType type;		//not need
	private String url;
	private String description;
	private String create_at;
	private String update_at;
	private int num_stars;
	private int num_forks;
	private int num_subscribers;


	private int num_contributors;
	private int num_collaborators;

	private int num_commits;
	private int num_pulls;
	private int num_issues;

	public RepoPO() {

	}

	//for radar chart to use
	public double getRepoActivity() {
		return 0.7*num_commits + 0.2*num_issues + 0.1*num_pulls;
	}

	//for radar chart to use
	public double getPopular() {
		return num_stars*0.6 + num_subscribers*0.4;
	}

	//for radar chart to use
	public double getComplexity() {
		return num_contributors*0.3 + num_collaborators*0.7;
	}

	//for radar to use
	public int getNum_forks() {
		return num_forks;
	}

	//for radar to use
	public int getSize() {
		return size;
	}

	public int getNum_contrbutors() {
		return num_contributors;
	}

	public void setNum_contrbutors(int num_contrbutors) {
		this.num_contributors = num_contrbutors;
	}

	public int getNum_collaborators() {
		return num_collaborators;
	}

	public void setNum_collaborators(int num_collaborators) {
		this.num_collaborators = num_collaborators;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getNum_stars() {
		return num_stars;
	}

	public void setNum_stars(int num_stars) {
		this.num_stars = num_stars;
	}

	public void setNum_forks(int num_forks) {
		this.num_forks = num_forks;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNum_subscribers() {
		return num_subscribers;
	}

	public void setNum_subscribers(int num_subscribers) {
		this.num_subscribers = num_subscribers;
	}

	public int getNum_commits() {
		return num_commits;
	}

	public void setNum_commits(int num_commits) {
		this.num_commits = num_commits;
	}

	public int getNum_issues() {
		return num_issues;
	}

	public void setNum_issues(int num_issues) {
		this.num_issues = num_issues;
	}

	public int getNum_pulls() {
		return num_pulls;
	}

	public void setNum_pulls(int num_pulls) {
		this.num_pulls = num_pulls;
	}

	//for userpo to use, userpo use this to compute repopo's value
	public double getRepoValue() {
		return num_stars*0.4 + num_forks*0.3 + num_subscribers*0.3;
	}
}
