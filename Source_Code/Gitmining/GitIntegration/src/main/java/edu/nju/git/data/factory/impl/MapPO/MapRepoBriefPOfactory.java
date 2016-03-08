package edu.nju.git.data.factory.impl.MapPO;

import java.net.MalformedURLException;
import java.util.Map;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.centralization.NodeCounter;
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
		Map<String, Object> map = this.mapService.getMap();
		RepoBriefPO repoBriefPO = new RepoBriefPO();
		int num_contributors = 0;
		try {
			NodeCounter nodeCounter = new NodeCounter(map.get("contributors_url").toString());
			num_contributors = nodeCounter.count();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		repoBriefPO.setNum_contributors(num_contributors);
		repoBriefPO.setDescription(map.get("description").toString());
		repoBriefPO.setName(map.get("name").toString());

		repoBriefPO.setNum_forks((Integer)map.get("forks_count"));
		repoBriefPO.setNum_stars((Integer)map.get("stargazers_count"));
	//	repoBriefPO.setOwer(map.get("owner").toString());
	//	repoBriefPO.setUrl(map.get("html_url").toString());
		// "html_url": "https://github.com/huerlisi/CyDoc",
		// "url": "https://api.github.com/repos/huerlisi/CyDoc",
		return repoBriefPO;
	}

}
