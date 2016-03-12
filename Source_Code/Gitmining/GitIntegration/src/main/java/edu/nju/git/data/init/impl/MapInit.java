package edu.nju.git.data.init.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.JacksonConfig;
import edu.nju.git.data.api.liststring.CollaboratorsListReader;
import edu.nju.git.data.api.liststring.ContributorsListReader;
import edu.nju.git.data.api.liststring.SubscribersListReader;

public class MapInit {

	private Map<String, RepoBriefPO>  nameToRepo = new HashMap<String,RepoBriefPO>();
	private Map<String, UserBriefPO>  nameToUser = new HashMap<String, UserBriefPO>();
	private Map<String, List<String>> userToOwnerRepo = new HashMap<String, List<String>>();
	
	private Map<String, List<String>> userToCollabRepo = new HashMap<String, List<String>>();
	private Map<String, List<String>> userToContribute = new HashMap<String, List<String>>();
	private Map<String, List<String>> userToSubscribeRepo = new HashMap<String, List<String>>();
	
	
	private Map<String, List<String>> repoToContributor = new HashMap<String, List<String>>();
	private Map<String, List<String>> repoToCollab = new HashMap<String, List<String>>();
	
	private List<RepoBriefPO> repoBriefPOs;
	private List<UserBriefPO> userBriefPOs;
	private Map<String, List<String>> repoToSubscriber = new HashMap<String, List<String>>();
	
	public MapInit() throws JsonGenerationException, JsonMappingException, IOException {
		init();
	}
	
	/*private void init7() throws JsonGenerationException, JsonMappingException, IOException{
		
		this.save("cache/map/userToOwnerRepo.txt", userToOwnerRepo);
		this.save("cache/map/userToCollabRepo.txt", userToCollabRepo);
		this.save("cache/map/userToContribute.txt", userToContribute);
		this.save("cache/map/userToSubscribeRepo.txt", userToSubscribeRepo);
		this.save("cache/map/repoToContributor.txt", repoToContributor);
		this.save("cache/map/repoToCollab.txt", repoToCollab);
		this.save("cache/map/repoToSubscriber.txt", repoToSubscriber);
		
		JacksonConfig.getObjectMapper().writeValue(new File("cache/map/.txt"), nameToRepo);
		JacksonConfig.getObjectMapper().writeValue(new File("cache/map/.txt"), nameToUser);
	}*/
	
	private void save(String path,Map<String, List<String>> map) throws JsonGenerationException, JsonMappingException, IOException {
        JacksonConfig.getObjectMapper().writeValue(new File(path), map);
	}
	
	public void initSubscribe() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoBriefPO repoBriefPO : repoBriefPOs) {
			List<Object> subscribers = new SubscribersListReader(repoBriefPO.getOwner(),repoBriefPO.getName()).getNames();
		    List<String> subscriberslist = this.covertToString(subscribers);
		    
		    repoToSubscriber.put(repoBriefPO.getOwner()+"/"+repoBriefPO.getName(), subscriberslist);
		    
		    for (String collaborator : subscriberslist) {
		    	if(userToSubscribeRepo.containsKey(collaborator)){
		    		userToSubscribeRepo.get(collaborator).add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
					userToSubscribeRepo.put(collaborator, valueStringList);
			    }
			}
		    
		}
		this.save("cache/map/repoToSubscriber.txt", repoToSubscriber);
		this.save("cache/map/userToSubscribeRepo.txt", userToSubscribeRepo);
	}
	
	public void initCollabRepo() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoBriefPO repoBriefPO : repoBriefPOs) {
			List<Object> collaborators = new CollaboratorsListReader(repoBriefPO.getOwner(),repoBriefPO.getName()).getNames();
		    List<String> collablist = this.covertToString(collaborators);
		    
		    repoToCollab.put(repoBriefPO.getOwner()+"/"+repoBriefPO.getName(), collablist);
		    
		    for (String collaborator : collablist) {
		    	if(userToCollabRepo.containsKey(collaborator)){
		    		userToCollabRepo.get(collaborator).add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
					userToCollabRepo.put(collaborator, valueStringList);
			    }
			}
		    
		}
		this.save("cache/map/userToCollabRepo.txt", userToCollabRepo);
		this.save("cache/map/repoToCollab.txt", repoToCollab);
	}
	
	public void initContribute() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoBriefPO repoBriefPO : repoBriefPOs) {
			List<Object> contributors = new ContributorsListReader(repoBriefPO.getOwner(),repoBriefPO.getName()).getNames();
		    List<String> contrilist = this.covertToString(contributors);
		    
		    repoToContributor.put(repoBriefPO.getOwner()+"/"+repoBriefPO.getName(), contrilist);
		    
		    for (String contri : contrilist) {
		    	if(userToContribute.containsKey(contri)){
			    	userToContribute.get(contri).add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
			    	userToContribute.put(contri, valueStringList);
			    }
			}
		    
		}
		this.save("cache/map/repoToContributor.txt", repoToContributor);
		this.save("cache/map/userToContribute.txt", userToContribute);
	}
	public void initUserToOwnerRepo() throws JsonGenerationException, JsonMappingException, IOException{
		
		for (RepoBriefPO repoBriefPO : repoBriefPOs) {
			boolean isContain = userToOwnerRepo.containsKey(repoBriefPO.getOwner());
			if(!isContain){
				List<String> reposnames = new ArrayList<String>();
				reposnames.add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
				userToOwnerRepo.put(repoBriefPO.getOwner(), reposnames);
			}else{
				userToOwnerRepo.get(repoBriefPO.getOwner()).add(repoBriefPO.getOwner()+"/"+repoBriefPO.getName());
			}
		}
		this.save("cache/map/userToOwnerRepo.txt", userToOwnerRepo);
		//userToOwnerRepo = null;
	}
	private void init(){
		LocalReader reader = new LocalReader();
		repoBriefPOs = reader.readRepos();
		userBriefPOs = reader.readUsers();
	}
	
	public void initNameToUser() throws JsonGenerationException, JsonMappingException, IOException{
		for (RepoBriefPO repoBriefPO : repoBriefPOs) {
			nameToRepo.put(repoBriefPO.getOwner()+"/"+repoBriefPO.getName(), repoBriefPO);
		}
		File aFile =  new File("cache/map/nameToRepo.txt");
		if(!aFile.exists()){aFile.createNewFile();}
		JacksonConfig.getObjectMapper().writeValue(aFile, nameToRepo);
		//nameToRepo = null;
		
		
		for (UserBriefPO userBriefPO : userBriefPOs) {
			nameToUser.put(userBriefPO.getLogin(), userBriefPO);
		}
		File bFile = new File("cache/map/nameToUser.txt");
		if(!bFile.exists()){aFile.createNewFile();}
		JacksonConfig.getObjectMapper().writeValue(bFile, nameToUser);
		//nameToUser = null;
	}
	
	public List<String> covertToString(List<Object> objects){
		List<String> list = new ArrayList<String>();
		for (Object object : objects) {
			list.add(object.toString());
		}
		return list;
	}


	
	public static void main(String[] args){
		try {
			MapInit init = new MapInit();
			//easy
			init.initUserToOwnerRepo();
//			init.initNameToUser();
//			init.initUserToOwnerRepo();
//			
//			//will cost mush time
//			init.initCollabRepo();
//			init.initContribute();
//			init.initSubscribe();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
