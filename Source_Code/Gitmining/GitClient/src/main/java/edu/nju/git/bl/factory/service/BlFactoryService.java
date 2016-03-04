package edu.nju.git.bl.factory.service;

import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.bl.service.UserBlService;

/**
 * This interface is an <b>abstract factory</b> for creating instances of <tt>BlService</tt> <br>
 *
 * This interface makes the specific factories extendable and maintainable, and it is easy to change <br>
 * the concrete factory instance.
 * @author benchaodong
 * @date 2016-03-04
 */
public interface BlFactoryService {
    /**
     * get the reference pointed to a specific instance of a class that <br>
     *     implements the <tt>RepoBlService</tt> interface.
     * @return the instance of <tt>RepoBlService</tt>
     */
    public RepoBlService getRepoBlService();

    /**
     * get the reference pointed to a specific instance of a class that <br>
     *     implements the <tt>UserBlService</tt> interface.
     * @return the instance of <tt>UserBlService</tt>
     */
    public UserBlService getUserBlService();
}
