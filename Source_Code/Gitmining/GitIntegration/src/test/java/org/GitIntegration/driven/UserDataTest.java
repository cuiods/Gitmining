package org.GitIntegration.driven;

import edu.nju.git.data.impl.UserDataImpl;
import junit.framework.TestCase;

public class UserDataTest extends TestCase {

	public UserDataImpl userDataImpl = UserDataImpl.instance();
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInstance() {
		assert userDataImpl!=null;
	}

	public void testGetSearchResult() {

		assertEquals(2404,  userDataImpl.getTotalCount());
	}

	/*public void testGetUserInfo() {
		fail("Not yet implemented");
	}

	public void testGetUserOwnRepos() {
		fail("Not yet implemented");
	}

	public void testGetUserSubscribeRepos() {
		fail("Not yet implemented");
	}

	public void testGetUserCollaborateRepos() {
		fail("Not yet implemented");
	}

	public void testGetUserContributeRepos() {
		fail("Not yet implemented");
	}*/

}
