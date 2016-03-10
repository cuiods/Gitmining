package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.VO.CommitVO;
import edu.nju.git.data.factory.impl.ListCreator;
import junit.framework.TestCase;

public class CommitsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test1(){
		ListCreator creator = new ListCreator();
		List<CommitVO> branchVOs = creator.getCommitVO("vrpn", "vrpn");
		for (CommitVO branchVO : branchVOs) {
			System.out.println(branchVO.getNum_addition());
			System.out.println(branchVO.getUserName());
			System.out.println(branchVO.getId());
			System.out.println(branchVO.getDate());
			System.out.println(branchVO.getMessage());
			System.out.println(branchVO.getNum_deletion());
			
			System.out.println(branchVO.getNum_file());
			System.out.println(branchVO.getNum_total());
			System.out.println(branchVO.getComments());
		}
		System.out.println(branchVOs.size());
	}
}
