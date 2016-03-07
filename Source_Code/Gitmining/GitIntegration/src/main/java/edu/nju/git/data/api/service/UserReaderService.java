package edu.nju.git.data.api.service;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;

public interface UserReaderService {

	/**
	 * <br/><b>precondition</b>： name must be set
	 * <br/><b>postcondition</b>：create one object
	 * @return
	 * @date 2016-03-07
	 */
	public UserBriefPO getBriefPO();
	/**
	 * <br/><b>precondition</b>： name must be set
	 * <br/><b>postcondition</b>：create one object
	 * @return
	 * @date 2016-03-07
	 */
	public UserPO getPO();
	
	public void setName(String name);
}
