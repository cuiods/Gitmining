package edu.nju.git.ui.control.function;


import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.ui.control.FunctionPanel;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/*
 * userStatistic function
 * @author yyy
 */
public class UserStatisticFunction extends FunctionPanel{
	
	
	@FXML private AnchorPane userType;
	@FXML private AnchorPane companyUser;
	@FXML private AnchorPane email;
	@FXML private AnchorPane create;
	@FXML private AnchorPane ownRepos;
	@FXML private AnchorPane gist;
	@FXML private AnchorPane followers;
	@FXML private AnchorPane subscribe;	
	@FXML private AnchorPane collaborate;
	@FXML private AnchorPane contribute;
	
	@FXML private Label userType_label;
	@FXML private Label companyUser_label;
	@FXML private Label email_label;
	@FXML private Label create_label;
	@FXML private Label ownRepos_label;
	@FXML private Label gist_label;
	@FXML private Label followers_label;
	@FXML private Label subscribe_label;	
	@FXML private Label collaborate_label;
	@FXML private Label contribute_label;
	
	
	
	@Override
	public String getLocationName() {
		return "UserStatistic";
	}
	
	public void initialize(URL location,ResourceBundle resources){
		initUserOrg();
	}
	
	private void labelAppear(Label label) {
		RotateTransition rotate = new RotateTransition(Duration.millis(1000), label);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setFromAngle(0);
		rotate.setToAngle(360f);
		FadeTransition fade = new FadeTransition(Duration.millis(1000),label);
		fade.setFromValue(0);
		fade.setToValue(1f);
		ParallelTransition parallel = new ParallelTransition();
		parallel.getChildren().addAll(rotate, fade);
		parallel.play();
	}
	private void labelDisappear(Label label) {
		RotateTransition rotate = new RotateTransition(Duration.millis(1000), label);
		rotate.setAxis(Rotate.Z_AXIS);
		rotate.setFromAngle(360f);
		rotate.setToAngle(0);
		FadeTransition fade = new FadeTransition(Duration.millis(1000),label);
		fade.setFromValue(1);
		fade.setToValue(0);
		ParallelTransition parallel = new ParallelTransition();
		parallel.getChildren().addAll(rotate, fade);
		parallel.play();
	}
	
	public void initPanel(Object[] bundle){
		
	}
	
	
	public void setChildren(Parent region){
		
	}
	
	private void initUserOrg(){
		CubeButton userOrgButton = new CubeButton();
		userType.getChildren().add(userOrgButton.createContent(1.4, Color.BLUE));
		userType.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userOrgButton.stop();	
			}
			
		});
		userType.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userOrgButton.stop();
				
			}
			
		});
	}

}
