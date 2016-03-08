package edu.nju.git.data.factory.impl.itemPO;

import edu.nju.git.PO.RepoPO;
import edu.nju.git.data.api.decentralization.ItemRepoPOReader;

public class ItemRepoPOfactory extends AbstractItemPO<RepoPO>{

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
		RepoPO po = new RepoPO();
		po.setOwnerName(owner);
		po.setName(name);
		po.setLanguage(this.basicItemService.getItem("language"));
//		po.setType(this.itemService.getItem("type"));
		po.setUrl(this.basicItemService.getItem("url"));
		po.setDescription(this.basicItemService.getItem("description"));
		po.setCreate_at(this.basicItemService.getItem("create_at"));
		po.setUpdate_at(this.basicItemService.getItem("pushed_at"));
		po.setNum_forks(this.getInteger("forks"));
		po.setNum_stars(this.getInteger("stargazers_count"));
		po.setNum_subscribers( this.getInteger("subscribers_count"));
		po.setSize(this.getInteger("size"));
		return po;
	}

	
	private String owner ;
	private String name;
	public ItemRepoPOfactory(String owner,String name) {
		this.setNames(owner, name);
	}
	public void setNames(String owner,String name){
		this.owner = owner;
		this.name = name;
		this.basicItemService = new ItemRepoPOReader(owner, name);
	}
	public ItemRepoPOfactory(String fullname) {
		this.setNames(fullname);
	}
	public void setNames(String fullname){
		String[] arrayName  = fullname.split("/");
		this.owner = arrayName[0];
		this.name = arrayName[1];
		this.basicItemService = new ItemRepoPOReader(fullname);
	}
	
}
