package edu.nju.git.data.init.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.MyObjectWiter;
import edu.nju.git.data.api.RepoReader;
import edu.nju.git.data.api.RepositoriesReader;
import edu.nju.git.data.init.service.RepoInitService;

public class RepoInitInpl implements RepoInitService {

	private RepositoriesReader repositoriesReader;
	private List<RepoBriefPO> pos ;
	
	/**
	 * @param reader can not be null
	 */
	public RepoInitInpl(RepositoriesReader reader) {
		this.repositoriesReader = 
				reader==null? new RepositoriesReader(): reader;
	}

	
	@Override
	public void init() {
		//1. get all repositories' full names
		List<String> repos = repositoriesReader.getNames();
		//2. get details information,one by one
		
		RepoReader repoReader = new RepoReader();
		pos = new ArrayList<RepoBriefPO>();
		for (String string : repos) {
			repoReader.setNames(string);
			pos.add(  repoReader.getBriefPO() );
		}
		this.save();
	}
	
	
	private void save()
	{
		new MyObjectWiter(pos, "cache/repoinfo.txt").excute();
	}

}
