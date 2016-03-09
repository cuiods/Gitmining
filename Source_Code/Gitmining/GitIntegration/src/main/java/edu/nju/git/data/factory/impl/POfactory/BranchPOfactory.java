package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.BranchPO;
import edu.nju.git.data.factory.service.POfactory;

public class BranchPOfactory implements POfactory<BranchPO>{

	private AbstractFieldsGetter itemHelper;
	

	public BranchPOfactory(AbstractFieldsGetter itemHelper) {
		super();
		this.itemHelper = itemHelper;
	}


	@Override
	public BranchPO getPO() {
		BranchPO po = new BranchPO();
		po.setId(itemHelper.getString("name"));
		po.setName(itemHelper.getString("fn"));
		po.setSHA(itemHelper.getString("commit"));
		return po;
	}

}
