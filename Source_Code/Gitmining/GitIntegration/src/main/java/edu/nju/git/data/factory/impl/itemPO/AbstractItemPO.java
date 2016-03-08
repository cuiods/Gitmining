package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.data.api.service.ItemService;
import edu.nju.git.data.factory.service.POfactory;

/**
 * the parent class of pofactories ,who read a po by single api.
 * ( we only get one value by one such api)
 * @author daixinyan
 * @date 2016-03-08
 * @param <T>
 */
public abstract class AbstractItemPO<T> implements POfactory<T>{

	/**
	 * @see ItemService
	 */
	protected ItemService itemService;
	
}
