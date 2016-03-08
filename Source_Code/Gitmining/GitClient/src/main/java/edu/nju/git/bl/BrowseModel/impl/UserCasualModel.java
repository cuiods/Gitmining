package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by Harry on 2016/3/6.
 */
public class UserCasualModel implements UserBrowseModelService {

    private UserBlImpl userBl;

    public UserCasualModel(UserBlImpl userBl) {
        this.userBl = userBl;
    }


    @Override
    public List<UserBriefVO> getSearchResult(String regex) {
        return null;
    }

    @Override
    public List<UserBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException {
        return null;
    }

    @Override
    public List<UserBriefVO> nextPage() throws PageOutOfBoundException {
        return null;
    }

    @Override
    public List<UserBriefVO> previousPage() throws PageOutOfBoundException {
        return null;
    }

    @Override
    public List<UserBriefVO> sort(SortType sortType, boolean reverse) {
        return null;
    }
}
