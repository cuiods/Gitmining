package edu.nju.git.data.api.abstractservice;

import java.util.Map;

public interface MapService {
	/**
	 * <br/><b>precondition</b>：url must be set and right.
	 * <br/><b>postcondition</b>：read a map's keys and values in map。
	 * @return  map
	 * @date 2016-03-08
	 */
	public Map<String, Object> getMap();
}
