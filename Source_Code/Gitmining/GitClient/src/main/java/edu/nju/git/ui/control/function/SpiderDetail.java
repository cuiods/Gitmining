package edu.nju.git.ui.control.function;

import edu.nju.git.ui.control.FunctionPanel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * show spider detail
 * @author cuihao
 *
 */
public class SpiderDetail extends FunctionPanel{
	
	@FXML private AnchorPane Pane;

	@Override
	public String getLocationName() {
		return "Radar detail";
	}


	@Override
	public void initPanel(Object[] bundle) {
		Node chart = (Node) bundle[0];
		Pane.getChildren().add(chart);
	}
	

}
