package edu.nju.git.data.init.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.centralization.RepoMapReader;
import edu.nju.git.data.api.liststring.RepositoriesListReader;
import edu.nju.git.data.factory.impl.POfactory.RepoBriefPOfactory;
import edu.nju.git.data.init.service.RepoInitService;

public class RepoInitInpl implements RepoInitService {

	private RepositoriesListReader repositoriesReader;
	private List<RepoBriefPO> pos ;
	
	/**
	 * @param reader can not be null
	 */
	public RepoInitInpl(RepositoriesListReader reader) {
		this.repositoriesReader = 
				reader==null? new RepositoriesListReader(): reader;
	}

	
	@Override
	public void init() {
		//1. get all repositories' full names
		List<Object> repos = repositoriesReader.getNames();
//		System.out.println(repos.size());
//		for (String string : repos) {
//			System.out.println(string);
//		}
		//2. get details information,one by one
		
		RepoMapReader getter = new RepoMapReader();
		RepoBriefPOfactory repoBreifPOfactory = new RepoBriefPOfactory(getter);
		pos = new ArrayList<RepoBriefPO>();
		for (Object string : repos) {
			getter.set(string.toString());
			pos.add(  repoBreifPOfactory.getPO() );
		}
		this.save();
	}
	
	
	private void save()
	{
		new MyObjectWiter(pos, "cache/repoinfo.txt").excute();
	}

}
