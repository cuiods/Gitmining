package edu.nju.git.VO;

import java.util.Map;

/**
 * Info of an issue
 * <br>
 * <b>warning</b> attribute {@link #state} unknown
 * @author cuihao
 * @date 2016-03-01 23:45:22
 */
public class IssueVO {
	private String id;
	//WARNING:unkown
	private String state;
	private boolean locked;
	private String title;
	private String body;
	private String userName;
	private String create_at;
	private String closed_at;
	private String update_at;
	private String merged_at;
	/**
	 * map? userName:String = coment:String
	 */
	private Map<String, String> comments;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getClosed_at() {
		return closed_at;
	}
	public void setClosed_at(String closed_at) {
		this.closed_at = closed_at;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	public String getMerged_at() {
		return merged_at;
	}
	public void setMerged_at(String merged_at) {
		this.merged_at = merged_at;
	}
	/**
	 * get map of comments
	 * @return map of {@link #comments}
	 */
	public Map<String, String> getComments() {
		return comments;
	}
	/**
	 * set map of comments
	 * @param map of {@link #comments}
	 */
	public void setComments(Map<String, String> comments) {
		this.comments = comments;
	}
}
