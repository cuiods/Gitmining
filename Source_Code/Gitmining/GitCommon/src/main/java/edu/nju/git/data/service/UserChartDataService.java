package edu.nju.git.data.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.nju.git.VO.MyChartVO;
/**
 * @author daixinyan
 * @date 2016-03-20
 */
public interface UserChartDataService extends Remote{

	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：return all types of user 
	 *         and corresponding Quantity of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserType()throws RemoteException;
	
	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：count all companies who has Employee on github, 
	 *         and return the corresponding Quantities of employee.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statCompanyUser()throws RemoteException;

	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：count all email type users have written, 
	 *         and return the corresponding Quantities of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserEmail();
	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：count all user's register time, 
	 *         and return the corresponding Quantities of user in a period time.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserCreateTime();
	
	/**
	 * <br/><b>precondition</b>：null
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
	 *
	 * @return
     */
	public MyChartVO statUserCollabRepo();

	/**
	 *
	 * @return
     */
	public MyChartVO statUserContriRepo();

	/**
	 *
	 * @return
     */
	public MyChartVO statUserSubscrRepo();
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count numbers of Followers that every user own, 
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserFolllowers();


}
