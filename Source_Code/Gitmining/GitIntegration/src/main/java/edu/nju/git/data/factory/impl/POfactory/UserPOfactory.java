package edu.nju.git.data.factory.impl.POfactory;

import edu.nju.git.VO.UserVO;
import edu.nju.git.data.factory.service.POfactory;
import edu.nju.git.type.OwnerType;

public class UserPOfactory implements POfactory<UserVO> {

	private AbstractFieldsGetter itemHelper;
	public UserPOfactory(AbstractFieldsGetter getter) {
		this.itemHelper = getter;
	}

	/**
	 * <b>config</b>
     * <br/>id,login,type,name
     * <br/>company,blog,location,email,bio
     * <br/>public_repos,public_gists,followers,following
     * <br/>created_at,updated_at
     * <br/> login is loginname, name is full personal name
	 */
	@Override
	public UserVO getPO() {

		UserVO user = new UserVO();
		
		user.setBio(itemHelper.getString("bio"));
		user.setBlog(itemHelper.getString("blog"));
		user.setCompany(itemHelper.getString("company"));
		user.setCreate_at(itemHelper.getString("created_at"));
		user.setEmail(itemHelper.getString("email"));
		
		user.setFollowingNum(itemHelper.getInteger("following"));
		user.setFollowNum(itemHelper.getInteger("followers"));
		
		user.setLocation(itemHelper.getString("location"));
		user.setLogin(itemHelper.getString("login"));
		user.setName(itemHelper.getString("name"));
		
		user.setPublic_repos(itemHelper.getInteger("public_repos"));
		user.setPublic_gists(itemHelper.getInteger("public_gists"));
		
		user.setType(OwnerType.getInstance(itemHelper.getString("type")));
		user.setUpdate_at(itemHelper.getString("updated_at"));
		
		return user;
	}

}
