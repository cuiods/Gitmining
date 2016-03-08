package edu.nju.git.data.factory.service;

public interface POfactory<T> {

	/**
	 * 
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：read data from cloud api.
	 * @return a PO ,if not exsits, return null
	 * @date 2016-03-08
	 */
	public T getPO();
}
