package edu.nju.git.data.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.data.factory.impl.POcreator;
import edu.nju.git.data.service.UserDataService;
import edu.nju.git.datavisitors.uservisitors.UserVisitor;
import edu.nju.git.exception.NoSearchResultException;
import edu.nju.git.tools.POVOConverter;
import edu.nju.git.type.SortType;

/**
 * * This is the implementation of {@link UserDataService}, the class get data from outer api (but in <br>
 * iteration 2 or 3 we may change our strategy and get data from database.<p>
 * @author benchaodong
 * @date 2016-03-04
 */
public class UserDataImpl implements UserDataService {

    /**
     * the reference pointed to the only instance of this class.
     */
    private static UserDataImpl uniqueInstance = null;

    /**
     * the index of <b>ALL</b> users. Each element in the list store one user's brief information.
     */
    private List<UserBriefPO> userIndex;

    /**
     * Singleton method.
     * @return the singleton reference pointed to the instance of this class.
     */
    public static UserDataImpl instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new UserDataImpl();
        }
        return uniqueInstance;
    }

    /**
     * private constructor
     */
    private UserDataImpl(){
        loadUserIndex();
    }

    /**
     * These three list are the index for <b>ALL</b> {@link UserBriefPO} by name order, followers count<br>
     * order and repository count order.
     *
     * <p>We user three list in order to save sort time. Whenever the client change the sort type, the class
     * only change the list to be used, so that it can save much time, especially when there are a lot of <br>
     * elements in the list.
     *
     * <p>Because the {@link UserBriefPO} only stores very small amount of information, so the list won't consume<br>
     * too much space.
     */
    private List<UserBriefPO> nameOrderUsers;

    private List<UserBriefPO> followerOrderUsers;

    private List<UserBriefPO> repoNumOrderUsers;

    /**
     * This method load the user index list {@code repoIndex} from disk.
     * <p>todo
     */
    private void loadUserIndex() {
    	UserLocalReader reader = new UserLocalReader();
    	nameOrderUsers = reader.readNameSort();
    	followerOrderUsers = reader.readFollowerSort();
    	repoNumOrderUsers = reader.readReposSort();
    }

    /**
     * This method save the content of list <tt>userIndex</tt> to disk file so that it can be read<br>
     * next time the system launches.
     * todo
     */
    private void saveUserIndex() {

    }

    @Override
    public List<UserBriefVO> getSearchResult(String regex){
        List<UserBriefVO> resultList = new ArrayList<UserBriefVO>();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        Iterator<UserBriefPO> itr = nameOrderUsers.iterator();
        while(itr.hasNext()){
            UserBriefPO po = itr.next();
            matcher = pattern.matcher(po.getLogin());
            if (matcher.find()) {
                resultList.add(POVOConverter.convert(po));
            }
        }
        return resultList;
    }

    @Override
    public int getTotalCount() {
        return nameOrderUsers.size();
    }

    @Override
    public List<UserBriefPO> getUserBriefPOs(SortType sortType) {
        switch (sortType) {
            case USER_NAME:return nameOrderUsers;
            case FOLLOWER_NUM:return followerOrderUsers;
            case REPO_NUM:return repoNumOrderUsers;
            default:return null;
        }
    }

    /**
     * a creator to create po/vo object
     */
    private POcreator creator = POcreator.getInstance();
    @Override
    public List<UserBriefVO> acceptVisitor(UserVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public UserVO getUserInfo(String userName) {
        return creator.getUserPO(userName);
    }

    @Override
    public List<RepoBriefVO> getUserOwnRepos(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserSubscribeRepos(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserCollaborateRepos(String userName) {
        return null;
    }

    @Override
    public List<RepoBriefVO> getUserContributeRepos(String userName) {
        return null;
    }
}
