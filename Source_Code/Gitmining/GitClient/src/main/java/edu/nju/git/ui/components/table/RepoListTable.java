package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.InfoLabel;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
import edu.nju.git.ui.controller.UserController;
import edu.nju.git.ui.listener.LabelListener;

public class RepoListTable extends MyTable{

	private static final long serialVersionUID = 1L;
	private ArrayList<RepoBriefVO> lists;
	private UserController userController;
	private ReposController repoController;
	public RepoListTable(Element element, UIController controller) {
		super(element, controller);
		lists = new ArrayList<RepoBriefVO>();
	}

	@Override
	protected void getData() {
		userController = controller.getUserController();
		if (element.attributeValue("name").contains("repos")) {
			lists = userController.getOwnRepos(controller.getID());
		}else if(element.attributeValue("name").contains("fork")){
			repoController = controller.getReposController();
			String[] or = controller.getID().split(" ");
			lists = repoController.getFork(or[0], or[1]);
		}else {
			lists = userController.getContributeRepos(controller.getID());
		}
		for (int i=0;i<lists.size();i++){
			MyTableLabel label = createLabel(lists.get(i));
			addData(label);
		}
	}
	
	private MyTableLabel createLabel(RepoBriefVO repoBriefVO){
		InfoLabel name = new InfoLabel(repoBriefVO.getName());
		name.setSize(250,30);
		java.awt.Component[] components = {name};
		MyTableLabel label = new MyTableLabel(element, controller, 30, components, this);
		label.addMouseListener(new LabelListener(label, controller));
		return label;
	}

}
