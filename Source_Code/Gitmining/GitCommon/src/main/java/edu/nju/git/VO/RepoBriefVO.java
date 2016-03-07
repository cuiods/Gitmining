package edu.nju.git.VO;

/**
 * simple view info of a repository
 * @author cuihao
 * @date 2016-03-02 12:10:49
 */
public class RepoBriefVO {
	private String owner;
	private String name;
	private String description;
	private int num_stars;
	private int num_forks;
	private int num_subscribers;
	private String lastUpdate;

	public RepoBriefVO(String owner,String description, String lastUpdate,
					   String name, int num_contributors, int num_forks, int num_stars) {
		this.owner = owner;
		this.description = description;
		this.lastUpdate = lastUpdate;
		this.name = name;
		this.num_subscribers = num_contributors;
		this.num_forks = num_forks;
		this.num_stars = num_stars;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		return num_subscribers;
	}
	public void setNum_contributors(int num_contributors) {
		this.num_subscribers = num_contributors;
	}
	public String getLastUpdate(){
		return this.lastUpdate;
	}
	public void setLastUpdate(String update){
		this.lastUpdate = update;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}


}
