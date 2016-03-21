package org.GitServer.dataread;

import java.util.List;
import java.util.Map;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.VO.MyChartVO;

/**
 * read local data form txt file.
 * @author daixinyan
 * @date 2016-03-20
 */
@SuppressWarnings("unused")
public class Reader {

	private List<RepoBriefPO> nameOrderRepoPOs;
    private List<RepoBriefPO> starOrderRepoPOs;
    private List<RepoBriefPO> forkOrderRepoPOs;
    private List<RepoBriefPO> subscrOrderRepoPOs;
    private List<RepoBriefPO> updateOrderRepoPOs;
    
    private List<UserBriefPO> nameOrderUsers;
    private List<UserBriefPO> followerOrderUsers;
    private List<UserBriefPO> repoNumOrderUsers;
	
	private Map<String, RepoBriefPO>  nameToRepo ;
	private Map<String, UserBriefPO>  nameToUser ;
	
	private Map<String, List<String>> userToOwnerRepo ;
	private Map<String, List<String>> userToCollabRepo ;
	private Map<String, List<String>> userToContribute ;
	private Map<String, List<String>> userToSubscribeRepo ;
	
	private Map<String, List<String>> repoToContributor ;
	private Map<String, List<String>> repoToCollab ;
	private Map<String, List<String>> repoToSubscriber;
	
	
	
	
	private MyChartVO language;
	private MyChartVO createTime;
	private MyChartVO contributors;
	private MyChartVO collabrators;
	private MyChartVO size;
	
	private MyChartVO statUserType;  
	private MyChartVO statCompanyUser;
	private MyChartVO statUserBlog;
	private MyChartVO statUserLocation;
	private MyChartVO statUserEmail;
	private MyChartVO statUserCreateTime;
	private MyChartVO statUserOwnRepo;
	private MyChartVO statUserGist;
	private MyChartVO statUserFolllowers;
	
	private MyChartVO statOrganizationUser;
	private MyChartVO statOrganizationRepo;
	
}
