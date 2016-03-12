package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.VO.IssueVO;
import edu.nju.git.data.factory.impl.ListCreator;
import junit.framework.TestCase;

public class IssueTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testIssueReader(){
	}
	
	public void test2(){
		ListCreator listCreator = ListCreator.getInstance();
		List<IssueVO> issueVOs = listCreator.getIssueVO("vrpn", "vrpn");
		assertEquals(30, issueVOs.size());
	}
}
