package edu.nju.git.bl.BrowseModel.impl;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.tools.ComparatorFactory;
import edu.nju.git.type.SortType;

import java.util.Collections;
import java.util.Comparator;
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
        int totalPage = repoBl.getTotalPage();
        if ((pageNum<1)||(pageNum>totalPage)){
            throw new PageOutOfBoundException("the page is out of bound");
        }
        List<RepoBriefVO> briefUserList = repoBl.getBriefRepoList();
        int pageCapacity = repoBl.getDEFAULT_PAGE_CAPACITY();
        //don't forget to change the value of current page
        repoBl.setCurrentPage(pageNum);

        if (pageNum == totalPage) {
            return briefUserList.subList(pageNum*pageCapacity, briefUserList.size());
        }

        return briefUserList.subList(pageCapacity*(pageNum-1), pageCapacity*pageNum);
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
        if (repoBl.getTotalPage()<=0){
            return repoBl.getBriefRepoList();
        }
        Comparator c = ComparatorFactory.getcComparator(sortType);
        if (reverse){
            c=c.reversed();
        }
        Collections.sort(repoBl.getBriefRepoList(), c);
        List<RepoBriefVO> theList = null;
        try{
            theList = jumpToPage(1);
        } catch (PageOutOfBoundException e) {
            e.printStackTrace();
        }
        return theList;
    }
}
