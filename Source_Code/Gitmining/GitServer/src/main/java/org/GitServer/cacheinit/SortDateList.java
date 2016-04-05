package org.GitServer.cacheinit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.GitServer.cacheinit.writer.Saver;
import org.GitServer.dataread.Reader;

/**
 * the class for deleting same 
 * @author daixinyan
 * @date 2016-04-05
 */
public class SortDateList {
	
	public void excute(){
		DataEncapsulation dataEncapsulation = new Reader().excute();
		this.dereplicationAndSort(dataEncapsulation.repoToCommit);
		System.out.println("commits dereplication and sorting,done");
		this.dereplicationAndSort(dataEncapsulation.repoToIssue);
		System.out.println("issues dereplication and sorting,done");
		this.dereplicationAndSort(dataEncapsulation.repoToPull);
		System.out.println("pulls dereplication and sorting,done");
		new Saver(dataEncapsulation, "cache").excute();
	}
	
	private void dereplicationAndSort(Map<String, List<String>> map){
		Set<String> set = map.keySet();
		for (String key : set) {
			List<String> temp = map.get(key);
			map.replace(key, this.dereplicationAndSort(temp));
		}
	}
	
	private List<String> dereplicationAndSort(List<String> replicationList){
		
		HashSet<String> hashSet = new HashSet<>(replicationList);
		ArrayList<String> replicationLess = new ArrayList<>(hashSet);
		Collections.sort(replicationLess);
		return replicationLess;
	}
	
	public static void main(String[] args){
		new SortDateList().excute();
	}
}
