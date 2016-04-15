package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.ui.control.GitPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
		this.setCssFactory(UIManager.instance().getCssFactory());
		if (repoBriefVO!=null) {
			name.setText(repoBriefVO.getOwner()+"/"+repoBriefVO.getName());
			name.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					String[] temp = name.getText().split("/");
					Parent root=UIManager.instance().changeFunction("function_repoDetail",new Object[]{temp[0],temp[1]});
					root.getStylesheets().add(getCssFactory().getFunctionUserDetail());
				}
			});
			description.setText(repoBriefVO.getDescription());
			//WARNING:TO BE COMPLETED
			create.setText("create: "+repoBriefVO.getCreate_at());
			update.setText("update: "+repoBriefVO.getLastUpdate());
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
