package edu.nju.git.data.init.service;
/**
 * write user information form cloud to file.
 * @author daixinyan
 * @date 2016-03-06
 */
public interface UserInitService {

	/** 
	 * this method will try to read data from external interface, 
	 * and extract some data to save in local file
	 * <br/><b>precondition</b>：file "cache/userinfo.txt" not exists or is null.
	 * <br/><b>postcondition</b>：rewrite file.
	 * @date 2016-03-06
	 * @see edu.nju.git.PO.UserBriefPO
	 */
	public void init();
}
