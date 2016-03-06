package edu.nju.git.bl.factory.impl;

import edu.nju.git.bl.BrowseModel.impl.RepoCasualModel;
import edu.nju.git.bl.BrowseModel.impl.UserCasualModel;
import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;
import edu.nju.git.bl.factory.service.BrowseModelFactoryService;

/**
 * Created by Harry on 2016/3/6.
 */
public class CasualModelFactory implements BrowseModelFactoryService {

    private static CasualModelFactory uniqueInstance = null;

    public static CasualModelFactory instance(){
        if (uniqueInstance == null) {
            uniqueInstance =new CasualModelFactory();
        }
        return uniqueInstance;
    }

    private CasualModelFactory(){

    }

    @Override
    public RepoBrowseModelService getRepoBrowseModelService() {
        return RepoCasualModel.instance();
    }

    @Override
    public UserBrowseModelService getUserBrowseModelService() {
        return UserCasualModel.instance();
    }
}
