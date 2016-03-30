package org.GitServer.cacheinit.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.GitServer.cacheinit.DataEncapsulation;
import org.GitServer.cacheinit.writer.Saver;
import org.GitServer.dataread.Reader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
/**
 * this class is for correct the user,s contribute\conllabrator and subscriber
 * @author daixinyan
 * @date 2016-03-30
 */
public class GenerateUserToMap {

	public static void main(String[] args){
		new GenerateUserToMap();
	}
	
	private DataEncapsulation dataEncapsulation ;
	private Saver saver;
	public GenerateUserToMap(){
		dataEncapsulation = new Reader().excute();
		saver = new Saver(dataEncapsulation, "cache");
		System.out.println("read out");
		
		try {
			initCollabRepo();
			System.out.println("init user to collabarotor");
	    	initContribute();
	    	System.out.println("init user to contribute");
			initSubscribe();
			System.out.println("init user to subscriber");
			
			saver.excute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initSubscribe() throws JsonGenerationException, JsonMappingException, IOException{
		Set<String> sets = dataEncapsulation.repoToSubscriber.keySet();
		printsize(dataEncapsulation.userToSubscribeRepo);
		dataEncapsulation.userToSubscribeRepo = new HashMap<String,List<String>>();
		for (String reponame : sets) {
			List<String> subscriberslist = dataEncapsulation.repoToSubscriber.get(reponame);
			for (String subscriber : subscriberslist) {
				if(dataEncapsulation.userToSubscribeRepo.containsKey(subscriber)){
		    		dataEncapsulation.userToSubscribeRepo.get(subscriber).add(reponame);
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(reponame);
					dataEncapsulation.userToSubscribeRepo.put(subscriber, valueStringList);
			    }
			}
		}
		printsize(dataEncapsulation.userToSubscribeRepo);
	}
	
	private void printsize(Map<String, List<String>> map){
		Set<String> sets = map.keySet();
		int count = 0;
		for (String string : sets) {
			List<String> itemlist = map.get(string);
			if(itemlist!=null){
				count+=itemlist.size();
			}
		}
		System.out.println(count);
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initCollabRepo() throws JsonGenerationException, JsonMappingException, IOException{
		Set<String> sets = dataEncapsulation.repoToCollab.keySet();
		printsize(dataEncapsulation.userToCollabRepo);
		dataEncapsulation.userToCollabRepo = new HashMap<String,List<String>>();
		for (String reponame : sets) {
			List<String> subscriberslist = dataEncapsulation.repoToCollab.get(reponame);
			for (String subscriber : subscriberslist) {
				if(dataEncapsulation.userToCollabRepo.containsKey(subscriber)){
		    		dataEncapsulation.userToCollabRepo.get(subscriber).add(reponame);
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(reponame);
					dataEncapsulation.userToCollabRepo.put(subscriber, valueStringList);
			    }
			}
		}
		printsize(dataEncapsulation.userToCollabRepo);
	}
	/**
	 * <br/><b>precondition</b>：dataEncapsulation.nameOrderRepoPOs and nameOrderUserPOs must be set
	 */
	private void initContribute() throws JsonGenerationException, JsonMappingException, IOException{
		Set<String> sets = dataEncapsulation.repoToContributor.keySet();
		printsize(dataEncapsulation.userToContribute);
		dataEncapsulation.userToContribute = new HashMap<String,List<String>>();
		for (String reponame : sets) {
			List<String> subscriberslist = dataEncapsulation.repoToContributor.get(reponame);
			for (String subscriber : subscriberslist) {
				if(dataEncapsulation.userToContribute.containsKey(subscriber)){
		    		dataEncapsulation.userToContribute.get(subscriber).add(reponame);
			    }else{
			    	List<String> valueStringList = new ArrayList<String>();
					valueStringList.add(reponame);
					dataEncapsulation.userToContribute.put(subscriber, valueStringList);
			    }
			}
		}
		printsize(dataEncapsulation.userToContribute);
	}
}
