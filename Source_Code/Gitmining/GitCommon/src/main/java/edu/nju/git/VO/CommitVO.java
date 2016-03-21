package edu.nju.git.VO;

import java.io.Serializable;

/**
 * info of a commit
 * @author cuihao
 * @date 2016-03-01 23:53:02
 */

public class CommitVO implements Serializable{
	private static final long serialVersionUID = -3912151972882753629L;
	/**
	 * sha
	 */
	private String id;
	private String userName;
	private String date;
	private String message;
	/**
	 * number of files changed
	 */
	/*private int num_file;
	*//**
	 * total lines of code changed
	 *//*
	private int num_total;
	private int num_addition;
	private int num_deletion;*/
	/**
	 * user:String = comment:String
	 */
//	private Map<String,String> comments;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
/*	public int getNum_file() {
		return num_file;
	}
	public void setNum_file(int num_file) {
		this.num_file = num_file;
	}
	public int getNum_total() {
		return num_total;
	}
	public void setNum_total(int num_total) {
		this.num_total = num_total;
	}
	public int getNum_addition() {
		return num_addition;
	}
	public void setNum_addition(int num_addition) {
		this.num_addition = num_addition;
	}
	public int getNum_deletion() {
		return num_deletion;
	}
	public void setNum_deletion(int num_deletion) {
		this.num_deletion = num_deletion;
	}
	public Map<String, String> getComments() {
		return comments;
	}*/
	/**
	 * comment: {@link #comments}
	 * @param comments
	 */
/*	public void setComments(Map<String, String> comments) {
		this.comments = comments;
	}*/
	
	

}
