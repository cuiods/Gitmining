package edu.nju.git.ui.control.function;


import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.ui.control.FunctionPanel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/*
 * userStatistic function
 * @author yyy
 */
public class UserStatisticFunction extends FunctionPanel{
	
	
	@FXML AnchorPane userType;
	@FXML AnchorPane companyUser;
	@FXML AnchorPane subscri;
	@FXML AnchorPane email;
	@FXML AnchorPane ownRepos;
	@FXML AnchorPane gist;
	@FXML AnchorPane followers;
	@FXML AnchorPane collabor;
	@FXML AnchorPane contri;
	@FXML AnchorPane create;
	
	
	@Override
	public String getLocationName() {
		return "UserStatistic";
	}
	
	public void initPanel(Object[] bundle){
		
	}
	public void initialize(URL location,ResourceBundle resources){
		initUserOrg();
	}
	
	public void setChildren(Parent region){
		
	}
	
	private void initUserOrg(){
		CubeButton userOrgButton = new CubeButton();
		userType.getChildren().add(userOrgButton.createContent(1.4, Color.BLUE));
		userType.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userOrgButton.play();	
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
