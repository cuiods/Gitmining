package org.GitIntegration.driven;

import edu.nju.git.data.impl.RepoDataImpl;
import edu.nju.git.data.service.RepoDataService;
import junit.framework.TestCase;

public class RepoDriven extends TestCase {

	public RepoDataService repoDataService = RepoDataImpl.instance();
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*public void testGetRepoBasicInfo() {
		RepoVO repoPO = repoDataService.getRepoBasicInfo("vrpn", "vrpn");
		assertEquals( (repoPO.getNum_subscribers()), 34);
	}

	public void testGetRepoContributor() {
		List<UserBriefVO> userBriefVOs = repoDataService.getRepoContributor("vrpn", "vrpn");
		assertEquals(userBriefVOs.size(),19);
	}*/
/*
	public void testGetRepoCollaborator() {
		List<UserBriefVO> userBriefVOs = repoDataService.getRepoCollaborator("vrpn", "vrpn");
		for (UserBriefVO userBriefVO : userBriefVOs) {
			System.out.println(userBriefVO.getLogin());
		}
		assertEquals(userBriefVOs.size(),5);
	}
*/
	// some times it throw errores , but the erroes can be  catched
/*	public void testGetRepoBranch() {
		List<BranchVO> userBriefVOs = repoDataService.getRepoBranch("vrpn", "vrpn");
		for (BranchVO branchVO : userBriefVOs) {
			System.out.println(branchVO.getName());
		}
	}
*/
	/*public void testGetRepoFork() {
		List<RepoBriefVO> repoBriefVOs = repoDataService.getRepoFork("vrpn", "vrpn");
		for (RepoBriefVO repoBriefVO : repoBriefVOs) {
			System.out.println(repoBriefVO.getDescription());
		}
		System.out.println(repoBriefVOs.size());
	}*/

	
	/*public void testGetRepoCommit() {
		List<CommitVO> commitVOs = repoDataService.getRepoCommit("vrpn", "vrpn");
		for (CommitVO commitVO : commitVOs) {
			System.out.println(commitVO.getNum_file());
		}
		System.out.println(commitVOs.size());
	}*/

	/*
	public void testGetRepoIssue() {
		List<IssueVO> issueVOs = repoDataService.getRepoIssue("vrpn", "vrpn");
		System.out.println(issueVOs.size());
	}*/

}
