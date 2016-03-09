package edu.nju.git.data.factory.impl.MapPO;

import edu.nju.git.PO.UserPO;
import edu.nju.git.data.api.centralization.UserMapReader;

public class MapUserPOfactory  extends AbstractMapPO<UserPO> {

	  public MapUserPOfactory() {}
		
	  public MapUserPOfactory(String name){    this.mapService = new UserMapReader(name); }
	    
	  public void setName(String name){  this.mapService = new UserMapReader(name); }


}
