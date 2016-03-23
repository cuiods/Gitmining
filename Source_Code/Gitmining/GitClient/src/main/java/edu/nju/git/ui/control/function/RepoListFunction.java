package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.VO.RepoBriefVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.RepoBlService;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.control.GitPanel;
import edu.nju.git.ui.control.RepoTableLabel;
import edu.nju.git.ui.control.UIManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class RepoListFunction extends GitPanel{

	@FXML private VBox listBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		RepoBlService service = BlFactory.instance().getRepoBlService();
		List<RepoBriefVO> list = service.getShownRepoList();
		if (list==null) {
			return;
		}
		for(Iterator<RepoBriefVO> iterator = list.iterator(); iterator.hasNext();){
			RepoBriefVO repoBriefVO = iterator.next();
			ScreenShot pane = ConfigReader.readParentPanel("RepotableLabel");
			RepoTableLabel label = (RepoTableLabel) pane.getPanel();
			label.setRepoBriefVO(repoBriefVO);
			listBox.getChildren().add(pane.getRoot());
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
