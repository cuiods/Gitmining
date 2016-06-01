package edu.nju.model.service;

import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleRepoVO;
import edu.nju.model.pojo.UserVO;

import java.util.List;

/**
 * Created by Harry on 2016/5/29.
 */
public interface HobbyModelService {

    public boolean starRepo(String ownername,String reponame,String webUsername);

    public boolean unstarRepo(String ownername, String reponame,String webUsername);

    public boolean starUser(String username, String webUsername);

    public boolean unStarUser(String username, String webUsername);

    /**
     * get the repos stared by the web user
     * @param webUsername
     * @return
     */
    public List<SimpleRepoVO> getStaredRepos(String webUsername, int page);

    public List<UserVO> getStaredUsers(String webUsername, int page);

    /**
     * get the count of pages of the repos stared
     * @param webUsername
     * @return
     */
    public int getTotalRepoStarPage(String webUsername);

    public int getTotalUserStarPage(String webUsername);
}
