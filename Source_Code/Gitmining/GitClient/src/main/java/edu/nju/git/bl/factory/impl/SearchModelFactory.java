package edu.nju.git.bl.factory.impl;

import edu.nju.git.bl.BrowseModel.impl.RepoSearchModel;
import edu.nju.git.bl.BrowseModel.impl.UserSearchModel;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.factory.service.BrowseModelFactoryService;

/**
 * Created by Harry on 2016/3/6.
 */
public class SearchModelFactory implements BrowseModelFactoryService{

    private static SearchModelFactory uniqueInstance = null;

    public static SearchModelFactory instance(){
        if (uniqueInstance == null) {
            uniqueInstance = new SearchModelFactory();
        }
        return uniqueInstance;
    }

    private SearchModelFactory(){

    }

    @Override
    public RepoBrowseModelService getRepoBrowseModelService() {
        return RepoSearchModel.instance();
    }

    @Override
    public UserBrowseModelService getUserBrowseModelService() {
        return UserSearchModel.instance();
    }
}
