package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelUserListener;
/*
 * @author yyy
 * @date 2016-03-10 23:59
 */
public class UserListTable extends MyTable {
	ArrayList<UserBriefVO> userList;
	ReposController repoControl;
	public UserListTable(Element element, UIController controller) {
		super(element, controller);
		userList = new ArrayList<UserBriefVO>();
		repoControl = controller.getReposController();
	}

	@Override
	protected void getData() {
		repoControl = controller.getReposController();
		String[] or = repoControl.getOwnerRepo().split(" ");
		String name = element.attributeValue("name");
		if(name.contains("collaborator")){
			userList = repoControl.getCollaborators(or[0], or[1]);
		}else if(name.contains("subscriber")){
			userList = repoControl.getSubscriber(or[0], or[1]);
		}else{
			userList = repoControl.getLaudator(or[0], or[1]);
		}
		for (int i=0;i<userList.size();i++){
			MyTableLabel label = createLabel(userList.get(i));
			addData(label);
		}

	}
	
	public MyTableLabel createLabel(UserBriefVO user){
		InfoLabel login = new InfoLabel(user.getLogin());
		login.setSize(160,30);
		InfoLabel followers = new InfoLabel(""+user.getFollowers());
		followers.setSize(160,30);
		InfoLabel publicRepos = new InfoLabel(""+user.getPublic_repos());
		publicRepos.setSize(160,30);
		java.awt.Component[] components = {login,followers,publicRepos};
		MyTableLabel label = new MyTableLabel(element, controller, 34, components, this);
		label.addMouseListener(new LabelUserListener(label, controller));
		return label;
	}

}
