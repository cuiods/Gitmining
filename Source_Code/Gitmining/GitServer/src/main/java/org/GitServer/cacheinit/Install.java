package org.GitServer.cacheinit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.RepoLoader;
import org.GitServer.cacheinit.loader.UserLoader;
import org.GitServer.cacheinit.loader.listfactory.CollaboratorsListReader;
import org.GitServer.cacheinit.loader.listfactory.ContributorsListReader;
import org.GitServer.cacheinit.loader.listfactory.RepositoriesListReader;
import org.GitServer.cacheinit.loader.listfactory.SubscribersListReader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserBriefPO;

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
	private List<String> repos;
	public Install() throws JsonGenerationException, JsonMappingException, IOException{
		repos = new RepositoriesListReader().getNames();
		dataEncapsulation.nameOrderRepoPOs = new RepoLoader(repos).getPos();
		dataEncapsulation.nameOrderUserPOs = new UserLoader(repos).getPos();
		initSubscribe();
		initCollabRepo();
		initContribute();
		
	}
	
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initSubscribe() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoPO repoBriefPO : dataEncapsulation.nameOrderRepoPOs) {
			
			List<String> subscriberslist = new SubscribersListReader(repoBriefPO.getOwnerName(),repoBriefPO.getName()).getNames();
			dataEncapsulation.repoToSubscriber.put(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName(), subscriberslist);
		    
		    for (String collaborator : subscriberslist) {
		    	if(dataEncapsulation.userToSubscribeRepo.containsKey(collaborator)){
		    		dataEncapsulation.userToSubscribeRepo.get(collaborator).add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
					dataEncapsulation.userToSubscribeRepo.put(collaborator, valueStringList);
			    }
			}
		    
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initCollabRepo() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoPO repoBriefPO : dataEncapsulation.nameOrderRepoPOs) {
			List<String> collablist = new CollaboratorsListReader(repoBriefPO.getOwnerName(),repoBriefPO.getName()).getNames();
		    
			dataEncapsulation.repoToCollab.put(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName(), collablist);
		    
		    for (String collaborator : collablist) {
		    	if(dataEncapsulation.userToCollabRepo.containsKey(collaborator)){
		    		dataEncapsulation.userToCollabRepo.get(collaborator).add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
					dataEncapsulation.userToCollabRepo.put(collaborator, valueStringList);
			    }
			}
		    
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initContribute() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoPO repoBriefPO : dataEncapsulation.nameOrderRepoPOs) {
			List<String> contrilist = new ContributorsListReader(repoBriefPO.getOwnerName(),repoBriefPO.getName()).getNames();
			dataEncapsulation.repoToContributor.put(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName(), contrilist);
		    
		    for (String contri : contrilist) {
		    	if(dataEncapsulation.userToContribute.containsKey(contri)){
		    		dataEncapsulation.userToContribute.get(contri).add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
					dataEncapsulation.userToContribute.put(contri, valueStringList);
			    }
			}
		    
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initUserToOwnerRepo() throws JsonGenerationException, JsonMappingException, IOException{
		
		for (RepoPO repoBriefPO : dataEncapsulation.nameOrderRepoPOs) {
			boolean isContain = dataEncapsulation.userToOwnerRepo.containsKey(repoBriefPO.getOwnerName());
			if(!isContain){
				List<String> reposnames = new ArrayList<String>();
				reposnames.add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
				dataEncapsulation.userToOwnerRepo.put(repoBriefPO.getOwnerName(), reposnames);
			}else{
				dataEncapsulation.userToOwnerRepo.get(repoBriefPO.getOwnerName()).add(repoBriefPO.getOwnerName()+"/"+repoBriefPO.getName());
			}
		}
	}
	


	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		new Install();
	}

}
