package edu.nju.git.data.factory.service;

/**
 * keys in map,matches fields' name in T;
 * values in map,matcher fields' value in T.<br/>
 * map's size can only equal or large than fields number 
 * <br/><b>precondition</b>：map must match with type T 
 * <br/><b>postcondition</b>：create a new PO object
 */
public interface POfactory<T> {

	
	/**
	 * 
	 * <br/><b>precondition</b>：null
	 * <br/><b>postcondition</b>：read data from cloud api.
	 * @return a PO ,if not exsits, return null
	 * @date 2016-03-08
	 */
	public T getPO() ;
}
