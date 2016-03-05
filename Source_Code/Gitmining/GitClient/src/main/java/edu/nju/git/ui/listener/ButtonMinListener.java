package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.nju.git.ui.controller.UIController;
/*
 * to minimize the frame
 * @auther yyy
 */
public class ButtonMinListener extends ButtonListener {

	public ButtonMinListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		controller.getFrame().setExtendedState(JFrame.ICONIFIED);

	}

}
