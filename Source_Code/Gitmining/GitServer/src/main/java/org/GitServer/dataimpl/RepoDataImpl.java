package org.GitServer.dataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.data.service.RepoDataService;
import org.GitServer.dataread.ReaderAndCount;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;
import org.GitServer.radarstrategy.impl.SimpleRepoRadarCalculator;
import org.GitServer.radarstrategy.service.RepoRadarService;

public class RepoDataImpl extends UnicastRemoteObject implements RepoDataService {

	private static final long serialVersionUID = -1928059689118751499L;

	/**
	 * the singleton reference pointed to the instance of this class.
	 */
	private static RepoDataImpl uniqueInstance = null;

	private ReaderAndCount readerAndCount = ReaderAndCount.instance();

	private RepoRadarService repoRadarService;

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
		repoRadarService = SimpleRepoRadarCalculator.instance(getRepoPOs(SortType.REPO_NAME));
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
		RepoPO po = readerAndCount.getNameToRepo().get(owner+"/"+repoName);
		if (po!=null) {
			RepoVO vo = POVOConverter.convertToVO(po);

			vo.setRadar_size(repoRadarService.calSize(po.getSize()));
			vo.setRadar_popular(repoRadarService.calPopular(po.getPopular()));
			vo.setRadar_forks(repoRadarService.calFork(po.getNum_forks()));
			vo.setRadar_activity(repoRadarService.calActivity(po.getRepoActivity()));
			vo.setRadar_complexity(repoRadarService.calComplexity(po.getComplexity()));
			return vo;
		}
		else {	//no repo matches given name
			return null;
		}
	}

	@Override
	public List<String> getRepoContributor(String owner, String repoName) throws RemoteException {
		return readerAndCount.getRepoToContributor().get(owner+"/"+repoName);
	}

	@Override
	public List<String> getRepoCollaborator(String owner, String repoName) throws RemoteException {
		return readerAndCount.getRepoToCollab().get(owner+"/"+repoName);
	}

	@Override
	public List<String> getRepoSubscriber(String owner, String repoName) throws RemoteException {
		return readerAndCount.getRepoToSubscriber().get(owner+"/"+repoName);
	}

}
