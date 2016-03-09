package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.data.api.decentralization.BranchItemreader;
import edu.nju.git.data.api.liststring.BranchesListReader;
import edu.nju.git.data.factory.impl.ListCreator;
import edu.nju.git.data.factory.impl.POfactory.BranchPOfactory;
import junit.framework.TestCase;

public class BranchTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void test1(){
		String fullname = "vrpn/vrpn";
		List<Object> branches = new BranchesListReader(fullname).getNames();
		for (Object string : branches) {
			BranchPOfactory branchPOfactory = new BranchPOfactory(new BranchItemreader(fullname, string.toString()));
			BranchVO po = branchPOfactory.getPO();
			System.out.println("string:"+string);
			System.out.println("sha:   "+po.getSHA());
		}
		
	}

	public void test2(){
		ListCreator creator = new ListCreator();
		List<BranchVO> branchVOs = creator.getBranches("vrpn", "vrpn");
		for (BranchVO branchVO : branchVOs) {
			System.out.println(branchVO.getSHA());
			System.out.println(branchVO.getId());
			System.out.println(branchVO.getName());
		}
		System.out.println(branchVOs.size());
	}
}
