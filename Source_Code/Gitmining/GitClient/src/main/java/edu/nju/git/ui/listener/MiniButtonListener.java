package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.nju.git.ui.controller.UIController;

/**
 *@author tj
 *@date 2015年11月24日
 */
public class MiniButtonListener extends ButtonListener {

	public MiniButtonListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		// TODO Auto-generated constructor stub
	}
	public void mouseReleased(MouseEvent e) {
		controller.getFrame().setExtendedState(JFrame.ICONIFIED);
	}
}
