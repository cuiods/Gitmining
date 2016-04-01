package org.GitServer.dataread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class Reader {

	private final String dir = "cache/";
	private final String file_postfix = ".txt";
	/**
	 * before initial we should have already load data from cloud to local cache
	 * @see org.GitServer.cacheinit.Install 
	 */
	public Reader(){}
	
	private DataEncapsulation dataEncapsulation;
	public DataEncapsulation excute(){
		dataEncapsulation = new DataEncapsulation();
		try {
			
			Runtime runtime = Runtime.getRuntime ();  
			Field[] fields = dataEncapsulation.getClass().getDeclaredFields();
			for (Field field : fields) {
				String path = dir+field.getName()+file_postfix;  //file path: "cache/nameOrderRepoPOs.txt"
				ObjectInputStream readerStream 
					= new ObjectInputStream(new FileInputStream(new File(path)));
				
				
				//set value as read object
				field.set(dataEncapsulation, readerStream.readObject());
				readerStream.close();
				
				
				System.out.println("done to read object :"+field.getName()+" at "+path);
				int freeMemory = ( int ) (runtime.freeMemory() / 1024 / 1024);
		        int totalMemory = ( int ) (runtime.totalMemory() / 1024 / 1024);
				System.out.println("curreny free memory:"+freeMemory);
				System.out.println("current total memory:"+totalMemory);
				System.out.println();
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
