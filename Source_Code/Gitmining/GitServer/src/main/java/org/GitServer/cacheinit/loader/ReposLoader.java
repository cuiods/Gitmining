package org.GitServer.cacheinit.loader;

import java.util.ArrayList;
import java.util.List;

import org.GitServer.cacheinit.loader.POfactory.RepoPOfactory;

import edu.nju.git.PO.RepoPO;
/**
 * before initial , we need inital
 * @author daixinyan
 * @date 2016-03-23
 */
public class ReposLoader {
	
	private Iterable<String> repoNames;
	private List<RepoPO> pos ;
	
	/**
	 * @param reader can not be null
	 */
	public ReposLoader(Iterable<String> reader){
		this.repoNames =  reader;
		init();
	}

	
	/**
	 * <br/><b>precondition</b>： this.pos can not be null ; the sever can connect to network.
	 * <br/><b>postcondition</b>：
	 * @date 2016-03-23
	 */
	private void init() {
		// create a po factory
		RepoPOfactory repoBreifPOfactory = new RepoPOfactory();
		
		//initial list,and add into list one by one
		pos = new ArrayList<RepoPO>(3300);
		for (String string : repoNames) {
			repoBreifPOfactory.setNames(string);
			pos.add(  repoBreifPOfactory.getPO() );
			System.out.println("reading out of repo: "+string);
		}
		System.out.println("done with reading repos");
	}

    
	/**
	 * <br/><b>precondition</b>：the object has been created (has been init)at the environment with network.
	 * <br/><b>postcondition</b>：return the intial not null list.
	 * @return
	 * @date 2016-03-23
	 */
	public List<RepoPO> getPos() {return pos;}
	
	
}
