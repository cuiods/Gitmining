package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.type.SortType;
import edu.nju.git.ui.components.MainButton;
import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.components.table.ReposTable;
import edu.nju.git.ui.components.table.UserTable;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;
/*
 * used to sort the data in table
 * @author yyy
 * @date 2016-03-08 18:25
 */
public class ButtonSortListener extends ButtonListener {
	
	public ButtonSortListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);

	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//according to different requirement ,change the order in table
		String path = button.getPicPath();
		
		if(path.contains("USER")){
			UserTable userTable =(UserTable)(units.get(0));
			ArrayList<UserBriefVO> userList = new ArrayList<UserBriefVO>();
			ArrayList<MyTableLabel> labelList = new ArrayList<MyTableLabel>();
			UserController userControl = controller.getUserController();
			if(path.contains("name1")){
				userList = userControl.sort(SortType.USER_NAME, true);
			}else if(path.contains("name2")){
				userList = userControl.sort(SortType.USER_NAME, false);
			}else if(path.contains("follower1")){
				userList = userControl.sort(SortType.FOLLOWER_NUM, true);
			}else if(path.contains("follower2")){
				userList = userControl.sort(SortType.FOLLOWER_NUM, false);
			}else if(path.contains("repo1")){
				userList = userControl.sort(SortType.REPO_NUM, true);
			}else if(path.contains("repo2")){
				userList = userControl.sort(SortType.REPO_NUM, false);
			}
			for (int i=0;i<userList.size();i++){
				MyTableLabel label = userTable.createLabel(userList.get(i));
				labelList.add(label);
			}
			userTable.setDataList(labelList);
			
			//to change the button performance
			
			for(int i=1;i<=3;i++){
				MainButton head = (MainButton)units.get(i);
				head.setIsBack(true);
				
			}
		
		}else if(path.contains("REPOS")){
			ReposTable repoTable = (ReposTable)units.get(0);
			ArrayList<RepoBriefVO> repoList = new ArrayList<RepoBriefVO>();
			ArrayList<MyTableLabel> labelList = new ArrayList<MyTableLabel>();
			ReposController repoController = controller.getReposController();
			if(path.contains("name1")){
				repoList = repoController.sort(SortType.REPO_NAME, true);
			}else if(path.contains("name2")){
				repoList = repoController.sort(SortType.REPO_NAME, false);
			}else if(path.contains("star1")){
				repoList = repoController.sort(SortType.STAR_NUM, true);
			}else if(path.contains("star2")){
				repoList = repoController.sort(SortType.STAR_NUM, false);
			}else if(path.contains("fork1")){
				repoList = repoController.sort(SortType.FORK_NUM, true);
			}else if(path.contains("fork2")){
				repoList = repoController.sort(SortType.FORK_NUM, false);
			}else if(path.contains("subscr1")){
				repoList = repoController.sort(SortType.SUBSCR_NUM, true);
			}else if(path.contains("subscr2")){
				repoList = repoController.sort(SortType.SUBSCR_NUM, false);
			}else if(path.contains("update1")){
				repoList = repoController.sort(SortType.UPDATE_TIME, true);
			}else if(path.contains("update2")){
				repoList = repoController.sort(SortType.UPDATE_TIME, false);
			}
			for(int i=0;i<repoList.size();i++){
				MyTableLabel label = repoTable.createLabel(repoList.get(i));
				labelList.add(label);
			}
			repoTable.setDataList(labelList);
			for(int i=1;i<=5;i++){
				MainButton head =(MainButton)units.get(i);
				head.setIsBack(true);
			}
			
		}
		
		button.setIsBack(false);
		controller.getFrame().repaint();
		
	}

	
}
