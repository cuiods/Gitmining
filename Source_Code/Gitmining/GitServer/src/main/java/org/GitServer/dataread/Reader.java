package org.GitServer.dataread;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.GitServer.cacheinit.DataEncapsulation;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;

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

	/**
	 * before initial we should install server and load data from cloud to local cache
	 * @see org.GitServer.cacheinit.Install 
	 */
	public Reader(){}
	private DataEncapsulation dataEncapsulation;
	public void excute(){
		try {
			Field[] fields = dataEncapsulation.getClass().getDeclaredFields();
			for (Field field : fields) {
				
			}
		} catch (Exception e) {
		}
	}
}
