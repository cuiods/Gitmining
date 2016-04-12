package edu.nju.git.VO;

import java.io.Serializable;

/**
 * simple view info of a repository
 * @author cuihao
 * @date 2016-03-02 12:10:49
 */
public class RepoBriefVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String owner;
	private String name;
	private String description;
	private int num_stars;
	private int num_forks;
	private int num_subscribers;
	private String lastUpdate;
	private String create_at;

	public RepoBriefVO() {
	}

	public RepoBriefVO(String owner, String name, String description, int num_stars,
					   int num_forks, int num_subscribers, String lastUpdate, String creat) {
		this.owner = owner;
		this.name = name;
		this.description = description;
		this.num_stars = num_stars;
		this.num_forks = num_forks;
		this.num_subscribers = num_subscribers;
		this.lastUpdate = lastUpdate;
		this.create_at = creat;
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
	public int getNum_subscribers() {
		return num_subscribers;
	}
	public void setNum_subscribers(int num_contributors) {
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


	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

}
