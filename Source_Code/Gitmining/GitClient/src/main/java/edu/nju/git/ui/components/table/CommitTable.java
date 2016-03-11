package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.CommitVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelListener;

public class CommitTable extends MyTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CommitVO> commits;
	private ReposController repoController;
	public CommitTable(Element element, UIController controller) {
		super(element, controller);
		commits =new ArrayList<CommitVO>();
		repoController = controller.getReposController();
	}

	@Override
	protected void getData() {
		repoController=controller.getReposController();
		String[] or = controller.getID().split(" ");
		commits = repoController.getCommits(or[0], or[1]);
		for(int i=0;i<commits.size();i++){
			MyTableLabel label = createLabel(commits.get(i));
			addData(label);
		}

	}
	
	public MyTableLabel createLabel(CommitVO comm){
		InfoLabel id = new InfoLabel(comm.getId());
		id.setSize(100,25);
		InfoLabel userName = new InfoLabel(comm.getUserName());
		userName.setSize(100,25);
		InfoLabel date = new InfoLabel(comm.getDate());
		date.setSize(130,25);
		InfoLabel message = new InfoLabel(comm.getMessage());
		message.setSize(140,30);
;		java.awt.Component components[] ={id,userName,date,message};
		MyTableLabel label = new MyTableLabel(element, controller, 25, components, this);
		label.addMouseListener(new LabelListener(label, controller));
		return label;
	}
	

}
