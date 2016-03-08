package edu.nju.git.datavisitors.uservisitors;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.data.service.UserDataService;

import java.util.List;

/**
 * This is a implementation of <b>Visitor</b> pattern which described in the book <l>Design Patterns</l>.<br>
 *
 * <P>The interface defines the operation the concrete visitor should implement to access the persistent data and<br>
 * generate the data to be return back to caller.
 *
 * @author benchaodong
 * @date 2016-03-07
 */
public interface UserVisitor {
    /**
     * the method visit data in data layer and produce the data needed by its caller
     *
     * @param userDataService the interface to access data layer
     * @return the list needed. If there no match result in specific page, the return list contains zero<br>
     *     elements.
     */
    public List<UserBriefVO> visit(UserDataService userDataService);
}
