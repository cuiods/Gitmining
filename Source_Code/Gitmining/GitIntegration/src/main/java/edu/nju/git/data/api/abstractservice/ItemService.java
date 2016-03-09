package edu.nju.git.data.api.abstractservice;

public interface ItemService {

	/**
	 * <b>configure</b>
     * 
     * <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/pull/{number}/item/{item}
     * 查询某个Pull Request的某项信息
     * item可接受的参数有：
     * state, locked, title,body 
     * user, user_id, user_type
     * created_at, updates_at, closed_at, merged_at
     * </p>
	 * <br/><b>precondition</b>：item must be In line with the right format
	 * <br/><b>postcondition</b>：connect to the host, vist the api, reader the data.
	 * @param item 
	 * @return value , if not exsit then null
	 * @date 2016-03-08
	 */
	public String getItem(String item);
}
