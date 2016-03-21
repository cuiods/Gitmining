package edu.nju.git.data.init.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.comparators.pocomparator.repopocomparator.RepoForkComparator;
import edu.nju.git.data.localDataReader.RepoLocalReader;

public class Mytest {

	private List<RepoBriefPO> nameOrderPOs;
    private List<RepoBriefPO> starOrderPOs;
    private List<RepoBriefPO> forkOrderPOs;
    private List<RepoBriefPO> subscrOrderPOs;
    private List<RepoBriefPO> updateOrderPOs;

    public void read(){
    	RepoLocalReader reader = new RepoLocalReader();
    	this.nameOrderPOs = reader.readNameSort();
    	this.starOrderPOs = reader.readStarSort();
    	this.forkOrderPOs = reader.readForkSort();
    	this.updateOrderPOs = reader.readUpdate();
    	this.subscrOrderPOs = reader.readSubscr();
    }
    
    @SuppressWarnings("unchecked")
	public void sort(){
    	//read one and sort
    	RepoLocalReader reader = new RepoLocalReader();
    	List<RepoBriefPO> temp = reader.readForkSort();
    	this.nameOrderPOs = (List<RepoBriefPO>) ((ArrayList<RepoBriefPO>) temp).clone();
    	this.starOrderPOs = (List<RepoBriefPO>) ((ArrayList<RepoBriefPO>) temp).clone();
    	this.forkOrderPOs = (List<RepoBriefPO>) ((ArrayList<RepoBriefPO>) temp).clone();
    	this.updateOrderPOs = (List<RepoBriefPO>) ((ArrayList<RepoBriefPO>) temp).clone();
    	this.subscrOrderPOs = (List<RepoBriefPO>) ((ArrayList<RepoBriefPO>) temp).clone();
    	
    	nameOrderPOs.sort(new RepoForkComparator());
    	starOrderPOs.sort(new RepoForkComparator());
    	forkOrderPOs.sort(new RepoForkComparator());
    	updateOrderPOs.sort(new RepoForkComparator());
    	subscrOrderPOs.sort(new RepoForkComparator());
    }
	public static void main(String[] args)
	{
		Mytest mytest = new Mytest();
		long a = System.currentTimeMillis();
		System.out.println(a);
		mytest.read();
		long b = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		System.out.println("read time:"+(b-a));
		mytest.sort();
		long c = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		System.out.println("sort time:"+(c-b));
	}

}
