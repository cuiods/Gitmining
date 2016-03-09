package edu.nju.git.data.init.impl;

import edu.nju.git.data.api.liststring.RepositoriesListReader;
import edu.nju.git.data.init.service.InitialService;

public class Initial implements InitialService {

	private RepositoriesListReader repositoriesReader;
	
	public Initial() {
		
	}

	@Override
	public void excute() {
		repositoriesReader = new RepositoriesListReader();
		new RepoInitInpl(repositoriesReader).init();
		new UserInitImpl(repositoriesReader).init();
	}

}
