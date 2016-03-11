package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelListener;

public class BranchTable extends MyTable {
	private ArrayList<BranchVO> branches;
	private ReposController repoController;
	public BranchTable(Element element, UIController controller) {
		super(element, controller);
		branches = new ArrayList<BranchVO>();
		repoController = controller.getReposController();
	}

	@Override
	protected void getData() {
		repoController = controller.getReposController();
		String[] oR =new String[2];
		oR = repoController.getOwnerRepo().split(" ");
		branches = repoController.getBranch(oR[0], oR[1]);//get repos with default order
		for (int i=0; i<branches.size();i++){
			MyTableLabel label = createLabel(branches.get(i));
			addData(label);
		}

	}
	
	public MyTableLabel createLabel(BranchVO bran){
		InfoLabel name = new InfoLabel(bran.getName());
		name.setSize(120,30);
		InfoLabel sha = new InfoLabel(bran.getSHA());
		sha.setSize(160,30);
		java.awt.Component components[] ={name,sha};
		MyTableLabel label = new MyTableLabel(element, controller, 25, components, this);
		label.addMouseListener(new LabelListener(label, controller));
		return label;
	}

}
