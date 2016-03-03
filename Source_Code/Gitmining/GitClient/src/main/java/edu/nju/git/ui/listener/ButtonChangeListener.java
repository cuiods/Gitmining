package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import edu.nju.git.ui.controller.UIController;



/**
 * Change to a new panel
 * @author cuihao
 * @see UIController
 */
public class ButtonChangeListener extends ButtonListener{

	public ButtonChangeListener(ArrayList<Component> units, UIController controller, Component button) {
		super(units, controller, button);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		controller.changeTo(button.getElement().attributeValue("change"));
	}

}
