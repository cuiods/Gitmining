package edu.nju.git.data.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import edu.nju.git.PO.UserBriefPO;

public class UserLocalReader {

	@SuppressWarnings("unchecked")
	public List<UserBriefPO> readUsers(String path){
		List<UserBriefPO> list = null;
		try {
			@SuppressWarnings("resource")
			ObjectInputStream readerStream 
			= new ObjectInputStream(new FileInputStream(new File(path)));
			list = (List<UserBriefPO>) readerStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<UserBriefPO> readNameSort(){
		return this.readUsers("cache/user/name.txt");
	}
	
	public List<UserBriefPO> readReposSort(){
		return this.readUsers("cache/user/userrepos.txt");
	}
	
	public List<UserBriefPO> readFollowerSort(){
		return this.readUsers("cache/user/follower.txt");
	}

}
