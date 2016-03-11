package edu.nju.git.data.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.JacksonConfig;

public class LoadData {

	private Map<String, RepoBriefPO>  nameToRepo ;
	private Map<String, UserBriefPO>  nameToUser ;
	private Map<String, List<String>> userToOwnerRepo ;
	
	private Map<String, List<String>> userToCollabRepo ;
	private Map<String, List<String>> userToContribute ;
	private Map<String, List<String>> userToSubscribeRepo ;
	
	
	private Map<String, List<String>> repoToContributor ;
	private Map<String, List<String>> repoToCollab ;
	
	private Map<String, List<String>> repoToSubscriber ;
	
	
	

	public Map<String, RepoBriefPO> getNameToRepo() {
		if(nameToRepo==null) this.setNameToRepo();
		return nameToRepo;
	}

	public Map<String, UserBriefPO> getNameToUser() {
		if(nameToUser==null) this.setNameToUser();
		return nameToUser;
	}

	public Map<String, List<String>> getUserToOwnerRepo() {
		if(userToOwnerRepo==null) this.setUserToOwnerRepo();
		return userToOwnerRepo;
	}

	public Map<String, List<String>> getUserToCollabRepo() {
		if(userToCollabRepo==null) this.setUserToCollabRepo();
		return userToCollabRepo;
	}

	public Map<String, List<String>> getUserToContribute() {
		if(userToContribute==null) this.setUserToContribute();
		return userToContribute;
	}

	public Map<String, List<String>> getUserToSubscribeRepo() {
		if(userToSubscribeRepo==null) this.setUserToSubscribeRepo();
		return userToSubscribeRepo;
	}

	public Map<String, List<String>> getRepoToContributor() {
		if(repoToContributor==null) this.setRepoToContributor();
		return repoToContributor;
	}

	public Map<String, List<String>> getRepoToCollab() {
		if(repoToCollab==null) this.setRepoToCollab();
		return repoToCollab;
	}


	public Map<String, List<String>> getRepoToSubscriber() {
		if(repoToSubscriber==null) this.setRepoToSubscriber();
		return repoToSubscriber;
	}


	@SuppressWarnings("unchecked")
	private synchronized void setNameToRepo()  {
		if(nameToRepo==null){
			try {
				this.nameToRepo = 
						JacksonConfig.getObjectMapper().readValue(new File("cache/map/nameToRepo.txt"), Map.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private synchronized void setNameToUser()  {
		if(this.nameToUser==null){
			try {
				this.nameToUser = 
						JacksonConfig.getObjectMapper().readValue(new File("cache/map/nameToUser.txt"), Map.class);
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}

	private synchronized void setUserToOwnerRepo()  {
		if(this.userToOwnerRepo==null){
			this.userToOwnerRepo = this.getMapString("cache/map/userToOwnerRepo.txt");
		}
	}

	private synchronized void setUserToCollabRepo()  {
		if(this.userToCollabRepo==null){
			userToCollabRepo = this.getMapString ("cache/map/userToCollabRepo.txt");
		}
	}
	private synchronized void setUserToContribute() {
		if(this.userToContribute==null){
			userToContribute = this.getMapString ("cache/map/userToContribute.txt");
		}
	}
	private synchronized void setUserToSubscribeRepo()  {
		if(this.userToSubscribeRepo==null){
			userToSubscribeRepo = this.getMapString ("cache/map/userToSubscribeRepo.txt");
		}
	}
	private synchronized void setRepoToContributor() {
		if(this.repoToContributor==null){
			repoToContributor = this.getMapString ("cache/map/repoToContributor.txt");
		}
	}
	private synchronized void setRepoToCollab(){
		if(this.repoToCollab==null){
			repoToCollab = this.getMapString ("cache/map/repoToCollab.txt");
		}
	}


	private synchronized void setRepoToSubscriber()  {
		if(this.repoToSubscriber==null){
			repoToSubscriber = this.getMapString("cache/map/repoToSubscriber.txt");
		}
	}


	@SuppressWarnings("unchecked")
	private Map<String, List<String>> getMapString(String path){
		try {
			return JacksonConfig.getObjectMapper().readValue(new File(path), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashMap<String ,List<String>>();
	}
	
	
}
