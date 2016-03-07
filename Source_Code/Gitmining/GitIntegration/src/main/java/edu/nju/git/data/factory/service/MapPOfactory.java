package edu.nju.git.data.factory.service;

import java.util.Map;
/**
 * map must match with type T 
 * @author daixinyan
 * @date 2016-03-07
 * @param <T>
 */
public interface MapPOfactory<T> {
	/**
	 * 
	 * keys in map,matches fields' name in T;
	 * values in map,matcher fields' value in T.<br/>
	 * map's size can only equal or large than fields number 
	 * <br/><b>precondition</b>：map must match with type T 
	 * <br/><b>postcondition</b>：create a new PO object 
	 * @param map
	 * @return
	 * @date 2016-03-07
	 */
	public T getPO(Map<String, Object> map);

}
