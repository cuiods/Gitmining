package edu.nju.git.ui.components.table;

import java.util.ArrayList;

import org.dom4j.Element;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.components.InfoLabel;
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
		InfoLabel reposName = new InfoLabel(repo.getName());
		reposName.setSize(160,30);
//		reposName.setOpaque(true);
		InfoLabel starNum = new InfoLabel(""+repo.getNum_stars());
		starNum.setSize(90,30);
		InfoLabel forkNum = new InfoLabel(""+repo.getNum_forks());
		forkNum.setSize(90,30);
		InfoLabel subscribeNum = new InfoLabel(""+repo.getNum_subscribers());
		subscribeNum.setSize(100,30);
		InfoLabel update = new InfoLabel(repo.getLastUpdate());
		update.setSize(160,30);
		InfoLabel owner = new InfoLabel(repo.getOwner());
		owner.setSize(150,30);
		InfoLabel descrip = new InfoLabel(repo.getDescription());
		descrip.setSize(220,30);
		java.awt.Component components[] = {reposName,starNum,forkNum,subscribeNum,update,owner,descrip};
		MyTableLabel label = new MyTableLabel(element,controller,35,components,this);
		return label;
	}
	
	public ArrayList<MyTableLabel> getNewData(ArrayList<RepoBriefVO> repos){
		ArrayList<MyTableLabel> labelList = new ArrayList<MyTableLabel>();
		for(int i=0;i<repos.size();i++){
			MyTableLabel label = createLabel(repos.get(i));
			labelList.add(label);
		}
		return labelList;
	}

}
