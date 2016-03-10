package edu.nju.git.ui.components.table;

import javax.sound.midi.MidiDevice.Info;

import org.dom4j.Element;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.CommitVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelListener;

public class CommitTable extends MyTable {

	public CommitTable(Element element, UIController controller) {
		super(element, controller);

	}

	@Override
	protected void getData() {
		

	}
	
	public MyTableLabel createLabel(CommitVO comm){
		InfoLabel id = new InfoLabel(comm.getId());
		id.setSize(110,30);
		InfoLabel content =new InfoLabel(comm.getContents());
		content.setSize(100,30);
		InfoLabel userName = new InfoLabel(comm.getUserName());
		userName.setSize(150,30);
		InfoLabel date = new InfoLabel(comm.getDate());
		date.setSize(150,30);
		InfoLabel message = new InfoLabel(comm.getMessage())
;		java.awt.Component components[] ={id,id};
		MyTableLabel label = new MyTableLabel(element, controller, 25, components, this);
		label.addMouseListener(new LabelListener(label, controller));
		return label;
	}
	

}
