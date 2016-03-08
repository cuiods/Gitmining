package edu.nju.git.data.api.decentralization;

import edu.nju.git.data.api.URLreader;
import edu.nju.git.data.api.service.ItemService;

public class ItemCommitPOReader implements ItemService{

	public ItemCommitPOReader() {
		// TODO Auto-generated constructor stub
	}

	private String url_string;
	public ItemCommitPOReader(String fullname,String sha) {
		this.setNames(fullname,sha);
	}

	public void setNames(String fullname,String sha){
		this.url_string  = "http://www.gitmining.net/api/repository/"+fullname+"/commit/"+sha+"/item/";
	}
	public ItemCommitPOReader(String owner,String name,String sha) {
		this.setNames(owner+"/"+name,sha);
	}

	public void setNames(String owner,String name,String sha){
		this.setNames(owner, name, sha);
	}
	/**
	 * http://www.gitmining.net/api/repository/{owner}/{reponame}/commit/{sha}/item/{item}
     * 查询某个commit的某项信息
     * item可接受的参数有：
     * committer,committer_email,Date,message
     * filenumber 更改文件数,additions 添加代码行数,deletions 删除代码行数,total 总共影响行数
     * 
	 */
	@Override
	public String getItem(String item) {
		String string = url_string+item;
		return URLreader.getInstance().reader(string);
	}

}
