package edu.nju.git.VO;

/**
 * simple view of a branch
 * @author cuihao
 * @date 2016-03-01 23:23:36
 */
public class BranchVO {
	/**
	 * name of a branch
	 */
	private String name;
	/**
	 * value of commit sha
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
	 * @return {@link #SHA}
	 */
	public String getSHA() {
		return SHA;
	}
	public void setSHA(String sHA) {
		SHA = sHA;
	}
	
	
}

