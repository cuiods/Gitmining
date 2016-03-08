package edu.nju.git.data.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;

public class LocalReader {

	private String repo = "cache/repoinfo.txt";
	private String user = "cache/userinfo.txt";

	@SuppressWarnings("unchecked")
	public List<RepoBriefPO> readRepos(){
		List<RepoBriefPO> list = null;
		try {
			@SuppressWarnings("resource")
			ObjectInputStream readerStream 
			= new ObjectInputStream(new FileInputStream(new File(repo)));
			list = (List<RepoBriefPO>) readerStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<RepoBriefPO> readUsers(){
		List<RepoBriefPO> list = null;
		try {
			@SuppressWarnings("resource")
			ObjectInputStream readerStream 
			= new ObjectInputStream(new FileInputStream(new File(user)));
			list = (List<RepoBriefPO>) readerStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
