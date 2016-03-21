package org.GitServer.dataread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import edu.nju.git.PO.UserBriefPO;

public class UserBriefReader {
	
	private final String user = "cache/userbyname.txt";
	
	@SuppressWarnings("unchecked")
	public List<UserBriefPO> readUsers(){
		List<UserBriefPO> list = null;
		try {
			@SuppressWarnings("resource")
			ObjectInputStream readerStream 
			= new ObjectInputStream(new FileInputStream(new File(user)));
			list = (List<UserBriefPO>) readerStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}
