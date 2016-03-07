package edu.nju.git.data.api.decentralization;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.nju.git.PO.UserBriefPO;
import edu.nju.git.PO.UserPO;
import edu.nju.git.data.api.service.UserReaderService;

public class UserDecenReader implements UserReaderService{

	private String name;
	public UserDecenReader(String name) {
		this.name = name;
	}

	@Override
	public UserBriefPO getBriefPO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPO getPO() {
		UserPO po = new UserPO();
		po.setBio(this.getItem("bio").toString());
		po.setBlog("blog");
		po.setCompany("company");
		po.setCreate_at("create_at");
		return null;
	}

	private Object getItem(String item)
	{
		try {
			URL url = new URL("http://www.gitmining.net/api/user/"+this.name+"/item/"+item);
			return url.getContent();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

}
