package edu.nju.git.VO;

import java.io.Serializable;

import edu.nju.git.type.OwnerType;

/**
 * info of repository<br>
 * the basic infomation about the repository, no list thing.
 * @author cuihao
 * @date 2016-03-02 12:25:14
 */
public class RepoVO implements Serializable{
	private static final long serialVersionUID = 8951738005334507950L;
	private String name;
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
	private int num_subscribers = 0;
	/**
	 * same measure unit as api provided
	 */
	private int size;
	
	
	
	private float radar_popular;//calculated by num of subscriber and star
	private float radar_forks; 
	private float radar_size;
	private float radar_complexity; //calculated by num of contributor\collabrator
	private float radar_activity;  //calculated by num of  commit and issue and pull.	
	
	private int num_ontributors;
	private int num_collaboration;
    private int num_commit;
    private int num_issue;
    private int num_pull;
    
	
	
	//lists of info
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
	public int getSize() {
		return size;
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
	public float getRadar_popular() {
		return radar_popular;
	}
	public void setRadar_popular(float radar_popular) {
		this.radar_popular = radar_popular;
	}
	public float getRadar_forks() {
		return radar_forks;
	}
	public void setRadar_forks(float radar_forks) {
		this.radar_forks = radar_forks;
	}
	public float getRadar_size() {
		return radar_size;
	}
	public void setRadar_size(float radar_size) {
		this.radar_size = radar_size;
	}
	public float getRadar_complexity() {
		return radar_complexity;
	}
	public void setRadar_complexity(float radar_complexity) {
		this.radar_complexity = radar_complexity;
	}
	public float getRadar_activity() {
		return radar_activity;
	}
	public void setRadar_activity(float radar_activity) {
		this.radar_activity = radar_activity;
	}
	public int getNum_ontributors() {
		return num_ontributors;
	}
	public void setNum_ontributors(int num_ontributors) {
		this.num_ontributors = num_ontributors;
	}
	public int getNum_collaboration() {
		return num_collaboration;
	}
	public void setNum_collaboration(int num_collaboration) {
		this.num_collaboration = num_collaboration;
	}
	public int getNum_commit() {
		return num_commit;
	}
	public void setNum_commit(int num_commit) {
		this.num_commit = num_commit;
	}
	public int getNum_issue() {
		return num_issue;
	}
	public void setNum_issue(int num_issue) {
		this.num_issue = num_issue;
	}
	public int getNum_pull() {
		return num_pull;
	}
	public void setNum_pull(int num_pull) {
		this.num_pull = num_pull;
	}
	
	
	
}
