package edu.nju.git.PO;

import java.util.ArrayList;
import java.util.List;

import edu.nju.git.type.OwnerType;

/**
 * info of repository<br>
 * <b>Note:</b><br>Lists don't have a set method, use add method.<br>
 * For example:<br>
 * <code>repo.addIssue(name);
 * @author cuihao
 * @date 2016-03-01 22:52:22
 */
public class RepoPO {
	/**
	 * warning: can be enumerate
	 */
	private String language;
	private String ownerName;
	private OwnerType type;
	private String url;
	private String description;
	private String create_at;
	private String update_at;
	private int num_stars = 0;
	private int num_forks = 0;
	private int num_contributors = 0;
	/**
	 * same measure unit as api provided
	 */
	private int size;
	
	//lists of info
	private List<String> info_contributor = new ArrayList<String>();
	private List<String> info_collaborator = new ArrayList<String>();
	private List<String> info_branch = new ArrayList<String>();
	private List<String> info_fork = new ArrayList<String>();
	private List<String> info_commit = new ArrayList<String>();
	private List<String> info_issue = new ArrayList<String>();
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
	public OwnerType getType() {
		return type;
	}
	public void setType(OwnerType type) {
		this.type = type;
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
	public int getNum_forks() {
		return num_forks;
	}
	public void setNum_forks(int num_forks) {
		this.num_forks = num_forks;
	}
	public int getNum_contributors() {
		return num_contributors;
	}
	public void setNum_contributors(int num_contributors) {
		this.num_contributors = num_contributors;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<String> getInfo_contributor() {
		return info_contributor;
	}
	public void addContributor(String contributor) {
		info_contributor.add(contributor);
	}
	public List<String> getInfo_collaborator() {
		return info_collaborator;
	}
	public void addCollaborator(String collaborator) {
		info_collaborator.add(collaborator);
	}
	public List<String> getInfo_branch() {
		return info_branch;
	}
	public void addBranch(String branch) {
		info_branch.add(branch);
	}
	public List<String> getInfo_fork() {
		return info_fork;
	}
	public void addFork(String fork){
		info_fork.add(fork);
	}
	public List<String> getInfo_commit() {
		return info_commit;
	}
	public void addCommit(String commit){
		info_commit.add(commit);
	}
	public List<String> getInfo_issue() {
		return info_issue;
	}
	public void addIssue(String issue) {
		info_issue.add(issue);
	}
	
	
}
