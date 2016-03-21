package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.data.factory.impl.BriefCreator;
import junit.framework.TestCase;

public class GetUserOwnerRepo extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test(){
		BriefCreator briefCreator = BriefCreator.getInstance();
		List<RepoBriefVO> repoBriefVOs = briefCreator.getUserOwnRepos("3b");
		assert(repoBriefVOs.size()>0);
		
	}
}
