package org.GitServer.cacheinit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.GitServer.cacheinit.loader.CommitLoader;
import org.GitServer.cacheinit.loader.IssueLoader;
import org.GitServer.cacheinit.loader.PullsLoader;
import org.GitServer.cacheinit.loader.ReposLoader;
import org.GitServer.cacheinit.loader.UsersLoader;
import org.GitServer.cacheinit.loader.listfactory.CollaboratorsListReader;
import org.GitServer.cacheinit.loader.listfactory.ContributorsListReader;
import org.GitServer.cacheinit.loader.listfactory.RepositoriesListReader;
import org.GitServer.cacheinit.loader.listfactory.SubscribersListReader;
import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;
import org.GitServer.cacheinit.writer.Saver;

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

	private DataEncapsulation dataEncapsulation = new DataEncapsulation();
	private Saver saver;
//	public static Set<String> errorCommitsReponame ;
	
	public Install(String rootpath) throws 
         JsonGenerationException, JsonMappingException, 
         IOException, NoSuchFieldException, SecurityException{
		
		init();
		saver = new Saver(dataEncapsulation, rootpath);
		
		List<String>  repos = new RepositoriesListReader().getNames();
		System.out.println("done with reading "+repos.size()+"repos' names");
		downloadUsers(repos);
//		downloadRepos(repos);
//		this.downloadReposAndUsers();
//		readReposAndUsers();
//		System.out.println("done with repos and users");
		
//		initUserToOwnerRepo();
//		System.out.println("done with initUserToOwnerRepo");
//		saver.excute(dataEncapsulation.getClass().getField("userToOwnerRepo"));
//		System.out.println("done with saving initUserToOwnerRepo");
		
		
//		initSubscribe();
//		System.out.println("done with initSubscribe");
//		saver.excute(dataEncapsulation.getClass().getField("userToSubscribeRepo"));
//		saver.excute(dataEncapsulation.getClass().getField("repoToSubscriber"));
//		System.out.println("done with saving last loading object.");
		
		
//		initCollabRepo();
//		System.out.println("done with initCollabRepo");
//		saver.excute(dataEncapsulation.getClass().getField("userToCollabRepo"));
//		saver.excute(dataEncapsulation.getClass().getField("repoToCollab"));
//		System.out.println("done with saving last loading object.");
		
//		initContribute();
//		System.out.println("done with initContribute");
//		saver.excute(dataEncapsulation.getClass().getField("userToContribute"));
//		saver.excute(dataEncapsulation.getClass().getField("repoToContributor"));
//		System.out.println("done with saving last loading object.");
		
//		initPull();
//		System.out.println("done with initPull");
//		saver.excute(dataEncapsulation.getClass().getField("repoToPull"));
//		System.out.println("done with saving last loading object.");
		
//		initIssue();
//		System.out.println("done with initIssue");
//		saver.excute(dataEncapsulation.getClass().getField("repoToIssue"));
//		System.out.println("done with saving last loading object.");
		
//		errorCommitsReponame = new HashSet<String>(50);
//		initCommit();
//		System.out.println("done with initCommit");
//		saver.excute(dataEncapsulation.getClass().getField("repoToCommit"));
//		System.out.println("done with saving last loading object.");
//		saver.excute("error/error.txt", errorCommitsReponame);
		
		
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs  must be set
	 */
	private void initCommit()throws JsonGenerationException, JsonMappingException, IOException{
		AbstractPullIssueCommitLoader loader = new CommitLoader();
		int i = 1;
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			String fullname  = repoPO.getOwnerName()+"/"+repoPO.getName();
			loader.setName(fullname);
			dataEncapsulation.repoToCommit.put(fullname, loader.read());
			System.out.println("done with reading out commits of : "+fullname+" " + (i++));
		}
		
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs a must be set
	 */
	private void initIssue()throws JsonGenerationException, JsonMappingException, IOException{
		AbstractPullIssueCommitLoader loader = new IssueLoader();
		int i = 1;
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			String fullname  = repoPO.getOwnerName()+"/"+repoPO.getName();
			loader.setName(fullname);
			dataEncapsulation.repoToIssue.put(fullname, loader.read());
			System.out.println("done with reading out issues of : "+fullname+" " + (i++));
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs  must be set
	 */
	private void initPull()throws JsonGenerationException, JsonMappingException, IOException{
		AbstractPullIssueCommitLoader loader = new PullsLoader();
		int i = 1;
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			String fullname  = repoPO.getOwnerName()+"/"+repoPO.getName();
			loader.setName(fullname);
			dataEncapsulation.repoToPull.put(fullname, loader.read());
			System.out.println("done with reading out pulls of : "+fullname+" " + (i++));
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initSubscribe() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			
			String fullname = repoPO.getOwnerName()+"/"+repoPO.getName();
			
			List<String> subscriberslist = new 
					SubscribersListReader(repoPO.getOwnerName(),repoPO.getName()).getNames();
			dataEncapsulation.repoToSubscriber.put(fullname, subscriberslist);
		    System.out.println("done with reading out repo's sbscribers name of repo:"+fullname);
		    for (String collaborator : subscriberslist) {
		    	if(dataEncapsulation.userToSubscribeRepo.containsKey(collaborator)){
		    		dataEncapsulation.userToSubscribeRepo.get(collaborator).add(fullname);
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoPO.getOwnerName()+"/"+repoPO.getName());
					dataEncapsulation.userToSubscribeRepo.put(collaborator, valueStringList);
			    }
			}
		    
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initCollabRepo() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			List<String> collablist = new CollaboratorsListReader(repoPO.getOwnerName(),repoPO.getName()).getNames();
		    
			dataEncapsulation.repoToCollab.put(repoPO.getOwnerName()+"/"+repoPO.getName(), collablist);
		    
		    for (String collaborator : collablist) {
		    	if(dataEncapsulation.userToCollabRepo.containsKey(collaborator)){
		    		dataEncapsulation.userToCollabRepo.get(collaborator).add(repoPO.getOwnerName()+"/"+repoPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoPO.getOwnerName()+"/"+repoPO.getName());
					dataEncapsulation.userToCollabRepo.put(collaborator, valueStringList);
			    }
			}
		    
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initContribute() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			List<String> contrilist = new ContributorsListReader(repoPO.getOwnerName(),repoPO.getName()).getNames();
			dataEncapsulation.repoToContributor.put(repoPO.getOwnerName()+"/"+repoPO.getName(), contrilist);
		    
		    for (String contri : contrilist) {
		    	if(dataEncapsulation.userToContribute.containsKey(contri)){
		    		dataEncapsulation.userToContribute.get(contri).add(repoPO.getOwnerName()+"/"+repoPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoPO.getOwnerName()+"/"+repoPO.getName());
					dataEncapsulation.userToContribute.put(contri, valueStringList);
			    }
			}
		    
		}
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initUserToOwnerRepo() throws JsonGenerationException, JsonMappingException, IOException{
		
		for (RepoPO repoPO : dataEncapsulation.nameOrderRepoPOs) {
			boolean isContain = dataEncapsulation.userToOwnerRepo.containsKey(repoPO.getOwnerName());
			if(!isContain){
				List<String> reposnames = new ArrayList<String>();
				reposnames.add(repoPO.getOwnerName()+"/"+repoPO.getName());
				dataEncapsulation.userToOwnerRepo.put(repoPO.getOwnerName(), reposnames);
			}else{
				dataEncapsulation.userToOwnerRepo.get(repoPO.getOwnerName()).add(repoPO.getOwnerName()+"/"+repoPO.getName());
			}
		}
	}
	

	
	
	private void downloadRepos(List<String>  repos ) throws NoSuchFieldException, SecurityException{
		dataEncapsulation.nameOrderRepoPOs = new ReposLoader(repos).getPos();
		System.out.println("done with reading repos");
		saver.excute(dataEncapsulation.getClass().getField("nameOrderRepoPOs"));
		System.out.println("done with saving last loading object.");
	}
	
	private void downloadUsers(List<String>  repos ) throws NoSuchFieldException, SecurityException{
		dataEncapsulation.nameOrderUserPOs = new UsersLoader(repos).getPos();
		System.out.println("done whith reading users");
		saver.excute(dataEncapsulation.getClass().getField("nameOrderUserPOs"));
		System.out.println("done with saving last loading object.");
	}
	/**
	 * 
	 */
	private void readReposAndUsers(){
		try {
			this.read(dataEncapsulation.getClass().getField("nameOrderRepoPOs"));
			this.read(dataEncapsulation.getClass().getField( "nameOrderUserPOs" ));
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	

	private void read(Field field){
		String path = "cache/"+field.getName()+".txt";  //file path: "cache/nameOrderRepoPOs.txt"
		
		try {
			ObjectInputStream readerStream 
				= new ObjectInputStream(new FileInputStream(new File(path)));
		    field.set(dataEncapsulation, readerStream.readObject());
			readerStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private final int REPO_HASHSIZE = (int)(3300/0.75);
	private final int USER_HASHSIZE = (int)(3000/0.75);
	private void init(){
//		dataEncapsulation.userToOwnerRepo = new HashMap<String, List<String>>(USER_HASHSIZE);
//		dataEncapsulation.userToCollabRepo = new HashMap<String, List<String>>(USER_HASHSIZE);
//		dataEncapsulation.userToContribute = new HashMap<String, List<String>>(USER_HASHSIZE);
//		dataEncapsulation.userToSubscribeRepo = new HashMap<String, List<String>>(USER_HASHSIZE);
//		
//		
//		dataEncapsulation.repoToContributor = new HashMap<String, List<String>>(REPO_HASHSIZE);
//		dataEncapsulation.repoToCollab = new HashMap<String, List<String>>(REPO_HASHSIZE);
//		dataEncapsulation.repoToSubscriber = new HashMap<String, List<String>>(REPO_HASHSIZE);

		dataEncapsulation.repoToCommit = new HashMap<String, List<String>>(REPO_HASHSIZE);
		dataEncapsulation.repoToIssue = new HashMap<String, List<String>>(REPO_HASHSIZE);
		dataEncapsulation.repoToPull = new HashMap<String, List<String>>(REPO_HASHSIZE);
	}

	public static void main(String[] args)
			throws JsonGenerationException, JsonMappingException, IOException,
			NoSuchFieldException, SecurityException {
		Install install = new Install("cache");
	}

}
