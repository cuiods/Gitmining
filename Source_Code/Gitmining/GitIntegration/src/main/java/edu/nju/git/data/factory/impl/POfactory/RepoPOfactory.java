package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.data.factory.service.POfactory;

public class RepoPOfactory implements POfactory<RepoPO> {

	private AbstractFieldsGetter itemHelper;
	

	public RepoPOfactory(AbstractFieldsGetter itemHelper) {
		super();
		this.itemHelper = itemHelper;
	}


	/**
	 * <p/>http://www.gitmining.net/api/api/repository/{owner}/{reponame}/item/{item}
	 * <br/>item可接受的参数有：
	 * <br/>owner_name:项目所有者登录名 
	 * <br/>owner_id:所有者的id 
	 * <br/>owner_type:所有者的用户类型（分为User和Organization）
	 * <br/>html_url:项目主页
	 * <br/>url description:项目描述 
	 * <br/>fork:是否是fork了他人项目所产生的项目 
     * <br/>created_at:创建时间 
     * <br/>updated_at:更新时间 
     * <br/>pushed_at:最后一次push的时间
     * <br/>size:项目大小 
     * <br/>stargazers_count:点赞人数 
     * <br/>language:项目主语言 
     * <br/>forks:被fork的次数 
     * <br/>open_issues:open的issue数 
     * <br/>subscribers_count:关注者数量
	 */
	@Override
	public RepoPO getPO() {
		RepoPO repoBriefPO = new RepoPO();
		
		String fullname[] = itemHelper.getString("full_name").split("/");
		repoBriefPO.setOwnerName(fullname[0]);
		repoBriefPO.setName(fullname[1]);
		
		Object description = itemHelper.getString("description");
		repoBriefPO.setDescription(description==null? "" : description.toString());
		
		int num_subcribers = itemHelper.getInteger("subscribers_count");
		repoBriefPO.setNum_subscribers(num_subcribers);
		
 		repoBriefPO.setNum_forks(itemHelper.getInteger("forks_count"));
		repoBriefPO.setNum_stars(itemHelper.getInteger("stargazers_count"));
		
		repoBriefPO.setUpdate_at(itemHelper.getString("updated_at"));

		return repoBriefPO;
	}

}
