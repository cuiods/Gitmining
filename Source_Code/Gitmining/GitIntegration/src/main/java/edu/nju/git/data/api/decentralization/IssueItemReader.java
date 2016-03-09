package edu.nju.git.data.api.decentralization;

import edu.nju.git.data.api.URLreader;
import edu.nju.git.data.api.service.ItemService;

public class IssueItemReader implements ItemService {

	public IssueItemReader() {
	}
	private String url_string;
	public IssueItemReader(String fullname,String sha) {
		this.setNames(fullname,sha);
	}

	public void setNames(String fullname,String sha){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/issue/"+sha+"/item/";
	}
	public IssueItemReader(String owner,String name,String sha) {
		this.setNames(owner+"/"+name,sha);
	}

	public void setNames(String owner,String name,String sha){
		this.setNames(owner, name, sha);
	}
	/**
	 * <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/issue/{number}/item/{item}
     * 查询某个Issue的某项信息
     * item可接受的参数有：
     * state,locked,title,body
     * user,user_id,user_type
     * created_at,updates_at,closed_at,merged_at
     * </p>
	 */
	@Override
	public String getItem(String item) {
		String string = url_string+item;
		return URLreader.getInstance().reader(string);
	}

}
