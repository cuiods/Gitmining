package org.GitServer.dataimpl;

import edu.nju.git.PO.UserPO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.datavisitors.uservisitors.UserVisitor;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;
import org.GitServer.dataread.ReaderAndCount;
import org.GitServer.radarstrategy.impl.SimpleUserRadarCalculator;
import org.GitServer.radarstrategy.service.UserRadarService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Harry on 2016/3/23.
 */
public class UserDataImpl extends UnicastRemoteObject implements UserDataService {

    private static UserDataImpl uniqueInstance;

    public static UserDataImpl instance() throws RemoteException {
        if (uniqueInstance == null) {
            uniqueInstance = new UserDataImpl();
        }
        return uniqueInstance;
    }

    private ReaderAndCount readerAndCount = ReaderAndCount.instance();

    private UserRadarService userRadarService;

    private UserDataImpl() throws RemoteException {
        super();
        userRadarService = SimpleUserRadarCalculator.instance(getUserPOs(SortType.USER_NAME));
    }

    @Override
    public List<UserBriefVO> getSearchResult(String regex) throws RemoteException {
        List<UserBriefVO> resultList = new ArrayList<UserBriefVO>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        Iterator<UserPO> itr = readerAndCount.getNameOrderUsers().iterator();
        while(itr.hasNext()){
            UserPO po = itr.next();
            matcher = pattern.matcher(po.getLogin());
            if (matcher.find()) {
                resultList.add(POVOConverter.convertToBriefVO(po));
            }
        }
        return resultList;
    }

    @Override
    public int getTotalCount() throws RemoteException {
        return readerAndCount.getNameOrderUsers().size();
    }

    @Override
    public List<UserPO> getUserPOs(SortType sortType) throws RemoteException {
        switch (sortType) {
            case USER_NAME:return readerAndCount.getNameOrderUsers();
            case FOLLOWER_NUM:return readerAndCount.getFollowerOrderUsers();
            case FOLLOWING_NUM:return readerAndCount.getFollowingOrderUsers();
            case REPO_NUM:return readerAndCount.getRepoNumOrderUsers();

            default:return null;
        }
    }

    @Override
    public List<UserBriefVO> acceptVisitor(UserVisitor visitor) throws RemoteException {
        return visitor.visit(this);
    }

    @Override
    public UserVO getUserInfo(String userName) throws RemoteException {
        UserPO po = readerAndCount.getNameToUser().get(userName);
        if (po != null) {
            UserVO vo = POVOConverter.convertToVO(po);

            vo.setRadar_activity(userRadarService.calActivity(po.getUserActivity()));
            vo.setRadar_follower(userRadarService.calFollower(po.getFollowNum()));
            vo.setRadar_gist(userRadarService.calGistCount(po.getPublic_gists()));
            vo.setRadar_ownrepos(userRadarService.calRepoCount(po.getPublic_repos()));
            vo.setRadar_value(userRadarService.calUserValue(po.getUserValue()));

            return vo;
        }
        else {
            //no match user, impossible
            return null;
        }
    }

    @Override
    public List<String> getUserOwnRepos(String userName) throws RemoteException {
        return readerAndCount.getUserToOwnerRepo().get(userName);
    }

    @Override
    public List<String> getUserSubscribeRepos(String userName) throws RemoteException {
        return readerAndCount.getUserToSubscribeRepo().get(userName);
    }

    @Override
    public List<String> getUserCollaborateRepos(String userName) throws RemoteException {
        return readerAndCount.getUserToCollabRepo().get(userName);
    }

    @Override
    public List<String> getUserContributeRepos(String userName) throws RemoteException {
        return readerAndCount.getUserToContribute().get(userName);
    }
}
