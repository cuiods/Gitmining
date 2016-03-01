package edu.nju.git.PO;

/**
 * simple po of a branch
 * @author cuihao
 * @date 2016-03-01 23:23:36
 */
public class BranchPO {
	/**
	 * full name of a project
	 */
	private String name;
	/**
	 * id of a branch
	 */
	private String id;
	/**
	 * value of SHA
	 */
	private String SHA;
	/**
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return {@link #SHA}
	 */
	public String getSHA() {
		return SHA;
	}
	public void setSHA(String sHA) {
		SHA = sHA;
	}
	
	
}
