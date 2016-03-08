package edu.nju.git.data.init.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.RepositoriesReader;
import edu.nju.git.data.factory.impl.MapPO.MapRepoBriefPOfactory;
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
//		System.out.println(repos.size());
//		for (String string : repos) {
//			System.out.println(string);
//		}
		//2. get details information,one by one
		
		MapRepoBriefPOfactory repoBreifPOfactory = new MapRepoBriefPOfactory();
		//ItemRepoBriefPOfactory repoBreifPOfactory = new ItemRepoBriefPOfactory();
		pos = new ArrayList<RepoBriefPO>();
		for (String string : repos) {
			repoBreifPOfactory.setNames(string);
			pos.add(  repoBreifPOfactory.getPO() );
		}
		this.save();
	}
	
	
	private void save()
	{
		new MyObjectWiter(pos, "cache/repoinfo.txt").excute();
	}

}
