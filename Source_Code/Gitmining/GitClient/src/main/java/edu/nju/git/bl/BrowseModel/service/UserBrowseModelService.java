package edu.nju.git.bl.BrowseModel.service;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.exception.PageOutOfBoundException;
import edu.nju.git.type.SortType;

import java.rmi.RemoteException;
import java.util.List;

/**
 * This interface defines what operations a class should implement when it browses through user data.
 *
 * <p>This is a interface for <b>Strategy</b> pattern.We encapsulate the operations that would change<br>
 *    when we change browse models.
 *
 * @author benchaodong
 * @date 2016-03-05
 */
public interface UserBrowseModelService {

    /**
     * jump to the page specified by the parameter <tt>pageNum</tt>
     * @param pageNum where you want to jump
     * @return result list of users.
     * @throws PageOutOfBoundException
     */
    public List<UserBriefVO> jumpToPage(int pageNum) throws PageOutOfBoundException;

    /**
     * jump to next page.
     * @return result list of users.
     * @throws PageOutOfBoundException
     */
    public List<UserBriefVO> nextPage() throws PageOutOfBoundException;

    /**
     * jump to previous page
     * @return list of user information
     * @throws PageOutOfBoundException
     */
    public List<UserBriefVO> previousPage() throws PageOutOfBoundException;

    /**
     * sort the result list.
     * @param sortType the way the list is sorted in.
     * @param reverse whether the sorted result is reversed.
     * @see SortType
     * @return result list of users after sorted.
     */
    public List<UserBriefVO> sort(SortType sortType, boolean reverse) throws RemoteException;
}
