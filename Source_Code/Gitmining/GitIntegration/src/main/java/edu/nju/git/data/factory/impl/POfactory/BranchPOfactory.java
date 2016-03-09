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
		// TODO Auto-generated method stub
		return null;
	}

}
