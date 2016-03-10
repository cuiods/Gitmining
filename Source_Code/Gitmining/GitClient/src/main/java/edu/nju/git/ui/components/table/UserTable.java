package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.components.MyTextField;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;
/*
 * the table of the simple user' information
 * @auther yyy
 * @date 2016-03-07 20:13
 */
public class UserTable extends MyTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<UserBriefVO> users;
	private UserController userController;

	public UserTable(Element element, UIController controller) {
		super(element, controller);
		users = new ArrayList<UserBriefVO>();
		userController = controller.getUserController();
	}

	@Override
	protected void getData() {
		userController = controller.getUserController();
		users = userController.getUsers(1,"");
		for (int i=0;i<users.size();i++){
			MyTableLabel label = createLabel(users.get(i));
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
		return label;
	}
	
	public ArrayList<MyTableLabel> getNewData(ArrayList<UserBriefVO> users){
		ArrayList<MyTableLabel> labelList = new ArrayList<MyTableLabel>();
		for(int i=0;i<users.size();i++){
			MyTableLabel label = createLabel(users.get(i));
			labelList.add(label);
		}
		return labelList;
	}

}
