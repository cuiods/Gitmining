package edu.nju.Gitmining.server;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.GitServer.cacheinit.DataEncapsulation;
import org.GitServer.dataread.Reader;

import edu.nju.git.PO.RepoPO;

public class DataTest {
	private DataEncapsulation dataEncapsulation ;
	
	public DataTest(){
		Reader reader = new Reader();
		dataEncapsulation = reader.excute();
		System.out.println("read out");
		printCommit();
		printRepos();
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
