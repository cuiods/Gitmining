package edu.nju.git.data.init.impl;

import edu.nju.git.data.api.RepositoriesReader;
import edu.nju.git.data.init.service.InitialService;

public class Initial implements InitialService {

	private RepositoriesReader repositoriesReader;
	
	public Initial() {
		
	}

	@Override
	public void excute() {
		repositoriesReader = new RepositoriesReader();
		//new RepoInitInpl(repositoriesReader).init();
		new UserInitImpl(repositoriesReader).init();
	}

}
