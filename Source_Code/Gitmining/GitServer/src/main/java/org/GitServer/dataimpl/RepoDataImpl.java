package org.GitServer.dataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.type.MostType;

import org.GitServer.cacheinit.TrimUsers;
import org.GitServer.dataread.ReaderAndCount;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;
import org.GitServer.radarstrategy.impl.LogRepoRadarCalculator;
import org.GitServer.radarstrategy.service.RepoRadarService;
import org.GitServer.repoactivitystrategy.impl.RepoActivityMonthCalculator;
import org.GitServer.repoactivitystrategy.service.RepoActivityCalculator;

public class RepoDataImpl extends UnicastRemoteObject implements RepoDataService {

	private static final long serialVersionUID = -1928059689118751499L;

	/**
	 * the singleton reference pointed to the instance of this class.
	 */
	private static RepoDataImpl uniqueInstance = null;

	private ReaderAndCount readerAndCount = ReaderAndCount.instance();

	private RepoRadarService repoRadarService;

	private RepoActivityCalculator repoActivityCalculator;

	/**
	 * Singleton method.
	 * @return the singleton reference pointed to the instance of this class.
	 */
	public static RepoDataImpl instance() throws RemoteException {
		if (uniqueInstance == null) {
			uniqueInstance = new RepoDataImpl();
		}
		return uniqueInstance;
	}
	
	private  RepoDataImpl() throws RemoteException {
		super();
		repoRadarService = LogRepoRadarCalculator.instance(getRepoPOs(SortType.REPO_NAME));
		repoActivityCalculator = new RepoActivityMonthCalculator();
	}

	@Override
	public List<RepoBriefVO> getSearchResult(String regex) throws RemoteException {
		List<RepoBriefVO> resultList = new ArrayList<RepoBriefVO>();
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		Iterator<RepoPO> itr = readerAndCount.getNameOrderRepoPOs().iterator();
		// TODO: 2016/3/23 we can improve the performance here by not search to end!
		while(itr.hasNext()){
			RepoPO po = itr.next();
			matcher = pattern.matcher(po.getName());
			if (matcher.find()) {
				resultList.add(POVOConverter.convertToBriefVO(po));
			}
		}
		return resultList;
	}

	@Override
	public int getTotalCount() throws RemoteException {
		return readerAndCount.getNameOrderRepoPOs().size();
	}

	@Override
	public List<RepoPO> getRepoPOs(SortType sortType) throws RemoteException {
		switch (sortType) {
			case REPO_NAME:return readerAndCount.getNameOrderRepoPOs();
			case STAR_NUM:return readerAndCount.getStarOrderRepoPOs();
			case FORK_NUM:return readerAndCount.getForkOrderRepoPOs();
			case SUBSCR_NUM:return readerAndCount.getSubscrOrderRepoPOs();
			case UPDATE_TIME:return readerAndCount.getUpdateOrderRepoPOs();
			default:return null;
		}
	}

	@Override
	public List<RepoBriefVO> acceptVisitor(RepoVisitor visitor) throws RemoteException {
		return visitor.visit(this);
	}

	@Override
	public RepoVO getRepoBasicInfo(String owner, String repoName) throws RemoteException {
		String id = owner+"/"+repoName;
		RepoPO po = readerAndCount.getNameToRepo().get(id);
		if (po!=null) {
			RepoVO vo = POVOConverter.convertToVO(po);
			vo.setLanguagsCharts(readerAndCount.getRepoLanguages().get(owner+"/"+repoName));
			//set radar chart data
			vo.setRadar_size(repoRadarService.calSize(po.getSize()));
			vo.setRadar_popular(repoRadarService.calPopular(po.getPopular()));
			vo.setRadar_forks(repoRadarService.calFork(po.getNum_forks()));
			vo.setRadar_activity(repoRadarService.calActivity(po.getRepoActivity()));
			vo.setRadar_complexity(repoRadarService.calComplexity(po.getComplexity()));

			//set line chart data
			List<String> commits = readerAndCount.getRepoToCommit().get(id);
			List<String> pulls = readerAndCount.getRepoToPull().get(id);
			List<String> issues = readerAndCount.getRepoToIssue().get(id);
			vo = repoActivityCalculator.generateLineChart(vo, commits, pulls, issues);
			return vo;
		}
		else {	//no repo matches given name
			return null;
		}
	}

	/**
	 * @param userNames
	 * @return filter the users not in user list.
	 * @date 2016-04-13
	 */
	private List<String> _TrimUsers(List<String> userNames){
		List<String> exsitUsers = new ArrayList<>();
		if(userNames==null){
			return exsitUsers;
		}
		Map<String, UserPO> userHash = readerAndCount.getNameToUser();
		userNames.stream()
				 .filter(p->userHash.containsKey(p))
				 .forEach(name->exsitUsers.add(name));
		
		return exsitUsers;
	}
	
	@Override
	public List<String> getRepoContributor(String owner, String repoName) throws RemoteException {
		List<String> temp = readerAndCount.getRepoToContributor().get(owner+"/"+repoName);
		return _TrimUsers(temp);
	}

	@Override
	public List<String> getRepoCollaborator(String owner, String repoName) throws RemoteException {
		List<String> temp = readerAndCount.getRepoToCollab().get(owner+"/"+repoName);
		return _TrimUsers(temp);
	}

	@Override
	public List<String> getRepoSubscriber(String owner, String repoName) throws RemoteException {
		List<String> temp = readerAndCount.getRepoToSubscriber().get(owner+"/"+repoName);
		return _TrimUsers(temp);
	}

	@Override
	public String getMostRank(MostType type) throws RemoteException {
		RepoPO po = readerAndCount.getMostRepo(type);
		if (po == null) {
			return null;
		}

		return po.getOwnerName()+"/"+po.getName();
	}

}
