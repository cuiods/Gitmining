package org.GitServer.cacheinit;

import java.util.List;

import org.GitServer.cacheinit.loader.IssueLoader;
import org.GitServer.cacheinit.loader.service.AbstractPullIssueCommitLoader;
import org.GitServer.cacheinit.writer.Saver;
import org.GitServer.dataread.Reader;

import edu.nju.git.PO.RepoPO;

/**
 * this class refresh data with new data. the data to be refreshed is the data that should be <br>
 *   computed through other data, includes user value, the chart information.
 *
 * @author benchaodong
 */
public class RefreshData {

	private DataEncapsulation dataEncapsulation ;
	private Saver saver ;
	public RefreshData(){
		read();
		refreshIssue();
		saver.excute();
		System.out.println("done with saving");
	}
	
	private void read(){
		Reader reader = new Reader();
		dataEncapsulation = reader.excute();
		saver = new Saver(dataEncapsulation, "refresh");
		System.out.println("done with read out of local data");
	}
	
	private void refreshIssue()	{
		Iterable<RepoPO> set = dataEncapsulation.nameOrderRepoPOs;
		AbstractPullIssueCommitLoader loader = new IssueLoader();
		int i  = 0;
		for (RepoPO repoPO : set) {
			i++;
			String name = repoPO.getOwnerName()+"/"+repoPO.getName();
			List<String> list = dataEncapsulation.repoToIssue.get(name);
			if(list==null || list.size()==0){
				loader.setName(name);
				dataEncapsulation.repoToIssue.replace(name, loader.read());
				System.out.println("done with replacing issues: "+name+" "+i);
			}
		}
		System.out.println("done with refreshing issues");
		
	}
    public static void main (String [] args) {
    	new RefreshData();
    }
}
