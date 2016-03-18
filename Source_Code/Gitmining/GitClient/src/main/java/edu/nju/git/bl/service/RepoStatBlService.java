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
	 * <br/><b>precondition</b>：
	 * <br/><b>postcondition</b>：calculate all repo's size, return the repo's numnber 
	 *       in a range of size. 
	 * @return
	 * @date 2016-03-18
	 */
	public MyChartVO statSize();
}
