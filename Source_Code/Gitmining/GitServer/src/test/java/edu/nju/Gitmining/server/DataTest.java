//package edu.nju.Gitmining.server;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.GitServer.cacheinit.DataEncapsulation;
//import org.GitServer.dataread.Reader;
//
//import edu.nju.git.PO.RepoPO;
//import edu.nju.git.PO.UserPO;
//@SuppressWarnings("unused")
//public class DataTest {
//	private DataEncapsulation dataEncapsulation ;
//	
//	public DataTest(){
//		Reader reader = new Reader();
//		dataEncapsulation = reader.excute();
//		System.out.println("read out");
////		printCommit();
////		printPullsDate();
////		printIssueDate();
////		printRepos();
////		printRepoToSub();
////		printUsers();
////		printUserOwne();
////		printrepoToSunCount();
////		printUserNameKeyToSubCount();
////		printallUsers();
////		printCollab();
//		
//	}
//
//	private void printCollab(){
//		Map<String, List<String>> map = dataEncapsulation.userToCollabRepo;
//		List<UserPO> usernames = dataEncapsulation.nameOrderUserPOs;
//		int count = 0;
//		for (UserPO userPO : usernames) {
//		
//			String name = userPO.getName();
//			List<String> temp = map.get(name);
//			if(temp==null){
//				
//			}else{
//				count+=temp.size();
//			}
//		}
//		System.out.println("all user to collab count sum:"+count);
//	}
//	
//	private void printallUsers(){
//		List<UserPO> usernames = dataEncapsulation.allUserPOs;
//		System.out.println(usernames.size());
//		
//	}
//	
//	private void printuserToSubCount(){
//		Map<String, List<String>> subs = dataEncapsulation.userToSubscribeRepo;
//		List<UserPO> usernames = dataEncapsulation.nameOrderUserPOs;
//		int count = 0;
//		for (UserPO userPO : usernames) {
//		
//			String name = userPO.getName();
//			List<String> temp = subs.get(name);
//			if(temp==null){
//				
//			}else{
//				count+=temp.size();
//			}
//		}
//		System.out.println("all user to sub count sum:"+count);
//	}
//	
//	private void printUserNameKeyToSubCount(){
//		Map<String, List<String>> subs = dataEncapsulation.userToSubscribeRepo;
//		System.out.println("all user to sub count sum:"+mapsize(subs));
//	}
//	
//	private int mapsize(Map<String, List<String>> map){
//		Set<String> keySet = map.keySet();
//		int count = 0;
//		for (String key : keySet) {
//			List<String> strings = map.get(key);
//			if(strings==null){
//				System.out.println("null");
//			}else{
//				count += strings.size();
//			}
//		}
//		return count;
//	}
//	
//	private void printrepoToSunCount(){
//		Map<String, List<String>> subs = dataEncapsulation.repoToSubscriber;		
//		System.out.println("all repo to sub count cum:"+mapsize(subs));
//	}
//	
//	private void printUsers(){
//		List<UserPO> users = dataEncapsulation.nameOrderUserPOs;
//		for (UserPO userPO : users) {
//			if(userPO!=null){
//				System.out.println(userPO.getName());
//			}else {
//				System.out.println("null");
//			}
//		}
//	}
//	
//	
//	private void printUserOwne(){
//		Map<String, List<String>> useronwer = dataEncapsulation.userToOwnerRepo;
//		List<UserPO> usernames = dataEncapsulation.nameOrderUserPOs;
//		for (UserPO userPO : usernames) {
//			List<String> temp = useronwer.get(userPO.getName());
//			if(temp==null || temp.size()==0){
//				System.out.println("null");
//			}
//		}
//	}
//	
//	private void printUsertoSub(){
//		Map<String, List<String>> subs = dataEncapsulation.userToSubscribeRepo;
//		List<UserPO> usernames = dataEncapsulation.nameOrderUserPOs;
//		int count = 0;
//		for (UserPO userPO : usernames) {
//		
//			String name = userPO.getName();
//			List<String> temp = subs.get(name);
//			if(temp==null){
//				count++;
//			}else{
//				System.out.println(name+" : "+temp);
//			}
//		}
//		System.out.println(count);
//	}
//	
//	
//	
//	private void printRepoToSub(){
//		Map<String, List<String>> subs = dataEncapsulation.repoToSubscriber;
//		List<RepoPO> usernames = dataEncapsulation.nameOrderRepoPOs;
//		for (RepoPO userPO : usernames) {
//			String name = userPO.getOwnerName()+"/"+userPO.getName();
//			System.out.println(name+" : "+subs.get(name));
//		}
//	}
//	
//	private void printMapToList(Map<String, List<String>> map){
//		Set<String> keys = map.keySet();
//		for (String string : keys) {
//			System.out.print(string);
//			List<String> list = map.get(string);
//			for (String element : list) {
//				System.out.print(" " +element);
//			}
//			System.out.println();
//		}
//	}
//	private void printCommit(){
//		Map<String, List<String>> commits = dataEncapsulation.repoToCommit;
//		this.printMapToList(commits);
//	}
//	
//	private void printPullsDate(){
//		Map<String, List<String>> pulls = dataEncapsulation.repoToPull;
//		this.printMapToList(pulls);
//	}
//	
//	private void printIssueDate(){
//		Map<String, List<String>> issues = dataEncapsulation.repoToIssue;
//		this.printMapToList(issues);
//	}
//	
//	private void printRepos(){
//		List<RepoPO> repoPOs = dataEncapsulation.nameOrderRepoPOs;
//		for (RepoPO repoPO : repoPOs) {
//			System.out.println(repoPO);
//		}
//	}
//	
//    public static void main (String [] args) {
//    	new DataTest();
//    }
//}
