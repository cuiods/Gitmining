package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.ui.components.table.MyTable;
import edu.nju.git.ui.controller.UIController;
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
		String path = button.getPicPath();
		MyTable table = (MyTable)units.get(0);
		if(path.contains("1")){
			
			
		}
		
	}

	
}
