package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.IssuePO;
import edu.nju.git.data.factory.service.POfactory;

public class IssuePOfactory implements POfactory<IssuePO> {

	private AbstractFieldsGetter itemHelper;
	private String sha;
	

	public IssuePOfactory(AbstractFieldsGetter itemHelper, String sha) {
		super();
		this.itemHelper = itemHelper;
		this.sha = sha;
	}


	/**
	 *  <p>http://www.gitmining.net/api/repository/{owner}/{reponame}/issue/{number}/item/{item}
     * 查询某个Issue的某项信息
     * item可接受的参数有：
     * state,locked,title,body
     * user,user_id,user_type
     * created_at,updates_at,closed_at,merged_at
     * </p>
	 */
	@Override
	public IssuePO getPO() {
		IssuePO po = new IssuePO();
		po.setId(sha);
		po.setState(itemHelper.getString("state"));
		po.setLocked(itemHelper.getBoolean("locked"));
		po.setTitle(itemHelper.getString("title"));
		po.setBody(itemHelper.getString("body"));
		po.setUserName(itemHelper.getString("user"));
		po.setCreate_at(itemHelper.getString("created_at"));
		po.setUpdate_at(itemHelper.getString("updates_at"));
		po.setMerged_at(itemHelper.getString("merged_at"));
		po.setClosed_at(itemHelper.getString("closed_at"));
		return po;
	}

}
