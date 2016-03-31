package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.ui.chart.ChartType;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * RepoStatistic panel controller <br>
 * Including buttons to other statistic panel
 * @author cuihao
 *
 */
public class RepoStatisticFunction extends FunctionPanel{
	
	@FXML private AnchorPane language;
	
	@Override
	public String getLocationName() {
		return "RepoStatistic";
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initLanguage();
	}

	@Override
	public void initPanel(Object[] bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildren(Parent region) {
		// TODO Auto-generated method stub
		
	}
	
	private void initLanguage() {
		CubeButton languageButton = new CubeButton();
		language.getChildren().add(languageButton.createContent(1.6, Color.RED));
		language.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				languageButton.play();
			}
		});
		language.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				languageButton.stop();
			}
		});
		language.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic", new Object[]{ChartType.RepoLanguage});
			}
		});
	}

}
