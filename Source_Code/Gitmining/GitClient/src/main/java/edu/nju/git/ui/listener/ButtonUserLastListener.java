package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.VO.UserBriefVO;
import edu.nju.git.ui.components.TextLabel;
import edu.nju.git.ui.components.table.UserTable;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;
/*
 * listener for lastPage button of users table
 * @author yyy
 * @date 2016-03-08 17:39
 */
public class ButtonUserLastListener extends ButtonListener {

	public ButtonUserLastListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UserTable table = (UserTable)units.get(0);
		UserController userControl = controller.getUserController();
		int nowPage = userControl.getCurrentPage();
		if(nowPage>1){
			ArrayList<UserBriefVO> lastPage = userControl.jumpPage(nowPage-1);
			table.setDataList(table.getNewData(lastPage));
			TextLabel pageNum = (TextLabel)units.get(6);
			pageNum.setPage(nowPage-1);
		}
		if(nowPage==2){
			button.setEnabled(false);//to deal with
		}
		controller.getFrame().repaint();

	}

}
