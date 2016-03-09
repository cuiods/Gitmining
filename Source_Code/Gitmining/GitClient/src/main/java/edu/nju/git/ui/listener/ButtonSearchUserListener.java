package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.ui.components.MyTextField;
import edu.nju.git.ui.components.table.UserTable;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;
/*
 * listener for search button of user table
 */
public class ButtonSearchUserListener extends ButtonListener {

	public ButtonSearchUserListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UserTable table =(UserTable)units.get(0);
		UserController userControl = controller.getUserController();
		MyTextField text = (MyTextField)units.get(4);
		String keyword = text.getText();
		ArrayList<UserBriefVO> list = userControl.getUsers(1, keyword);
		table.setDataList(table.getNewData(list));
		//may need to do something else
		controller.getFrame().repaint();

	}

}
