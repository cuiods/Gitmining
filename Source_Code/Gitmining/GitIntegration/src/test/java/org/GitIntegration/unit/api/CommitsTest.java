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
		ListCreator creator =  ListCreator.getInstance();
		List<CommitVO> branchVOs = creator.getCommitVO("vrpn", "vrpn");
		assertEquals(30, (branchVOs.size()));
	}
}
