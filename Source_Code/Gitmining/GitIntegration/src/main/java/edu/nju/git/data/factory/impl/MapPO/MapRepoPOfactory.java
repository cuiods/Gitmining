package edu.nju.git.data.factory.impl.MapPO;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.data.api.centralization.RepoMapReader;

public class MapRepoPOfactory extends AbstractMapPO<RepoPO> {




	/**
	 * need set owner and name or full name
	 */
	public MapRepoPOfactory() {
	}
	public MapRepoPOfactory(String owner,String name) {
		this.mapService = (new RepoMapReader(owner,name));
	}
	public MapRepoPOfactory(String fullname) {
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
