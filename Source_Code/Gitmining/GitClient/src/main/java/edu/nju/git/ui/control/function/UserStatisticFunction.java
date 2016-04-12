package edu.nju.git.ui.control.function;


import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.chart.ChartType;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
		initUserColla();
		initCompanyUser();
		initOwnRepos();
		initUserContri();
		initUserCreate();
		initUserEmail();
		initUserFollower();
		initUserGist();
		initUserSubs();
	}
	
	
	
	public void initPanel(Object[] bundle){
		
	}
	
	
	public void setChildren(Parent region){
		
	}
	
	private void initUserOrg(){
		CubeButton userOrgButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/userType.png");
		userType.getChildren().add(userOrgButton.createContent(1.65,null, new Image(url.toExternalForm()),Color.TRANSPARENT));
		userOrgButton.play();
		setHandler(userType,userOrgButton,userType_label);
		userType.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserType});
				
			}
			
		});
	}
	
	private void initCompanyUser(){
		CubeButton companyUserButton = new CubeButton();
		
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/shopify.png");
		companyUser.getChildren().add(companyUserButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		companyUserButton.play();
		setHandler(companyUser, companyUserButton, companyUser_label);
		companyUser.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.CompanyUser});
				
			}
			
		});
	}
	
	private void initUserEmail(){
		CubeButton userEmailButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/email.png");
		email.getChildren().add(userEmailButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		userEmailButton.play();
		setHandler(email,userEmailButton,email_label);
		email.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserEmail});
				
			}
			
		});
	}
	
	private void initOwnRepos(){
		CubeButton ownReposButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/ownRepos.png");
		ownRepos.getChildren().add(ownReposButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		ownReposButton.play();
		setHandler(ownRepos,ownReposButton,ownRepos_label);
		ownRepos.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserOwnRepos});
				
			}
			
		});
	}
	
	private void initUserGist(){
		CubeButton userGistButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/gists.png");
		gist.getChildren().add(userGistButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		userGistButton.play();
		setHandler(gist,userGistButton,gist_label);
		gist.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserGist});
				
			}
			
		});
	}
	
	private void initUserFollower(){
		CubeButton userFollowerButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/followers.png");
		followers.getChildren().add(userFollowerButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		userFollowerButton.play();
		setHandler(followers,userFollowerButton,followers_label);
		followers.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserFollowers});
				
			}
			
		});
	}
	
	private void initUserCreate(){
		CubeButton userCreateButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/time.png");
		create.getChildren().add(userCreateButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		userCreateButton.play();
		setHandler(create,userCreateButton,create_label);
		create.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserCreate});
				
			}
			
		});
	}
	
	private void initUserSubs(){
		CubeButton userSubsButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/subscribe.png");
		subscribe.getChildren().add(userSubsButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT));
		userSubsButton.play();
		setHandler(subscribe,userSubsButton,subscribe_label);
		subscribe.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserSubsRepos});
				
			}
			
		});
	}
	
	private void initUserColla(){
		CubeButton userCollaButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/collaborate.png");
		Parent cube = userCollaButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT);
		collaborate.getChildren().add(cube);
		cube.setLayoutX(100);
		userCollaButton.play();
		setHandler(collaborate,userCollaButton,collaborate_label);
		collaborate.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserCollaRepos});
				
			}
			
		});
	}
	
	private void initUserContri(){
		CubeButton userContriButton = new CubeButton();
		URL url = Main.class.getResource(StringReader.readPath("picture")+"statisticButton/contribute.png");
		Parent cube = userContriButton.createContent(1.65, null,new Image(url.toExternalForm()),Color.TRANSPARENT);
		contribute.getChildren().add(cube);
		cube.setLayoutX(100);
		userContriButton.play();
		setHandler(contribute,userContriButton,contribute_label);
		contribute.setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeFunction("function_Statistic",new Object[]{ChartType.UserContriRepos});
				
			}
			
		});
	}
	
	
	
	private void setHandler(AnchorPane pane,CubeButton cube,Label label){
		pane.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				cube.stop();
				labelAppear(label);
				
			}
			
		});
		pane.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				cube.play();
				labelDisappear(label);
				
			}
			
		});
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

}
