package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.TextLabel;
import edu.nju.git.ui.components.table.ReposTable;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
/*
 * listener for lastPage button of repos table
 */
public class ButtonReposLastListener extends ButtonListener {

	public ButtonReposLastListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ReposTable table = (ReposTable)units.get(0);
		ReposController repoControl = controller.getReposController();
		int nowPage = repoControl.getCurrentPage();
		if(nowPage>1){
			ArrayList<RepoBriefVO> lastPage = repoControl.jumpPage(nowPage-1);
			table.setDataList(table.getNewData(lastPage));
			TextLabel pageNum = (TextLabel)units.get(10);
			pageNum.setPage(nowPage-1);
		}
		if(nowPage==2){
			button.setEnabled(false);//to deal with
		}
		
		controller.getFrame().repaint();


	}

}
