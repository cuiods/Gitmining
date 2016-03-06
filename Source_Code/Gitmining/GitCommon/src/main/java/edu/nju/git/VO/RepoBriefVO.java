package edu.nju.git.VO;

/**
 * simple view info of a repository
 * @author cuihao
 * @date 2016-03-02 12:10:49
 */
public class RepoBriefVO {

	private String name;
	private String url;
	private String description;
	private int num_stars = 0;
	private int num_forks = 0;
	private int num_contributors = 0;
	private String lastUpdate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLastUpdate(){
		return this.lastUpdate;
	}
	public void setLastUpdate(String update){
		this.lastUpdate = update;
	}
	
	
}
