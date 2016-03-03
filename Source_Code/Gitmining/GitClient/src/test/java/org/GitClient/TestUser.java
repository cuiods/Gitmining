package org.GitClient;

import edu.nju.git.bl.service.UserBlService;
import junit.framework.TestCase;

public class TestUser extends TestCase {

	private UserBlService userBlService;
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetSearchResult() {
		//this.userBlService.getSearchResult("");
	}
	
	public void testGetUserInfo() {
		this.userBlService.getUserInfo("");
	}

}
