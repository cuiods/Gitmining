package edu.nju.git.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.controller.UIController;
/*
 * maybe no use
 * @auther yyy
 * @date 2016-03-06 17:55; 03-07 10:54
 * to complete
 */
public class LabelRepoListener extends LabelListener{

	public LabelRepoListener(MyTableLabel label, UIController controller) {
		super(label, controller);
		
	}
	/*
	 * (non-Javadoc)
	 * @see edu.nju.git.ui.listener.LabelListener#mouseClicked(java.awt.event.MouseEvent)
	 * to complete
	 * 
	 */
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (e.getClickCount()==2){
			controller.changeTo(label.getElement().attributeValue("change"));
		}
		
	}
	

}
