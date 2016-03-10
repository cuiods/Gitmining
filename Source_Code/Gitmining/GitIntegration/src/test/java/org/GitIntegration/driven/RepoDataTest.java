package org.GitIntegration.driven;

import java.util.List;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.data.impl.RepoDataImpl;
import edu.nju.git.exception.NoSearchResultException;
import junit.framework.TestCase;

public class RepoDataTest extends TestCase {

	public RepoDataImpl repoDataImpl = RepoDataImpl.instance();
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testGetSearchResult() {
		try {
			List<RepoBriefVO> list = repoDataImpl.getSearchResult("*");
			for (RepoBriefVO repoBriefVO : list) {
				System.out.println(repoBriefVO.getName());
			}
			System.out.println(list.size());
		} catch (NoSearchResultException e) {
			System.out.println("null");
			e.printStackTrace();
		}
	}

	/*public void testGetRepoBasicInfo() {
		fail("Not yet implemented");
	}

	public void testGetRepoContributor() {
		fail("Not yet implemented");
	}

	public void testGetRepoCollaborator() {
		fail("Not yet implemented");
	}

	public void testGetRepoBranch() {
		fail("Not yet implemented");
	}

	public void testGetRepoFork() {
		fail("Not yet implemented");
	}

	public void testGetRepoCommit() {
		fail("Not yet implemented");
	}

	public void testGetRepoIssue() {
		fail("Not yet implemented");
	}
*/
}
