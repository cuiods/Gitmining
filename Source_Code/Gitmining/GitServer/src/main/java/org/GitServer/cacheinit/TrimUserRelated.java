package org.GitServer.cacheinit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.GitServer.dataread.Reader;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;

/**
 * @deprecated
 * @author daixinyan
 * @date 2016-04-13
 */
public class TrimUserRelated {

	private HashSet<String> userNames = new HashSet<>();
	private DataEncapsulation dataEncapsulation;
	public TrimUserRelated(){
		dataEncapsulation = new Reader().excute();
		List<UserPO> userPOs = dataEncapsulation.nameOrderUserPOs;
		for (UserPO userPO : userPOs) {
			userNames.add(userPO.getName());
		}
	}
	public void excute(){
		_trimMap(dataEncapsulation.repoToCollab);
		_trimMap(dataEncapsulation.repoToSubscriber);
		_trimMap(dataEncapsulation.repoToContributor);
	}
	private void _trimMap(Map<String, List<String>> map){
		
		
		List<RepoPO> repoPOs = dataEncapsulation.nameOrderRepoPOs;
		
		for (RepoPO repoPO : repoPOs) {
			String key = repoPO.getOwnerName()+"/"+repoPO.getName();
			List<String> valueStringList = map.get(key);
			
			List<String> exsitsUser = new ArrayList<>();
//			for (String string : valueStringList) {
//				if(userNames.contains(string)){
//					exsitsUser.add(string);
//				}
//			}
			 valueStringList.stream()
			 				.filter(e->userNames.contains(e) )
			 				.forEach((e)->{exsitsUser.add(e);});
			map.put(key, exsitsUser);
			System.out.println("size from "+valueStringList.size()+" to "+exsitsUser.size());
		}
		System.out.println("done");
		System.out.println();
	}
	
	public static void main(String[] args){
		new TrimUserRelated().excute();
	}
}
