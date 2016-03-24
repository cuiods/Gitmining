package org.GitServer.dataread;

import java.io.*;
import java.lang.reflect.Field;

import org.GitServer.cacheinit.DataEncapsulation;

/**
 * this class  just read local data as fields without any operation,
 * these fields can is public by getter function.</br>
 * before initial we should install server and load data from cloud to local cache
 * @see org.GitServer.cacheinit.Install 
 * @author daixinyan
 * @date 2016-03-23
 */
@SuppressWarnings("unused")
public class Reader {

	private final String dir = "cache/";
	/**
	 * before initial we should have already load data from cloud to local cache
	 * @see org.GitServer.cacheinit.Install 
	 */
	public Reader(){}
	public DataEncapsulation excute(){
		DataEncapsulation dataEncapsulation = new DataEncapsulation();
		try {
			Field[] fields = dataEncapsulation.getClass().getDeclaredFields();
			for (Field field : fields) {
				String path = dir+field.getName()+".txt";  //file path: "cache/nameOrderRepoPOs.txt"
				ObjectInputStream readerStream 
					= new ObjectInputStream(new FileInputStream(new File(path)));
				field.set(dataEncapsulation, readerStream.readObject());
				readerStream.close();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataEncapsulation;
	}
}
