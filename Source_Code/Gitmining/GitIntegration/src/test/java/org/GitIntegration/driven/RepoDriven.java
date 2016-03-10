package org.GitIntegration.driven;

import java.util.List;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
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

	public void testGetRepoBasicInfo() {
		RepoVO repoPO = repoDataService.getRepoBasicInfo("vrpn", "vrpn");
		System.out.println(repoPO.getSize());
		//assertEquals( (repoPO.getNum_subscribers()), 34);
	}

	public void testGetRepoContributor() {
		List<UserBriefVO> userBriefVOs = repoDataService.getRepoContributor("vrpn", "vrpn");
		System.out.println(userBriefVOs.size());
		//assertEquals(userBriefVOs.size(),19);
	}

	public void testGetRepoCollaborator() {
		List<UserBriefVO> userBriefVOs = repoDataService.getRepoCollaborator("vrpn", "vrpn");
		System.out.println(userBriefVOs.size());
		//assertEquals(userBriefVOs.size(),5);
	}

	// some times it throw errores , but the erroes can be  catched
	public void testGetRepoBranch() {
		List<BranchVO> userBriefVOs = repoDataService.getRepoBranch("vrpn", "vrpn");
		System.out.println(userBriefVOs.size());
		//assertEquals(13, userBriefVOs.size());
	}

	public void testGetRepoFork() {
		List<RepoBriefVO> repoBriefVOs = repoDataService.getRepoFork("vrpn", "vrpn");
//		for (RepoBriefVO repoBriefVO : repoBriefVOs) {
//			System.out.println(repoBriefVO.getDescription());
//		}
		System.out.println(repoBriefVOs.size());
	}

	
	public void testGetRepoCommit() {
		List<CommitVO> commitVOs = repoDataService.getRepoCommit("vrpn", "vrpn");
		System.out.println(commitVOs.size());
		//assertEquals(30, commitVOs.size());
	}

	
	public void testGetRepoIssue() {
		List<IssueVO> issueVOs = repoDataService.getRepoIssue("vrpn", "vrpn");
		System.out.println(issueVOs.size());
		//assertEquals(30, issueVOs.size());
	}

}
