package edu.nju.git.data.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;

public class RepoLocalReader {


	private String repo = "cache/repoinfo.txt";
	
	@SuppressWarnings("unchecked")
	public List<RepoBriefPO> readRepos(String path){
		List<RepoBriefPO> list = null;
		try {
			@SuppressWarnings("resource")
			ObjectInputStream readerStream 
			= new ObjectInputStream(new FileInputStream(new File(path)));
			list = (List<RepoBriefPO>) readerStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public List<RepoBriefPO> readNameSort(){
		return this.readRepos("cache/repo/name.txt");
	}
	
	public List<RepoBriefPO> readForkSort(){
		return this.readRepos("cache/repo/fork.txt");
	}
	
	public List<RepoBriefPO> readStarSort(){
		return readRepos("cache/repo/star.txt");
	}

	public List<RepoBriefPO> readSubscr(){
		return this.readRepos("cache/repo/subsriber.txt");
	}
	
	public List<RepoBriefPO> readUpdate(){
		return this.readRepos("cache/repo/update.txt");
	}
}
