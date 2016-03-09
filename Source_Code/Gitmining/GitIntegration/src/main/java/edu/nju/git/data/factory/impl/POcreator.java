package edu.nju.git.data.factory.impl;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.data.api.centralization.RepoMapReader;
import edu.nju.git.data.api.centralization.UserMapReader;
import edu.nju.git.data.factory.impl.POfactory.AbstractFieldsGetter;
import edu.nju.git.data.factory.impl.POfactory.RepoBriefPOfactory;
import edu.nju.git.data.factory.impl.POfactory.RepoPOfactory;
import edu.nju.git.data.factory.impl.POfactory.UserBriefPOfactory;
import edu.nju.git.data.factory.impl.POfactory.UserPOfactory;

public class POcreator {

	public POcreator() {
		// TODO Auto-generated constructor stub
	}
	
	public UserPO getUserPO(String name){
		AbstractFieldsGetter getter = new UserMapReader(name);
//		AbstractFieldsGetter getter = new UserItemReader(name);
		UserPOfactory pofactory = new UserPOfactory(getter);
		return pofactory.getPO();
	}

	public UserBriefPO getUserBriefPO(String name){
		AbstractFieldsGetter getter = new UserMapReader(name);
//		AbstractFieldsGetter getter = new UserItemReader(name);
		UserBriefPOfactory pofactory = new UserBriefPOfactory(getter);
		return pofactory.getPO();
	}
	
	public RepoBriefPO getRepoBriefPO(String fullname){
		AbstractFieldsGetter getter = new RepoMapReader(fullname);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoBriefPOfactory pOfactory = new RepoBriefPOfactory(getter);
		return pOfactory.getPO();
		
	}
	
	public RepoBriefPO getRepoBriefPO(String owner,String name){
		AbstractFieldsGetter getter = new RepoMapReader(owner, name);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoBriefPOfactory pOfactory = new RepoBriefPOfactory(getter);
		return pOfactory.getPO();
	}
	
	public RepoPO getRepoPO(String fullname){
		AbstractFieldsGetter getter = new RepoMapReader(fullname);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoPOfactory pOfactory = new RepoPOfactory(getter);
		return pOfactory.getPO();
	}
	
	public RepoPO getRepoPO(String owner,String name){
		AbstractFieldsGetter getter = new RepoMapReader(owner, name);
//		AbstractFieldsGetter getter = new RepoItemReader(fullname);
		RepoPOfactory pOfactory = new RepoPOfactory(getter);
		return pOfactory.getPO();
	}
	
	
	

}
