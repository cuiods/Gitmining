package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.ui.controller.UIController;
/*
 * exit the program
 * @auther yyy
 * 
 */
public class ButtonExitListener extends ButtonListener {
	
	public ButtonExitListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.exit(0);

	}

}
