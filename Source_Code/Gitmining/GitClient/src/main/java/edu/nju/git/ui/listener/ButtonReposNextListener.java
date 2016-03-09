package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.components.table.ReposTable;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
/*
 * listener for nextPage button of repos table
 * @author yyy
 * @date 2016-03-09 17:14
 */
public class ButtonReposNextListener extends ButtonListener {

	public ButtonReposNextListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ReposTable table = (ReposTable)units.get(0);
		ReposController repoControl = controller.getReposController();
		int nowPage = repoControl.getCurrentPage();
		ArrayList<RepoBriefVO> nextPage = repoControl.jumpPage(nowPage+1);
		table.setDataList(table.getNewData(nextPage));
		controller.getFrame().repaint();

	}

}
