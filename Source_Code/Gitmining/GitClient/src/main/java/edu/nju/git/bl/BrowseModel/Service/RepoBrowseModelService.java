package edu.nju.git.bl.BrowseModel.service;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.util.List;

/**
 * This interface defines what operations a class should implement when it browses through repository data.
 *
 * <p>This is a interface for <b>Strategy</b> pattern.We encapsulate the operations that would change<br>
 *    when we change browse models.
 *
 * @author benchaodong
 * @date 2016-03-05
 */
public interface RepoBrowseModelService {

    /**
     * get the list match the regex.
     * @param regex a regex represents the content you want to search.
     * @return result list of repositories.
     */
    public List<RepoBriefVO> getSearchResult(String regex);

    /**
     * jump to the page specified by the parameter <tt>pageNum</tt>
     * @param pageNum where you want to jump
     * @return result list of repositories.
     * @throws PageOutOfBoundException
     */
    public List<RepoBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException;

    /**
     * jump to next page.
     * @return result list of repositories.
     * @throws PageOutOfBoundException
     */
    public List<RepoBriefVO> nextPage() throws PageOutOfBoundException;

    /**
     * jump to previous page
     * @return list of repository information
     * @throws PageOutOfBoundException
     */
    public List<RepoBriefVO> previousPage() throws PageOutOfBoundException;

    /**
     * sort the result list.
     * @param sortType the way the list is sorted in.
     * @param reverse whether the sorted result is reversed.
     * @see SortType
     * @return result list of repositories after sorted.
     */
    public List<RepoBriefVO> sort(SortType sortType, boolean reverse);
}
