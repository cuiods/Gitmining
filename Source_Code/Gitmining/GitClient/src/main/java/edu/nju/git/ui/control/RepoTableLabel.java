package edu.nju.git.ui.control;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.VO.RepoBriefVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * One single item in the repository table.<br>
 * @author cuihao
 * @date 2016-03-22 19:09:13
 */
public class RepoTableLabel extends GitPanel{
	private RepoBriefVO repoBriefVO;
	@FXML private Hyperlink name;
	@FXML private Label description;
	@FXML private Label create;
	@FXML private Label update;
	@FXML private Label star;
	@FXML private Label fork;
	@FXML private Label contributor;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	/**
	 * set data of the single repository item in the table
	 * @param repoBriefVO
	 */
	public void setRepoBriefVO(RepoBriefVO repoBriefVO){
		this.repoBriefVO = repoBriefVO;
		initialize();
	}
	private void initialize(){
		if (repoBriefVO!=null) {
			name.setText(repoBriefVO.getOwner()+"/"+repoBriefVO.getOwner());
			description.setText(repoBriefVO.getDescription());
			//WARNING:TO BE COMPLETED
			create.setText("create:");
			update.setText("update:"+repoBriefVO.getLastUpdate());
			star.setText(repoBriefVO.getNum_stars()+"");
			fork.setText(repoBriefVO.getNum_forks()+"");
			contributor.setText(repoBriefVO.getNum_subscribers()+"");
		}
	}
	@Override
	public void initPanel(Object[] bundle) {
	}
	@Override
	public void setChildren(Parent region) {
	}
	@Override
	public void removeChild() {
	}
}
