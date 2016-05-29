package edu.nju.model.service;

import edu.nju.model.pojo.RepoVO;
import edu.nju.model.pojo.SimpleRepoVO;

import java.util.List;

/**
 * Created by Harry on 2016/5/29.
 */
public interface HobbyModelService {

    public boolean starRepo(String ownername,String reponame,String webUsername);

    public boolean unstarRepo(String ownername, String reponame,String webUsername);

    /**
     * get the repos stared by the web user
     * @param webUsername
     * @return
     */
    public List<SimpleRepoVO> getStaredRepos(String webUsername, int page);

    /**
     * get the count of pages of the repos stared
     * @param webUsername
     * @return
     */
    public int getTotalStarPage(String webUsername);
}
