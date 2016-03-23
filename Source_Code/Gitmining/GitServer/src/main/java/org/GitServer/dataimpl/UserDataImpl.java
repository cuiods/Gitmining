package org.GitServer.dataimpl;

import edu.nju.git.PO.UserPO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.data.rmiservice.UserDataRMIService;
import edu.nju.git.datavisitors.uservisitors.UserVisitor;
import edu.nju.git.type.SortType;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Harry on 2016/3/23.
 */
public class UserDataImpl implements UserDataRMIService {

    @Override
    public List<UserBriefVO> getSearchResult(String regex) throws RemoteException {
        return null;
    }

    @Override
    public int getTotalCount() throws RemoteException {
        return 0;
    }

    @Override
    public List<UserPO> getPOs(SortType sortType) throws RemoteException {
        return null;
    }

    @Override
    public List<UserBriefVO> acceptVisitor(UserVisitor visitor) throws RemoteException {
        return null;
    }

    @Override
    public UserVO getUserInfo(String userName) throws RemoteException {
        return null;
    }

    @Override
    public List<String> getUserOwnRepos(String userName) throws RemoteException {
        return null;
    }

    @Override
    public List<String> getUserSubscribeRepos(String userName) throws RemoteException {
        return null;
    }

    @Override
    public List<String> getUserCollaborateRepos(String userName) throws RemoteException {
        return null;
    }

    @Override
    public List<String> getUserContributeRepos(String userName) throws RemoteException {
        return null;
    }
}
