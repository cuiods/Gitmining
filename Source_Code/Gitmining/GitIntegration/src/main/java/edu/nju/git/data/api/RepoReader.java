package edu.nju.git.data.api;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;

/**
 * read detail repository information by owner/repo_name pair
 * @author daixinyan
 * @date 2016-03-06
 */
public class RepoReader {

	
	public RepoBriefPO getBriefPO(){
		return null;
	}
	
	public RepoPO getPO(){
		return null;
	}
	
	
	private String owner;
	private String repo;
	
	public RepoReader(){}
	
	public RepoReader(String owner, String repo) {
		super();
		this.owner = owner;
		this.repo = repo;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public void setRepo(String repo) {
		this.repo = repo;
	}


	public void setNames(String str)
	{
		String[] names = str.split("/");
		owner = names[0];
		repo = names[1];
	}
	

}
