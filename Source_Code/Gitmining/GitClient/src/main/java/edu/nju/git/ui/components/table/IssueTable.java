package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.BranchVO;
import edu.nju.git.VO.IssueVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.listener.LabelListener;
/*
 * @author yyy
 * @date 2015-03-10 23:24
 */
public class IssueTable extends MyTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<IssueVO> issues;
	private ReposController repoController;
	public IssueTable(Element element, UIController controller) {
		super(element, controller);
		issues = new ArrayList<IssueVO>();
		repoController = controller.getReposController();
	}

	@Override
	protected void getData() {
		repoController = controller.getReposController();
		String[] or = controller.getID().split(" ");
		issues = repoController.getIssues(or[0], or[1]);
		for(int i=0;i<issues.size();i++){
			MyTableLabel label = createLabel(issues.get(i));
			addData(label);
		}
	}
	
	public MyTableLabel createLabel(IssueVO iss){
		InfoLabel id = new InfoLabel(iss.getId());
		id.setSize(80,25);
		InfoLabel state = new InfoLabel(iss.getState());
		state.setSize(50,25);
		InfoLabel lock = new InfoLabel((iss.isLocked())+"");
		lock.setSize(50,25);
		InfoLabel title = new InfoLabel(iss.getTitle());
		title.setSize(100,25);
		InfoLabel body = new InfoLabel(iss.getBody());
		body.setSize(130,25);
		InfoLabel userName = new InfoLabel(iss.getUserName());
		userName.setSize(100,25);
		InfoLabel create = new InfoLabel(iss.getCreate_at());
		create.setSize(105,25);
		InfoLabel update = new InfoLabel(iss.getUpdate_at());
		update.setSize(105,25);
		java.awt.Component[] components = {id,state,lock,title,body,userName,create,update};
		MyTableLabel label = new MyTableLabel(element, controller, 25, components, this);
		label.addMouseListener(new LabelListener(label,controller));
		return label;
	}

}
