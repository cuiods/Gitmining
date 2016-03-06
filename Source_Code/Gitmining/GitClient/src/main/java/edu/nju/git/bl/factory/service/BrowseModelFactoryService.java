package edu.nju.git.bl.factory.service;

import edu.nju.git.bl.BrowseModel.service.RepoBrowseModelService;
import edu.nju.git.bl.BrowseModel.service.UserBrowseModelService;

/**
 * Created by Harry on 2016/3/6.
 */
public interface BrowseModelFactoryService {

    public RepoBrowseModelService getRepoBrowseModelService();

    public UserBrowseModelService getUserBrowseModelService();
}
