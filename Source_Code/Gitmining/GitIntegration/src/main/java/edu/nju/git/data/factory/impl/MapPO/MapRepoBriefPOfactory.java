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

	/**
	 * <br/><b>precondition</b>：this.owner this.repo (map)must be set
	 * <br/><b>postcondition</b>：return a PO
	 * @return 
	 * @date 2016-03-06
	 */
	@Override
	public RepoBriefPO getPO() {
		map = this.mapService.getMap();
		RepoBriefPO repoBriefPO = new RepoBriefPO();
		
		String fullname[] = map.get("full_name").toString().split("/");
		repoBriefPO.setOwner(fullname[0]);
		repoBriefPO.setName(fullname[1]);
		
		Object description = map.get("description");
		repoBriefPO.setDescription(description==null? "" : description.toString());
		
		int num_subcribers = Integer.parseInt(map.get("subscribers_count").toString());
		repoBriefPO.setNum_subscribers(num_subcribers);
		
 		repoBriefPO.setNum_forks((Integer)map.get("forks_count"));
		repoBriefPO.setNum_stars((Integer)map.get("stargazers_count"));
		
		repoBriefPO.setLastUpdate(map.get("updated_at").toString());

		System.out.println(i++);
		return repoBriefPO;
	}

	public static int i = 0;
}
