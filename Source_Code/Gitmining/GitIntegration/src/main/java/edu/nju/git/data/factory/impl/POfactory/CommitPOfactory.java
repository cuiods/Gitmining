package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.CommitPO;
import edu.nju.git.data.factory.service.POfactory;

public class CommitPOfactory implements POfactory<CommitPO> {

	private AbstractFieldsGetter itemHelper;
	private String sha;
	
	public CommitPOfactory() {
	}
	public CommitPOfactory(AbstractFieldsGetter itemHelper,String sha) {
		super();
		this.itemHelper = itemHelper;
		this.sha = sha;
	}
    public void setFiledsGetter(AbstractFieldsGetter itemHelper,String sha) {
		this.itemHelper = itemHelper;
		this.sha = sha;
    }


    /**
	 * http://www.gitmining.net/api/repository/{owner}/{reponame}/commit/{sha}/item/{item}
     * 查询某个commit的某项信息
     * item可接受的参数有：
     * committer,committer_email,Date,message
     * filenumber 更改文件数,additions 添加代码行数,deletions 删除代码行数,total 总共影响行数
	 */
	@Override
	public CommitPO getPO() {
		CommitPO po = new CommitPO();
		
		po.setId(sha);
		
//		po.setContents(itemHelper.getString(""));
		po.setDate(itemHelper.getString("Date"));
//		po.setId(itemHelper.getString(""));
		po.setMessage(itemHelper.getString("message"));
		
		po.setNum_addition(itemHelper.getInteger("additions"));
		po.setNum_deletion(itemHelper.getInteger("deletions"));
		po.setNum_file(itemHelper.getInteger("filenumber"));
		po.setNum_total(itemHelper.getInteger("total"));
		
		po.setUserName(itemHelper.getString("committer"));
		
		
		return po;
	}

}
