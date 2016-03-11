package edu.nju.git.data.init.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 * @author daixinyan
 * @date 2016-03-07
 */
public class MyObjectWiter {

	private Object object;
	private String path;
	

	public MyObjectWiter(Object object, String path) {
		super();
		this.object = object;
		this.path = path;
	}

	public void set(Object object, String path) {
		this.object = object;
		this.path = path;
	}

	public boolean excute()
	{
		try {
			File file = new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(object);
			outputStream.flush();
			outputStream.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		    return false;
	}
}
