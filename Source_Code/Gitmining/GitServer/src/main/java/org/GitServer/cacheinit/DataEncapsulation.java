package org.GitServer.cacheinit;

import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;
/**
 * Encapsulated all data in local cache.
 * @author daixinyan
 * @date 2016-03-23
 */
public class DataEncapsulation {
	public List<RepoPO> nameOrderRepoPOs;
	public List<UserPO> nameOrderUserPOs;
	
	public Map<String, List<String>> userToOwnerRepo ;
	public Map<String, List<String>> userToCollabRepo ;
	public Map<String, List<String>> userToContribute ;
	public Map<String, List<String>> userToSubscribeRepo ;
	
	public Map<String, List<String>> repoToContributor ;
	public Map<String, List<String>> repoToCollab ;
	public Map<String, List<String>> repoToSubscriber;

	/**repo's name to commit time*/
	public Map<String, List<String>> repoToCommit;
	/**repo's name to issue created time*/
	public Map<String, List<String>> repoToIssue;
	/**repo's name to pullcreated time*/
	public Map<String, List<String>> repoToPull;
}
