package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.ui.components.table.UserTable;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;

public class ButtonUserBrowseListener extends ButtonListener {

	public ButtonUserBrowseListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UserTable table = (UserTable)units.get(0);
		UserController userControl = controller.getUserController();
		ArrayList<UserBriefVO> list = userControl.getUsers(1, "");
		table.setDataList(table.getNewData(list));
		controller.changeTo(button.getElement().attributeValue("change"));
		controller.getFrame().repaint();

	}

}
