package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.datavisitors.repovisitors.RepoNameOrderVisitor;
import edu.nju.git.datavisitors.repovisitors.SimpleRepoVisitor;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.tools.VisitorFactory;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * Created by Harry on 2016/3/6.
 */
public class RepoCasualModel implements RepoBrowseModelService {

    private RepoBlImpl repoBl;

    /**
     * the visitor indicates the way the result list is sorted by.it will change as the <tt>sort</tt><br>
     *     method is invoked.In default the visitor is a {@link RepoNameOrderVisitor}
     */
    private SimpleRepoVisitor visitor;

    public RepoCasualModel(RepoBlImpl repoBl) {
        this.repoBl = repoBl;
        visitor = new RepoNameOrderVisitor(1, false);
    }


    @Override
    public List<RepoBriefVO> getSearchResult(String regex) {
        return null;
    }

    @Override
    public List<RepoBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException {
        int totalPage = repoBl.getTotalPage();
        if ((pageNum<1)||(pageNum>totalPage)){
            throw new PageOutOfBoundException("the page is out of bound");
        }
        visitor.setPage(pageNum);
        repoBl.setCurrentPage(pageNum);
        return visitor.visit(repoBl.getRepoDataService());
    }

    @Override
    public List<RepoBriefVO> nextPage() throws PageOutOfBoundException {
        return jumpToPage(repoBl.getCurrentPage()+1);
    }

    @Override
    public List<RepoBriefVO> previousPage() throws PageOutOfBoundException {
        return jumpToPage(repoBl.getCurrentPage()-1);
    }

    @Override
    public List<RepoBriefVO> sort(SortType sortType, boolean reverse) {
        int current_page = visitor.getPage();
        visitor = (SimpleRepoVisitor) VisitorFactory.getRepoVisitor(sortType);
        visitor.setPage(current_page);
        visitor.setReverse(reverse);
        return visitor.visit(repoBl.getRepoDataService());
    }
}
