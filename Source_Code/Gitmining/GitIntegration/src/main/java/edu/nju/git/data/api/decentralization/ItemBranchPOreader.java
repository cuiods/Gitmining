package edu.nju.git.data.api.decentralization;

import edu.nju.git.data.api.URLreader;
import edu.nju.git.data.api.service.ItemService;

public class ItemBranchPOreader implements ItemService{

	public ItemBranchPOreader() {
	}

	private String url_string;
	public ItemBranchPOreader(String fullname,String sha) {
		this.setNames(fullname,sha);
	}

	public void setNames(String fullname,String sha){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/branch/"+sha+"/item/";
	}
	public ItemBranchPOreader(String owner,String name,String sha) {
		this.setNames(owner+"/"+name,sha);
	}

	public void setNames(String owner,String name,String sha){
		this.setNames(owner, name, sha);
	}
	/**
	 * commit 对应提交的sha值 name 版本号 fn 项目全称
	 * 
	 * </p>
     * <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/branch/{name}/item/{item}
     * <br/>查询单个项目的某个版本的某项信息
     * <br/>item可接受的参数有：
     * <br/>commit 对应提交的sha值 
     * <br/>name 版本号 fn 项目全称
     * <br/>return:List<String>
     * </p>
	 */
	@Override
	public String getItem(String item) {
		String string = url_string+item;
		return URLreader.getInstance().reader(string);
	}

}
