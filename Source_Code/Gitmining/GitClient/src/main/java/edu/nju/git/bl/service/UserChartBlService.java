package edu.nju.git.bl.service;

import edu.nju.git.VO.chartvo.MyChartVO;

import java.rmi.RemoteException;

/**
 * @author daixinyan
 * @date 2016-03-18
 */
public interface UserChartBlService {

	/**
	 * <br/><b>precondition</b>：the client can connect to the internet, 
	 *         and the configure port (for RMI) are not be used.
	 * <br/><b>postcondition</b>：return all types of user 
	 *         and corresponding Quantity of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserType() throws RemoteException;
	
	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count all companies who has Employee on github, 
	 *         and return the corresponding Quantities of employee.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statCompanyUser() throws RemoteException;

	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：count all email type users have written,
	 *         and return the corresponding Quantities of user.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserEmail() throws RemoteException;
	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：count all user's register time,
	 *         and return the corresponding Quantities of user in a period time.
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserCreateTime() throws RemoteException;

	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：count  numbers of Repo that every user own,
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserOwnRepo() throws RemoteException;

	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count numbers of Gist that every user own,
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserGist() throws RemoteException;

	/**
	 *
	 * @return
	 */
	public MyChartVO statUserCollabRepo() throws RemoteException;

	/**
	 *
	 * @return
	 */
	public MyChartVO statUserContriRepo() throws RemoteException;

	/**
	 *
	 * @return
	 */
	public MyChartVO statUserSubscrRepo() throws RemoteException;

	/**
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：count numbers of Followers that every user own,
	 *         and return the corresponding Quantities of user .
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statUserFolllowers() throws RemoteException;
}
