package edu.nju.git.data.init.service;

public interface InitialService {

	/**
	 * 
	 * 
	 * <br/><b>precondition</b>： null
	 * <br/><b>postcondition</b>：initial the local data which is keep persistent.Generate two files in cache, if not exist
	 * @date 2016-03-06
	 */
	public void excute();
}
