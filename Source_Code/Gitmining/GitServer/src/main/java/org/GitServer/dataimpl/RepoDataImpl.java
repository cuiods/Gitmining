package org.GitServer.dataimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.GitServer.dataread.ReaderAndCount;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.RepoVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.data.rmiservice.RepoDataRMIService;
import edu.nju.git.datavisitors.repovisitors.RepoVisitor;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

public class RepoDataImpl extends UnicastRemoteObject implements RepoDataRMIService{

	private static final long serialVersionUID = -1928059689118751499L;

	private ReaderAndCount readerAndCount = new ReaderAndCount();
	
	public  RepoDataImpl() throws RemoteException {
		super();
	}

	@Override
	public List<RepoBriefVO> getSearchResult(String regex) throws RemoteException {
		return null;
	}

	@Override
	public int getTotalCount() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RepoBriefPO> getRepoBriefPOs(SortType sortType) throws RemoteException {
		List<RepoBriefPO> list = readerAndCount.getNameOrderRepoPOs();
		return list;
	}

	@Override
	public List<RepoBriefVO> acceptVisitor(RepoVisitor visitor) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
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
