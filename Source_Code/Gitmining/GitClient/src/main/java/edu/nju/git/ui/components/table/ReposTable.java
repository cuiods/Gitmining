package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.MyTextField;
import edu.nju.git.ui.controller.ReposController;
import edu.nju.git.ui.controller.UIController;
/*
 * the table of  simple repositories' information
 * @auther yyy
 * 
 */
public class ReposTable extends MyTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<RepoBriefVO> repos;
	private ReposController repoController;
	public ReposTable(Element element, UIController controller) {
		super(element, controller);
		repos = new ArrayList<RepoBriefVO>();
		repoController = controller.getReposController();
		
	}

	@Override
	protected void getData() {
		repoController = controller.getReposController();
		repos = repoController.getRepos(1, "");//get repos with default order
		for (int i=0; i<repos.size();i++){
			MyTableLabel label = createLabel(repos.get(i));
			addData(label);
		}

	}
	
	public MyTableLabel createLabel(RepoBriefVO repo){
		MyTextField reposName = new MyTextField(repo.getName());
		reposName.setSize(160,30);
		MyTextField descrip = new MyTextField(repo.getDescription());
		descrip.setSize(200,30);
		MyTextField owner = new MyTextField(repo.getOwner());
		owner.setSize(150,30);
		MyTextField update = new MyTextField(repo.getLastUpdate());
		update.setSize(150,30);
		MyTextField starNum = new MyTextField(""+repo.getNum_stars());
		starNum.setSize(80,30);
		MyTextField forkNum = new MyTextField(""+repo.getNum_forks());
		forkNum.setSize(80,30);
		MyTextField subscribeNum = new MyTextField(""+repo.getNum_contributors());
		subscribeNum.setSize(150,30);
		java.awt.Component components[] = {reposName,descrip,update,starNum,forkNum,subscribeNum};
		MyTableLabel label = new MyTableLabel(element,controller,35,components,this);
		return label;
	}

}
