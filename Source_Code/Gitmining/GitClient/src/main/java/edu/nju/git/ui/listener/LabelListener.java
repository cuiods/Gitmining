package edu.nju.git.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.nju.git.ui.components.MyLabel;
import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.frame.MainPanel;

/**
 * listener observed by label in the reposTable or userTable
 * @author yyy
 * @date 2016-03-08 10:35
 */
public class LabelListener implements MouseListener {
	protected MyTableLabel label;
	protected UIController controller;
	private String picPath;
	public LabelListener(MyTableLabel label, UIController controller) {
		this.label = label;
		this.controller = controller;
		picPath = label.getPicPath();
	}
	public void setPicPath(String picPath){
		this.picPath = picPath;
	}
	/*
	 * double clicked will start the new event 
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		label.setPicPath("src/main/resources/pictures/label_clicked.png");
		label.repaint();
//		if (e.getClickCount()==2){
//			controller.changeTo(label.getElement().attributeValue("change"));
//		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		label.setPicPath("src/main/resources/pictures/label_clicked.png");
		label.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		label.setPicPath("src/main/resources/pictures/label_clicked.png");
		label.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		label.setPicPath("src/main/resources/pictures/changeIn.png");
		label.repaint();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		if(MyTableLabel.pathBit==1)
//			label.setPicPath("src/main/resources/pictures/grey.png");
//		else
//			label.setPicPath("src/main/resources/pictures/white.png");
		label.setPicPath(picPath);
		label.repaint();
		
	}
}
