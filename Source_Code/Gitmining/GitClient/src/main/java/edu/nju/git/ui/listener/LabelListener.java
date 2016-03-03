package edu.nju.git.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.nju.git.ui.components.MyLabel;
import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.frame.MainPanel;

/**
 * @author tj
 * @date 2015年12月11日
 */
public class LabelListener implements MouseListener {
	private MyTableLabel label;
	private UIController controller;
	public LabelListener(MyTableLabel label, UIController controller) {
		this.label = label;
		this.controller = controller;
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
