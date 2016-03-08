package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.ui.controller.UIController;
/*
 * to show the result of search
 * @auther yyy
 * to complete
 */
public class ButtonSearchListener extends ButtonListener {

	public ButtonSearchListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button.setPressed(true);
		controller.getFrame().repaint();

	}

}
