package edu.nju.ui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	@FXML Node addChildren;
	private BorderPane childpane;
	private List<ScreenShot> tasks = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), image);
				rotateTransition.setFromAngle(0);
				rotateTransition.setToAngle(360);
				rotateTransition.play();
				
				FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), image);
				fadeTransition.setFromValue(1.0f);
				fadeTransition.setToValue(0f);
				fadeTransition.play();
				image.toBack();
				
				FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(3000),taskButton);
				fadeTransition2.setFromValue(0f);
				fadeTransition2.setToValue(0.5f);
				fadeTransition2.play();
				
				FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(3000),addButton);
				fadeTransition3.setFromValue(0f);
				fadeTransition3.setToValue(0.5f);
				fadeTransition3.play();
			}
		});
	}

	@Override
	public void initPanel(Object[] bundle) {
		ScreenShot shot =  ConfigReader.readParentPanel("task_default");
		childpane = (BorderPane) shot.getRoot();
		pane.getChildren().add(childpane);
		tasks.add(shot);
	}

	@Override
	public void setChildren(Region region) {
		if (region instanceof BorderPane) {
			BorderPane cpane = (BorderPane) region;
			pane.getChildren().remove(childpane);
			pane.getChildren().add(cpane);
			childpane = cpane;
		}
	}

	@Override
	public void removeChild() {
		
	}
	
	@FXML
	public void addTask(ActionEvent event) {
		ScreenShot shot =  ConfigReader.readParentPanel("task_default");
		tasks.add(shot);
		box.getChildren().remove(addChildren);
		Button button = new Button();
		button.setText("Task"+tasks.size());
		button.setPrefWidth(taskButton.getWidth());
		button.setPrefHeight(taskButton.getHeight());
		button.setOpacity(taskButton.getOpacity());
		button.setFont(button.getFont());
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showPanel(button);
			}
		});
		box.getChildren().add(button);
		box.getChildren().add(addChildren);
	}
	@FXML
	public void setPanel(ActionEvent event){
		Button button = (Button) event.getSource();
		showPanel(button);
	}
	
	private void showPanel(Button button){
		String text = button.getText();
		text = text.substring(4, text.length());
		BorderPane cpane = (BorderPane) tasks.get(Integer.parseInt(text)-1).getRoot();
		pane.getChildren().remove(childpane);
		pane.getChildren().add(cpane);
		childpane = cpane;
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
