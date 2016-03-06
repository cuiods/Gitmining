package org.GitIntegration.unit.api;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.UserReader;
import junit.framework.TestCase;

public class UserReaderTest extends TestCase {

	private UserReader reader ;
	
	protected void setUp() throws Exception {
		super.setUp();
		reader = new UserReader("huerlisi");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUserBreifPO()
	{
		UserBriefPO po = reader.getBriefPO();
		assertTrue(po!=null);
	//	System.out.println(po.toString());
	}
}
