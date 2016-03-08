package edu.nju.git.PO;

import java.io.Serializable;

/**
 * This class is used in data layer to store <b>ALL</b> the indexes of repository information.
 * <p>In order to save network expenditure, we save all the repositories' <b>brief</b> information<br>
 * to local disk in advance. Therefor when we search user information, we can refer to this class<br>
 * instead of database(Actually we want to user database but Professor Liu forbid us from using it.<br>
 * Holy Shit!)
 * @author benchaodong
 * @date 2016-03-04
 */
public class RepoBriefPO implements Serializable{
	private static final long serialVersionUID = 3586985796146025910L;
	private String owner;
	private String name;
	private String description;
	private int num_stars;
	private int num_forks;
	private int num_subscribers;
	private String lastUpdate;

	public RepoBriefPO(){

	}

	public RepoBriefPO(String owner, String name, String description, int num_stars, int num_forks,
					   int num_subscribers, String lastUpdate) {
		this.owner = owner;
		this.name = name;
		this.description = description;
		this.num_stars = num_stars;
		this.num_forks = num_forks;
		this.num_subscribers = num_subscribers;
		this.lastUpdate = lastUpdate;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
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

	public void setNum_subscribers(int num_subscribers) {
		this.num_subscribers = num_subscribers;
	}


	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
