package edu.nju.git.ui.listener;

import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.controller.UIController;
/*
 * maybe no use
 * @auther yyy
 * @date 2016-03-06 17:55; 03-07 10:54; 03-10 18:31
 * 
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
			String owner = ((InfoLabel)label.getComponent(5)).getText();
			String repoName = ((InfoLabel)label.getComponent(0)).getText();
			controller.setID(owner+" "+repoName);
			controller.changeTo(label.getElement().attributeValue("change"));
		}
		
	}
	

}
