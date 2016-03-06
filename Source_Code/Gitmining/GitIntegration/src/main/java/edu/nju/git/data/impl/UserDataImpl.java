package edu.nju.git.data.impl;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.VO.UserVO;
import edu.nju.git.data.service.UserDataService;

import java.util.List;

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
     * This method load the user index list {@code repoIndex} from disk.
     * <p>todo
     */
    private void loadUserIndex() {

    }

    /**
     * This method save the content of list <tt>userIndex</tt> to disk file so that it can be read<br>
     * next time the system launches.
     * todo
     */
    private void saveUserIndex() {

    }

    @Override
    public List<UserBriefVO> getSearchResult(String keyword) {
        return null;
    }

    @Override
    public UserVO getUserInfo(String userName) {
        return null;
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
