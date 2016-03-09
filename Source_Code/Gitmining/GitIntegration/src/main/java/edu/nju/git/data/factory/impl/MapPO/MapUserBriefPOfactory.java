package edu.nju.git.data.factory.impl.MapPO;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.data.api.centralization.UserMapReader;

public class MapUserBriefPOfactory extends AbstractMapPO<UserBriefPO> {


    public MapUserBriefPOfactory() {}
	
    public MapUserBriefPOfactory(String name){    this.mapService = new UserMapReader(name); }
    
    public void setName(String name){  this.mapService = new UserMapReader(name); }


}
