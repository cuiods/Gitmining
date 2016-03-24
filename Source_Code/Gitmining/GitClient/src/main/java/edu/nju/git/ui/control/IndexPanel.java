package edu.nju.git.ui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.css.CSSFactory;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * <p>Root panel of the application.</p>
 * <p>This is likely a home page of this application.User can add a task {@link TaskPanel}.<br>
 * In every task, user can use different functions.{@link FunctionPanel}. Tasks can be merged<br>
 * so that user can compare the data.</p>
 * @author cuihao
 * @date 2016-03-04 23:06:32
 *
 */
public class IndexPanel extends GitPanel{
	@FXML HBox box;
	@FXML BorderPane pane;
	@FXML ImageView image;
	@FXML Button exit;
	private BorderPane childpane;
	private List<ScreenShot> tasks = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setCssFactory(UIManager.instance().getCssFactory());
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), image);
				fadeTransition.setFromValue(1.0f);
				fadeTransition.setToValue(0f);
				fadeTransition.play();
				
				TranslateTransition transition = new TranslateTransition(Duration.millis(800), image);
				transition.setToY(-870);
				transition.play();
			}
		});
		
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});
	}

	@Override
	public void initPanel(Object[] bundle) {
		ScreenShot shot =  ConfigReader.readParentPanel("task_default");
		childpane = (BorderPane) shot.getRoot();
		childpane.getStylesheets().add(getCssFactory().getTaskCSS());
		shot.getPanel().initPanel(null);
		pane.getChildren().add(childpane);
		tasks.add(shot);
		
	}

	@Override
	public void setChildren(Parent region) {
//		if (region instanceof BorderPane) {
//			BorderPane cpane = (BorderPane) region;
//			pane.getChildren().remove(childpane);
//			pane.getChildren().add(cpane);
//			childpane = cpane;
//		}
	}
	
	public void startBusy(){
		
	}

	@Override
	public void removeChild() {
		
	}

}
