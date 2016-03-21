package org.GitIntegration.unit.api;

import java.util.List;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.init.impl.LocalReader;
import junit.framework.TestCase;

public class LocalReaderTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRead() {
		LocalReader reader = new LocalReader();
		List<RepoBriefPO> list = reader.readRepos();
		assertEquals(list.size(), 3216);
		
		List<UserBriefPO> users = reader.readUsers();
		assertEquals(2404, users.size());
	}

}
