package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * The implementation class of {@link RepoBrowseModelService}.
 * <p>This class is the core class of the strategy pattern for browse the repository data.<br>
 *    This class is used in {@link edu.nju.git.bl.impl.RepoBlImpl} class to encapsulate the operations <br>
 *        that may change for different browse models.
 *
 * @see edu.nju.git.bl.impl.RepoBlImpl
 * @author benchaodong
 * @date 2016-03-05
 */
public class RepoSearchModel implements RepoBrowseModelService {

    private static RepoSearchModel uniqueInstance = null;

    public static RepoSearchModel instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new RepoSearchModel(RepoBlImpl.instance());
        }
        return uniqueInstance;
    }

    private RepoBlImpl repoBl;

    private RepoSearchModel(RepoBlImpl repoBl){
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
