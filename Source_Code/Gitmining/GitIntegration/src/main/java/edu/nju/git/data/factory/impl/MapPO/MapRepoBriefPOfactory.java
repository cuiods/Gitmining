package edu.nju.git.data.factory.impl.MapPO;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.centralization.RepoMapReader;

public class MapRepoBriefPOfactory extends AbstractMapPO<RepoBriefPO> {


	/**
	 * need set owner and name or full name
	 */
	public MapRepoBriefPOfactory() {
	}
	public MapRepoBriefPOfactory(String owner,String name) {
		this.mapService = (new RepoMapReader(owner,name));
	}
	public MapRepoBriefPOfactory(String fullname) {
		String[] names = fullname.split("/");
		this.mapService = (new RepoMapReader(names[0],names[1]));
	}
	public void setNames(String owner,String name)
	{
		this.mapService = new RepoMapReader(owner,name);
	}
	public void setNames(String fullname) {
		String[] names = fullname.split("/");
		this.mapService = (new RepoMapReader(names[0],names[1]));
	}

	

}
