package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.BranchPO;
import edu.nju.git.data.factory.impl.MapPO.MapBranchPOfactory;
import edu.nju.git.data.factory.service.POfactory;

public class BranchPOfactory implements POfactory<BranchPO>{

	private AbstractPOfactory itemHelper;
	public BranchPOfactory(String owner, String name) {
		itemHelper = new MapBranchPOfactory();
	}

	@Override
	public BranchPO getPO() {
		// TODO Auto-generated method stub
		return null;
	}

}
