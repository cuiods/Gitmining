package edu.nju.git.data.factory.service;

import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.data.service.UserDataService;

/**
 * This interface is an <b>abstract factory</b> for creating instances of <tt>DataService</tt> <br>
 *
 * This interface makes the specific factories extendable and maintainable, and it is easy to change <br>
 * the factory instance.
 * @author benchaodong
 * @date 2016-03-04
 */
public interface DataFactoryService {
    /**
     * get the reference pointed to a specific instance of a class that <br>
     *     implements the <tt>UserDataService</tt> interface.
     * @return the instance of <tt>UserDataService</tt>
     */
    public UserDataService getUserDataService();

    /**
     * get the reference pointed to a specific instance of a class that <br>
     *     implements the <tt>RepoDataService</tt> interface.
     * @return the instance of <tt>RepoDataService</tt>
     */
    public RepoDataService getRepoDataService();
}
