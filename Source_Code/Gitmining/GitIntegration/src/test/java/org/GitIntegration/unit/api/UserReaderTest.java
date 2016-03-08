package org.GitIntegration.unit.api;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.factory.impl.MapPO.MapUserBriefPOfactory;
import junit.framework.TestCase;

public class UserReaderTest extends TestCase {

	private MapUserBriefPOfactory reader ;
	
	protected void setUp() throws Exception {
		super.setUp();
		reader = new MapUserBriefPOfactory("huerlisi");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUserBreifPO()
	{
		UserBriefPO po = reader.getPO();
		assertTrue(po!=null);
	//	System.out.println(po.toString());
	}
}
