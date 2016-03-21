package org.GitServer.dataread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import edu.nju.git.PO.RepoBriefPO;

public class RepoBriefReader {
	private String repo = "cache/repobyname.txt";
	

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
	
	
}
