package org.GitServer.dataread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * synchronized,using multi threads reading files, which can speed up.
	 */
	public DataEncapsulation excute(){
		dataEncapsulation = new DataEncapsulation();
		long a = System.currentTimeMillis();
		init();
		long b = System.currentTimeMillis();
		System.out.println("\n we speed "+(b-a)/1000+" seconds to reading");
		return dataEncapsulation;
	}
	
	private void init(){
		Field[] fields = DataEncapsulation.class.getDeclaredFields();
		try{
//			for (Field field : fields) {
//				myFunction(field);
//			}
			List<Thread> threads = new ArrayList<>(fields.length);
			//created multiple threads
			for (Field field : fields) {
				final Thread thread = new Thread(()->{myFunction(field);});
				thread.setPriority( Thread.MAX_PRIORITY);
				thread.start();
				threads.add(thread);
			}
			
			//synchronized methods,the thread who created this object,should wait these reading thread.
			for (Thread thread : threads) {
				thread.join();
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	private void myFunction(Field field) {
		try{
			String path = dir+field.getName()+file_postfix;  //file path: "cache/nameOrderRepoPOs.txt"
			ObjectInputStream readerStream 
				= new ObjectInputStream(new FileInputStream(new File(path)));
			
			//set value as read object
			field.set(dataEncapsulation, readerStream.readObject());
			readerStream.close();
			System.out.println("done to read object :"+field.getName()+" at "+path);
		}catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
