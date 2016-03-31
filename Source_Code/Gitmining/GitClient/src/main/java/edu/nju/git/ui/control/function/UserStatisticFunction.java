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
	
	
	@FXML AnchorPane userOrg;
	@FXML AnchorPane companyUser;
	@FXML AnchorPane blog;
	@FXML AnchorPane email;
	@FXML AnchorPane repos;
	@FXML AnchorPane gist;
	@FXML AnchorPane followers;
	@FXML AnchorPane following;
	@FXML AnchorPane location;
	@FXML AnchorPane create;
	@FXML AnchorPane organization;
	
	
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
		userOrg.getChildren().add(userOrgButton.createContent(1.4, Color.BLUE));
		userOrg.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userOrgButton.play();	
			}
			
		});
		userOrg.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userOrgButton.stop();
				
			}
			
		});
	}

}
