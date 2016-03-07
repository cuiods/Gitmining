package edu.nju.git.datavisitors.repovisitors;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.data.service.RepoDataService;

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
public interface RepoVisitor {
    /**
     * the method visit data in data layer and produce the data needed by its caller
     *
     * @param repoDataService the interface to access data layer
     * @return the list needed
     */
    public List<RepoBriefVO> visit(final RepoDataService repoDataService);
}
