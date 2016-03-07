package edu.nju.ui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
	@FXML Button addButton;
	@FXML Button taskButton;
	private List<ScreenShot> tasks = new ArrayList<>();
	private int task = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), image);
				fadeTransition.setFromValue(1.0f);
				fadeTransition.setToValue(0f);
				fadeTransition.play();
				image.toBack();
			}
		});
	}

	@Override
	public void initPanel(Object[] bundle) {
		ScreenShot shot =  ConfigReader.readParentPanel("task_default");
		pane = (BorderPane) shot.getRoot();
		tasks.add(shot);
	}

	@Override
	public void setChildren(Region region) {
		// haven't finished.
		if (region instanceof BorderPane) {
			pane = (BorderPane) region;
		}
	}

	@Override
	public void removeChild() {
		
	}
	
	@FXML
	public void addTask(ActionEvent event) {
		initPanel(null);
		task+=1;
		box.getChildren().remove(addButton);
		Button button = new Button();
		button.setText("Task"+task);
		button.setPrefWidth(taskButton.getWidth());
		button.setPrefHeight(taskButton.getHeight());
		box.getChildren().add(button);
		box.getChildren().add(addButton);
	}
	@FXML
	public void setPanel(ActionEvent event){
		Button button = (Button) event.getSource();
		showPanel(button);
	}
	
	private void showPanel(Button button){
		String text = button.getText();
		text = text.substring(4, text.length());
		pane = (BorderPane) tasks.get(Integer.parseInt(text)-1).getRoot();
	}
	/**
	 * merge two task by their index in {@link #tasks}
	 * @param origin
	 * 			index of the origin task.
	 * @param toMerge
	 * 			index of the task to be merged.
	 */
	public void mergeTask(int origin, int toMerge){
		
	}

}
