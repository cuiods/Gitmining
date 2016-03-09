package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.MyTextField;
import edu.nju.git.ui.components.table.ReposTable;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
/*
 * to show the result of search the repo table
 * @auther yyy
 * @date 2016-03-08 17:54
 */
public class ButtonSearchRepoListener extends ButtonListener {

	public ButtonSearchRepoListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ReposTable table = (ReposTable)units.get(0);
		MyTextField text = (MyTextField)units.get(8);
		String keyword = text.getText();
		ReposController repoControl = controller.getReposController();
		ArrayList<RepoBriefVO> list = repoControl.getRepos(1, keyword);
		table.setDataList(table.getNewData(list));
		//may have to do someting else
		controller.getFrame().repaint();

	}

}
