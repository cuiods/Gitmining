package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.CommitPO;
import edu.nju.git.data.factory.service.POfactory;

public class CommitPOfactory implements POfactory<CommitPO> {

	private AbstractFieldsGetter itemHelper;
	public CommitPOfactory() {
	}
	public CommitPOfactory(AbstractFieldsGetter itemHelper) {
		super();
		this.itemHelper = itemHelper;
	}
    public void setFiledsGetter(AbstractFieldsGetter itemHelper) {
		this.itemHelper = itemHelper;
    }


	@Override
	public CommitPO getPO() {
		// TODO Auto-generated method stub
		return null;
	}

}
