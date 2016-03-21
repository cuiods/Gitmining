package edu.nju.git.VO;

import java.io.Serializable;

/**
 * the pullVO contains the basic information of pull request.
 * @author daixinyan
 * @date 2016-03-21
 */
public class PullVO implements Serializable{

	private static final long serialVersionUID = 4538111313132350005L;
	private String number;
	private String created_at;
	private String update_at;
	private String title;
	private String locked;
	
	
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	
	
}
