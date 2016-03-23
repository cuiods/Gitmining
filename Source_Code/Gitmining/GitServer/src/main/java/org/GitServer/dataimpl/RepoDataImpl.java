package org.GitServer.dataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.data.rmiservice.RepoDataRMIService;
import org.GitServer.dataread.ReaderAndCount;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

public class RepoDataImpl extends UnicastRemoteObject implements RepoDataRMIService {

	private static final long serialVersionUID = -1928059689118751499L;

	/**
	 * the singleton reference pointed to the instance of this class.
	 */
	private static RepoDataImpl uniqueInstance = null;

	private ReaderAndCount readerAndCount = ReaderAndCount.instance();

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
	public List<RepoPO> getRepoBriefPOs(SortType sortType) throws RemoteException {
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
		//return visitor.visit(this);
		return null;
	}

	@Override
	public RepoVO getRepoBasicInfo(String owner, String repoName) throws RemoteException {
		RepoPO po = readerAndCount.getNameToRepo().get(owner+"/"+repoName);
		if (po!=null) {
			RepoVO vo = POVOConverter.convertToVO(po);
			// TODO: 2016/3/23 set the double value for radar chart to use

			return null;
		}
		else {
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
/*
	@Override
	public List<BranchVO> getRepoBranch(String owner, String repoName) throws RemoteException {
		return null;
	}

	@Override
	public List<RepoBriefVO> getRepoFork(String owner, String repoName) throws RemoteException {
		return null;
	}

	@Override
	public List<CommitVO> getRepoCommit(String owner, String repoName) throws RemoteException {
		return null;
	}

	@Override
	public List<IssueVO> getRepoIssue(String owner, String repoName) throws RemoteException {
		return null;
	}
*/
}
