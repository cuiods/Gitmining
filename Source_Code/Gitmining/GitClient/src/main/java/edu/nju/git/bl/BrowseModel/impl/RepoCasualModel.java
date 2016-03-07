package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by Harry on 2016/3/6.
 */
public class RepoCasualModel implements RepoBrowseModelService {

    private static RepoCasualModel uniqueInstance = null;

    public static RepoCasualModel instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new RepoCasualModel(RepoBlImpl.instance());
        }
        return uniqueInstance;
    }

    private RepoBlImpl repoBl;

    private RepoCasualModel(RepoBlImpl repoBl) {
        this.repoBl = repoBl;
    }


    @Override
    public List<RepoBriefVO> getSearchResult(String regex) {
        return null;
    }

    @Override
    public List<RepoBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException {
        return null;
    }

    @Override
    public List<RepoBriefVO> nextPage() throws PageOutOfBoundException {
        return null;
    }

    @Override
    public List<RepoBriefVO> previousPage() throws PageOutOfBoundException {
        return null;
    }

    @Override
    public List<RepoBriefVO> sort(SortType sortType, boolean reverse) {
        return null;
    }
}
