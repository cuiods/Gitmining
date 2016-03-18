package edu.nju.git.bl.service;

import edu.nju.git.VO.MyChartVO;
/**
 * @author daixinyan
 * @date 2016-03-18
 */
public interface UserStatBlService {

	/**
	 * <br/><b>precondition</b>：the client can connect to the internet, 
	 *         and the configure port (for RMI) are not be used.
	 * <br/><b>postcondition</b>：return all types of user 
	 *         and corresponding Quantity of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserType();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count all companies who has Employee on github, 
	 *         and return the corresponding Quantities of employee.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statCompanyUser();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count all blogs user have , 
	 *         and return the corresponding Quantities of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserBlog();
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count all locations users have written, 
	 *         and return the corresponding Quantities of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserLocation();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count all email type users have written, 
	 *         and return the corresponding Quantities of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserEmail();
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count all user's register time, 
	 *         and return the corresponding Quantities of user in a period time.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserCreateTime();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count  numbers of Repo that every user own, 
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserOwnRepo();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count numbers of Gist that every user own, 
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserGist();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count numbers of Followers that every user own, 
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserFolllowers();
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count names of Organization that every user belongs to, 
	 *         and return the corresponding Quantities of user in every company .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statOrganizationUser();
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count names of Organization that every user belongs to, 
	 *         and return the corresponding Quantities of Repo in every company .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statOrganizationRepo();
}
