package edu.nju.git.data.factory.impl;

import edu.nju.git.data.factory.service.DataFactoryService;
import edu.nju.git.data.impl.RepoDataImpl;
import edu.nju.git.data.impl.UserDataImpl;
import edu.nju.git.data.service.RepoDataService;
import edu.nju.git.data.service.UserDataService;

/**
 * A specific factory that returns the specific <tt>UserDataService</tt> and <tt>RepoDataService</tt>.
 * @author benchadong
 * @date 2016-03-04
 */
public class DataFactory implements DataFactoryService {

    /**
     * The reference pointed to the only instance of this class.
     */
    private static DataFactory uniqueInstance = null;

    /**
     * Singleton method.
     * @return the instance of this class.
     */
    public static DataFactory instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new DataFactory();
        }
        return uniqueInstance;
    }

    /**
     * We make the constructor private to prevent users from creating instance of this class arbitrarily.
     * <p>Anyone who want to get the instance of this class must use <tt>instance()</tt> method.
     */
    private DataFactory(){

    }

    @Override
    public UserDataService getUserDataService() {
        return UserDataImpl.instance();
    }

    @Override
    public RepoDataService getRepoDataService() {
        return RepoDataImpl.instance();
    }
}
