package edu.nju.git.data.factory.impl;

import java.net.MalformedURLException;
import java.util.Map;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.api.NodeCounter;
import edu.nju.git.data.factory.service.POfactory;

public class RepoBriefPOfactory implements POfactory<RepoBriefPO> {


	@Override
	public RepoBriefPO getPO(Map<String, Object> map) {
		RepoBriefPO repoBriefPO = new RepoBriefPO();
		int num_contributors = 0;
		try {
			NodeCounter nodeCounter = new NodeCounter(map.get("contributors_url").toString());
			num_contributors = nodeCounter.count();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}repoBriefPO.setNum_contributors(num_contributors);
		
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
