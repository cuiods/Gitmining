package edu.nju.git.data.init.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.RepositoriesReader;
import edu.nju.git.data.factory.impl.MapPO.MapUserBriefPOfactory;
import edu.nju.git.data.init.service.UserInitService;

public class UserInitImpl implements UserInitService {

	private RepositoriesReader repositoriesReader;
	private List<UserBriefPO> pos ;
	
	public UserInitImpl(RepositoriesReader reader) {
		this.repositoriesReader = 
				reader==null? new RepositoriesReader(): reader;
	}
	
	@Override
	public void init() {
		//1. get all repositories' full names
		List<String> repos = repositoriesReader.getNames();
		//2. get details information,one by one
		
		pos = new ArrayList<UserBriefPO>();
		
		Set<String> stringSet = new HashSet<String>();
		//ItemUserBriefPOfactory userBriefPOfactory = new ItemUserBriefPOfactory();
		MapUserBriefPOfactory userBriefPOfactory = new MapUserBriefPOfactory();
		int i = 0,y=0;;
		for (String string : repos) {
			String tempString = string.split("/")[0];
			if(!stringSet.contains(tempString))
			{
				stringSet.add(tempString);
				userBriefPOfactory.setName(tempString);
				UserBriefPO po = userBriefPOfactory.getPO();
				if(po!=null){
					pos.add(po);
				}
				System.out.print(i++);
			}
			System.out.println("  "+(y++));
		}
		
		new MyObjectWiter(pos, "cache/userinfo.txt").excute();
	}
			

}
