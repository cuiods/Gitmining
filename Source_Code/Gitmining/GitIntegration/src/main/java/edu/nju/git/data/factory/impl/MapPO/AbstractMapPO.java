package edu.nju.git.data.factory.impl.MapPO;

import edu.nju.git.data.api.service.MapService;
import edu.nju.git.data.factory.service.POfactory;

public abstract class AbstractMapPO<T> implements POfactory<T>{

	/**
	 * @see MapService
	 */
	protected MapService mapService;

	/**
	 * keys in map,matches fields' name in T;
	 * values in map,matcher fields' value in T.<br/>
	 * map's size can only equal or large than fields number 
	 * <br/><b>precondition</b>：map must match with type T 
	 * <br/><b>postcondition</b>：create a new PO object
	 */
	public abstract T getPO() ;


}
