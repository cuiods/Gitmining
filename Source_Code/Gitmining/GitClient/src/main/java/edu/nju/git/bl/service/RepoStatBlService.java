package edu.nju.git.bl.service;

import edu.nju.git.VO.MyChartVO;

public interface RepoStatBlService {

	/**
	 * 
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：add up the using times of languages in all repos
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statLanguage();
	/**
	 * 
	 * <br/><b>precondition</b>：can connect to the internet
	 * <br/><b>postcondition</b>：calculate the number of Repo whicg created in a period time
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statCreateTime();
	
	/**
	 * <br/><b>precondition</b>： can connect to the internet
	 * <br/><b>postcondition</b>：calculate all repo's contributors number, 
	 *        and return the repo's number in a range a contributors number. 
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statContributors();
	
	/**
	 * <br/><b>precondition</b>： can connect to the internet
	 * <br/><b>postcondition</b>：calculate all repo's Conllabrators number, 
	 *        and return the repo's number in a range a Conllabrators number. 
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statConllabrators();
	
	/**
	 * <br/><b>precondition</b>： connect to the internet
	 * <br/><b>postcondition</b>：calculate all repo's size, return the repo's numnber 
	 *       in a range of size. 
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statSize();
	/**
	 * <br/><b>precondition</b>： connect to the internet
	 * <br/><b>postcondition</b>：calculate all repo's subscribers, return the subscriber's number by group 
	 * @return
	 * @throws RemoteException
	 * @date 2016-03-21
	 */
	public MyChartVO statSubscriber() ;
	
	/**
	 * <br/><b>precondition</b>： connect to the internet
	 * <br/><b>postcondition</b>：calculate all repo's forked times, return the forked time
	 * @return
	 * @throws RemoteException
	 * @date 2016-03-21
	 */
	public MyChartVO statForks() ;
	
	/**
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：calculate all repo's stars number, return the stars number in mychartVO
	 * @return
	 * @throws RemoteException
	 * @date 2016-03-21
	 */
	public MyChartVO statStars() ;
}
