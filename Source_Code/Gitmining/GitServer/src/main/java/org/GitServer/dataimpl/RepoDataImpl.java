package org.GitServer.dataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.git.data.service.RepoDataService;
import org.GitServer.dataread.ReaderAndCount;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

public class RepoDataImpl extends UnicastRemoteObject implements RepoDataService {

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
		Iterator<RepoBriefPO> itr = readerAndCount.getNameOrderRepoPOs().iterator();
		while(itr.hasNext()){
			RepoBriefPO po = itr.next();
			matcher = pattern.matcher(po.getName());
			if (matcher.find()) {
				resultList.add(POVOConverter.convert(po));
			}
		}
		return resultList;
	}

	@Override
	public int getTotalCount() throws RemoteException {
		return readerAndCount.getNameOrderRepoPOs().size();
	}

	@Override
	public List<RepoBriefPO> getRepoBriefPOs(SortType sortType) throws RemoteException {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserBriefVO> getRepoContributor(String owner, String repoName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserBriefVO> getRepoCollaborator(String owner, String repoName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BranchVO> getRepoBranch(String owner, String repoName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepoBriefVO> getRepoFork(String owner, String repoName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommitVO> getRepoCommit(String owner, String repoName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssueVO> getRepoIssue(String owner, String repoName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
