package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.table.ReposTable;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
/*
 * used to jump back to browse model
 * @author yyy
 * @date 2016-03-10 16:02
 */
public class ButtonRepoBrowseListener extends ButtonListener {

	public ButtonRepoBrowseListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ReposTable table= (ReposTable)units.get(0);
		ReposController repoControl = controller.getReposController();
		ArrayList<RepoBriefVO> dataList = repoControl.getRepos(1, "");
		table.setDataList(table.getNewData(dataList));
		controller.changeTo(button.getElement().attributeValue("change"));
		controller.getFrame().repaint();

	}

}
