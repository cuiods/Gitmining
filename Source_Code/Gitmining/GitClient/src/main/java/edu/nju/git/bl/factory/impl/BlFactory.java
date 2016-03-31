package edu.nju.git.bl.factory.impl;

import edu.nju.git.bl.impl.RepoBlImpl;
import edu.nju.git.bl.impl.RepoChartBlImpl;
import edu.nju.git.bl.impl.UserBlImpl;
import edu.nju.git.bl.impl.UserChartBlImpl;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.bl.service.RepoChartBlService;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.bl.service.UserChartBlService;

/**
 * A specific factory that returns the specific <tt>UserBlService</tt> and <tt>RepoBlService</tt>.
 * @author benchadong
 * @date 2016-03-04
 */
public class BlFactory {

    /**
     * The reference pointed to the only instance of this class.
     */
    private static BlFactory uniqueInstance = null;

    /**
     * Singleton method.
     * @return the instance of this class.
     */
    public static BlFactory instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new BlFactory();
        }
        return uniqueInstance;
    }

    /**
     * We make the constructor private to prevent users from creating instance of this class arbitrarily.
     * <p>Anyone who want to get the instance of this class must use <tt>instance()</tt> method.
     */
    private BlFactory(){

    }

    public RepoBlService getRepoBlService() {
        return RepoBlImpl.instance();
    }

    public UserBlService getUserBlService() {
        return UserBlImpl.instance();
    }

    public RepoChartBlService getRepoChartBlService() {
        return RepoChartBlImpl.instance();
    }

    public UserChartBlService getUserChartBlService() {
        return UserChartBlImpl.instance();
    }
}
