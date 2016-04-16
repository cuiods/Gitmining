package org.GitClient.driven;

import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.rmi.RMIClientLauncher;
import junit.framework.TestCase;

public class UserBLTest extends TestCase {

	private  UserBlService userBlService ;
	
	protected void setUp() throws Exception {
		super.setUp();
		RMIClientLauncher.initRMI();
		userBlService = BlFactory.instance().getUserBlService();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testInstance() {
		assertEquals(userBlService, UserBlImpl.instance());
	}

	public void testGetSearchResult() {
		assertTrue ( userBlService.getSearchResult("r").size()>0);
	}

	
	
	public void testGetNONEUserInfo() {
		assertTrue ( userBlService.getUserInfo("1545")==null);
	}

	public void testGetUserInfo(){
		assertTrue(userBlService.getUserInfo("550").getPublic_gists()>0);
	}
	
	public void testGetNONEUserOwnRepos() {
		assertTrue ( userBlService.getUserOwnRepos("2543")==null);
	}
	
	public void testGetUserOwnRepos(){
		assertTrue(!userBlService.getUserOwnRepos("550").isEmpty());
	}

	
	public void testGetNONEUserSubscribeRepos() {
		assertTrue ( userBlService.getUserSubscribeRepos("6456")==null);
	}
	public void testGetUserSubscribeRepos(){
		assertTrue(!userBlService.getUserSubscribeRepos("550").isEmpty());
	}
	

	public void testGetNONEUserCollaborateRepos() {
		assertTrue (userBlService.getUserCollaborateRepos("mojombo")!=null);
	}
	
	public void testGetUserCollaborateRepos(){
		assertTrue(!userBlService.getUserCollaborateRepos("550").isEmpty());
	}
	
	public void testGetNONEUserContributeRepos() {
		assertTrue( userBlService.getUserContributeRepos("mojombo")!=null);
	}

	public void testGetUserContributeRepos() {
		assertTrue( userBlService.getUserContributeRepos("550")!=null);
	}
	
}
