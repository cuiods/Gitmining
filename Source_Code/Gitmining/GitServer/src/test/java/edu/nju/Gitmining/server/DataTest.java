package edu.nju.Gitmining.server;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.GitServer.cacheinit.DataEncapsulation;
import org.GitServer.dataread.Reader;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;
@SuppressWarnings("unused")
public class DataTest {
	private DataEncapsulation dataEncapsulation ;
	
	public DataTest(){
		Reader reader = new Reader();
		dataEncapsulation = reader.excute();
		System.out.println("read out");
//		printCommit();
//		printRepos();
//		printRepoToSub();
		printUsers();
	}

	private void printUsers(){
		List<UserPO> users = dataEncapsulation.nameOrderUserPOs;
		for (UserPO userPO : users) {
			if(userPO!=null){
				System.out.println(userPO.getName());
			}else {
				System.out.println("null");
			}
		}
	}
	
	
	private void printRepoToSub(){
		Map<String, List<String>> subs = dataEncapsulation.userToSubscribeRepo;
		List<RepoPO> usernames = dataEncapsulation.nameOrderRepoPOs;
		for (RepoPO userPO : usernames) {
			String name = userPO.getOwnerName();
			System.out.println(name+" : "+subs.get(name));
		}
	}
	
	private void printCommit(){
		Map<String, List<String>> commits = dataEncapsulation.repoToCommit;
		Set<String> keys = commits.keySet();
		for (String string : keys) {
			System.out.println(string+" "+commits.get(string));
		}
	}
	
	private void printRepos(){
		List<RepoPO> repoPOs = dataEncapsulation.nameOrderRepoPOs;
		for (RepoPO repoPO : repoPOs) {
			System.out.println(repoPO);
		}
	}
	
    public static void main (String [] args) {
    	new DataTest();
    }
}
