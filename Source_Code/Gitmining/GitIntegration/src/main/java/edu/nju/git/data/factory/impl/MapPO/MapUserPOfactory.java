package edu.nju.git.data.factory.impl.MapPO;

import edu.nju.git.PO.UserPO;
import edu.nju.git.data.api.centralization.UserMapReader;
import edu.nju.git.type.OwnerType;

public class MapUserPOfactory  extends AbstractMapPO<UserPO> {

	  public MapUserPOfactory() {}
		
	  public MapUserPOfactory(String name){    this.mapService = new UserMapReader(name); }
	    
	  public void setName(String name){  this.mapService = new UserMapReader(name); }

	/**
	 * id,login,type,name
     * company,blog,location,email,bio
     * public_repos,public_gists,followers,following
     * created_at,updated_at
	 */
	@Override
	public UserPO getPO() {
		this.map = this.mapService.getMap();
		if(map==null){
			return null;
		}
		UserPO po = new UserPO();
		
		po.setBio(this.getString("bio"));
		po.setBlog(this.getString("blog"));
		po.setCompany(this.getString("company"));
		po.setCreate_at(this.getString("created_at"));
		po.setEmail(this.getString("email"));
		
		po.setFollowingNum(this.getInteger("following"));
		po.setFollowNum(this.getInteger("followers"));
		
		po.setLocation(this.getString("location"));
		po.setLogin(this.getString("login"));
		po.setName(this.getString("name"));
		
		po.setPublic_repos(this.getInteger("public_repos"));
		po.setPublicGists(this.getInteger("public_gists"));
		
		po.setType(OwnerType.getInstance(this.getString("type")));
		po.setUpdate_at(this.getString("updated_at"));
		
		return po;
	}


}
