package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.RepoBriefPO;
import edu.nju.git.data.factory.impl.MapPO.MapRepoBriefPOfactory;
import edu.nju.git.data.factory.impl.itemPO.ItemRepoBriefPOfactory;
import edu.nju.git.data.factory.service.POfactory;

public class RepoBriefPOfactory implements POfactory<RepoBriefPO> {

	private AbstractPOfactory itemHelper;
	public RepoBriefPOfactory(String owner,String name) {
		itemHelper = new MapRepoBriefPOfactory(owner, name);
		itemHelper = new ItemRepoBriefPOfactory(owner,name);
	}

	public RepoBriefPOfactory() {
		itemHelper = new MapRepoBriefPOfactory();
		itemHelper = new ItemRepoBriefPOfactory();
	}
	
	public void setNames(String owner,String name)
	{
		itemHelper = new MapRepoBriefPOfactory(owner, name);
		itemHelper = new ItemRepoBriefPOfactory(owner,name);
	}
	public void setNames(String fullname) {
		String[] names = fullname.split("/");
		this.setNames(names[0],names[1]);
	}
	/**
	 * <br/><b>precondition</b>：this.owner this.repo (map)must be set
	 * <br/><b>postcondition</b>：return a PO
	 * @return 
	 * @date 2016-03-06
	 */
	@Override
	public RepoBriefPO getPO() {

		RepoBriefPO repoBriefPO = new RepoBriefPO();
		
		String fullname[] = itemHelper.getString("full_name").split("/");
		repoBriefPO.setOwner(fullname[0]);
		repoBriefPO.setName(fullname[1]);
		
		Object description = itemHelper.getString("description");
		repoBriefPO.setDescription(description==null? "" : description.toString());
		
		int num_subcribers = itemHelper.getInteger("subscribers_count");
		repoBriefPO.setNum_subscribers(num_subcribers);
		
 		repoBriefPO.setNum_forks(itemHelper.getInteger("forks_count"));
		repoBriefPO.setNum_stars(itemHelper.getInteger("stargazers_count"));
		
		repoBriefPO.setLastUpdate(itemHelper.getString("updated_at"));

		return repoBriefPO;
	}

}
