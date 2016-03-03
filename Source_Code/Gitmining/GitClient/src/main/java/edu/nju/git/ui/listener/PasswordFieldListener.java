package edu.nju.git.ui.listener;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import edu.nju.git.ui.components.MyPasswordField;

/**
 *@author tj
 *@date 2015年12月9日
 */
public class PasswordFieldListener implements FocusListener {
	protected MyPasswordField field;
	public PasswordFieldListener(Component button) {
		this.field = (MyPasswordField)button;
	}
	public void focusGained(FocusEvent e) {
		field.setIn(true);
		field.repaint();
	}
	public void focusLost(FocusEvent e) {
		field.setIn(false);
		field.repaint();
	}

}
