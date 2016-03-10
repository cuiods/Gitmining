package org.GitIntegration.unit.api;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.centralization.UserMapReader;
import edu.nju.git.data.api.decentralization.UserItemReader;
import edu.nju.git.data.factory.impl.gitminingCreator.UserBriefPOfactory;
import junit.framework.TestCase;

public class UserReaderTest extends TestCase {

	private UserBriefPOfactory reader ;
	private UserBriefPOfactory reader2;
	protected void setUp() throws Exception {
		super.setUp();
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetUserBreifPO()
	{
		reader = new UserBriefPOfactory(new UserMapReader("huerlisi"));
		
		UserBriefPO po = reader.getPO();
		assertTrue(po!=null);
		int foller = po.getFollowers();
		assertEquals(foller, 47);
		assertEquals("huerlisi", po.getLogin());
		assertEquals(112, po.getPublic_repos());
		
		
		
	//	System.out.println(po.toString());
	}
	
	public void test2(){
		reader2 = new UserBriefPOfactory(new UserItemReader("huerlisi"));
		UserBriefPO po = reader2.getPO();
		assertTrue(po!=null);
		assertEquals(47, po.getFollowers());
		assertEquals("huerlisi", po.getLogin());
		assertEquals(112, po.getPublic_repos());
	}
}
