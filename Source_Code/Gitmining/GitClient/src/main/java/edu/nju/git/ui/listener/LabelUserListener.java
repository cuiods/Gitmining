package edu.nju.git.ui.listener;

import java.awt.event.MouseEvent;

import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.components.table.MyTableLabel;
import edu.nju.git.ui.controller.UIController;

public class LabelUserListener extends LabelListener{

	public LabelUserListener(MyTableLabel label, UIController controller) {
		super(label, controller);
	}
	
	public void mouseClicked(MouseEvent e){
		controller.setID(((InfoLabel)label.getComponents(0)).getText());
		controller.setBefore(true);
		controller.changeTo(label.getElement().attributeValue("change"));
	}

}
