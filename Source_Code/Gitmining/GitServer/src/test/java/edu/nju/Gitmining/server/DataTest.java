package edu.nju.Gitmining.server;

import java.util.List;

import org.GitServer.cacheinit.DataEncapsulation;
import org.GitServer.dataread.Reader;

import edu.nju.git.PO.RepoPO;

public class DataTest {
	private DataEncapsulation dataEncapsulation ;
	
	public DataTest(){
		Reader reader = new Reader();
		dataEncapsulation = reader.excute();
		print();
	}
	
	private void print(){
		List<RepoPO> repoPOs = dataEncapsulation.nameOrderRepoPOs;
		for (RepoPO repoPO : repoPOs) {
			System.out.println(repoPO);
		}
	}
	
    public static void main (String [] args) {
    	new DataTest();
    }
}
