package org.GitServer.cacheinit;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.nju.git.PO.CommitPO;
import edu.nju.git.PO.IssuePO;
import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;

/**
 * the mesage should be installed as follows:<br/>
 * 
 * <br/>statConllabrators
 * <br/>statContributors 		=> map => done
 * <br/>statSubscriber
 * <br/>statUserOwnRepo 
 * <br/>
 * <br/>statLanguage
 * <br/>statCreateTime   		=> RepoVO => commit and issue and pull
 * <br/>statSize
 * <br/>
 * <br/>statForks
 * <br/>statStars
 * <br/>
 * <br/>statUserType
 * <br/>statUserBlog
 * <br/>statUserLocation  		=> UserVO  => map
 * <br/>statUserEmail
 * <br/>statUserCreateTime
 * <br/>
 * <br/>statUserGist
 * <br/>statUserFolllowers
 * 
 * <br/>statOrganizationUser 	=> RepoVO,UserVO
 * <br/>statOrganizationRepo
 * 
 * <br/>statCompanyUser  		=> All company
 * @author daixinyan
 * @date 2016-03-22
 */
@SuppressWarnings("unused")
public class Install {

	/**
	 * 1. read out map, and breifvos
	 */

	private DataEncapsulation dataEncapsulation;
	
	
	public Install(){
		readPOS();
		readMaps();
		
		downloadCommit();
		downloadIssue();
		downloadPull();
	}
	
	private void downloadPull() {
		// TODO Auto-generated method stub
		
	}

	private void downloadIssue() {
		// TODO Auto-generated method stub
		
	}

	private void downloadCommit() {
		// TODO Auto-generated method stub
		
	}

	private void readPOS() {
	}

	private void readMaps() {
	}

	public static void main(String[] args) {
		new Install();
	}

}
