package org.GitServer.cacheinit.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import org.GitServer.cacheinit.DataEncapsulation;

public class Saver {
	private DataEncapsulation dataEncapsulation;
	private final String rootPath;
	public Saver(DataEncapsulation dataEncapsulation,String rootpath){
		this.dataEncapsulation = dataEncapsulation;
		this.rootPath = rootpath;
	}
	
	public void excute(){
		try {
			Field[] fields = dataEncapsulation.getClass().getDeclaredFields();
			for (Field field : fields) {
				File file = new File(rootPath+"/"+field.getName()+".txt");
				if(!file.exists()){
					file.createNewFile();
				}
				ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
				outputStream.writeObject(field.get(dataEncapsulation));
				outputStream.flush();
				outputStream.close();
			}
		} catch (IllegalArgumentException  e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
