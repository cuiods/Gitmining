package edu.nju.git.ui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * <h1>Present a task user started.</h1>
 * <p>
 * User can start a task in index panel:{@link IndexPanel}, IF and <b>ONLY</b>
 * IF a task<br>
 * is created, services can be used. A task panel can set function panel:
 * {@link FunctionPanel}<br>
 * A task can hold many function panels at a time, but only one will present to users.
 * </p>
 * <p>
 * Function panels can be memorized and can go back.
 * </p>
 * 
 * @author cuihao
 * @date 2016-03-04 23:16:26
 *
 */
public class TaskPanel extends GitPanel {

	/**
	 * To the last panel
	 */
	@FXML
	private Button back;
	/**
	 * To the next panel
	 */
	@FXML
	private Button forward;
	/**
	 * Panel to show function.
	 */
	@FXML
	private BorderPane childPanel;
	/**
	 * top bar including {@link #back} {@link #forward}, etc.
	 */
	@FXML
	private BorderPane topbar;
	/**
	 * button to home page ({@code Function})
	 */
	@FXML
	private Button nav_home;
	/**
	 * button to user list page
	 */
	@FXML
	private Button nav_user;
	/**
	 * button to repository list page
	 */
	@FXML
	private Button nav_repo;
	/**
	 * left bar including {@link #nav_home}, {@link #nav_repo} and {@link #nav_user}
	 */
	@FXML
	private VBox leftbar;
	@FXML
	private Label location;
	

	/**
	 * sub button of {@link #nav_user}
	 */
	private Button subview_user1;
	private Button subview_user2;
	/**
	 * sub button of repository
	 */
	private Button subview_repo1;
	private Button subview_repo2;
	/**
	 * a list to store past pages.
	 */
	private ArrayList<ScreenShot> functions = new ArrayList<>(20);
	private int index = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		setListener();
		initHome();
	}

	@Override
	public void initPanel(Object[] bundle) {
		clearFunction();
		ScreenShot shot = ConfigReader.readParentPanel("function_default");
		Parent child = shot.getRoot();
		setChildren(child);
		functions.add(shot);
		initHome();
	}
	
	public void initHome() {
		clearFunction();
		ScreenShot shot = ConfigReader.readParentPanel("function_home");
		shot.getRoot().getStylesheets().add(getCssFactory().getFunctionHome());
		setChildren(shot.getRoot());
		functions.add(shot);
		updateLocation();
	}
	
	private void initUser() {
		clearFunction();
		ScreenShot shot = ConfigReader.readParentPanel("function_userList");
		shot.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoList());
		setChildren(shot.getRoot());
		functions.add(shot);
		updateLocation();
	}

	private void initRepo() {
		clearFunction();
		ScreenShot shot = ConfigReader.readParentPanel("function_repoList");
		shot.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoList());
		setChildren(shot.getRoot());
		functions.add(shot);
		updateLocation();
	}
	
	private void initRepoStatistic() {
		clearFunction();
		ScreenShot shot = ConfigReader.readParentPanel("function_repoStatistic");
		//shot.getRoot().getStylesheets().add(e)
		setChildren(shot.getRoot());
		functions.add(shot);
		updateLocation();
	}

	@Override
	public void setChildren(Parent panel) {
		
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				childPanel.getChildren().clear();
				childPanel.getChildren().add(panel);
			}
		};

		if (functions.size() == 0) {
			new Timeline(
					new KeyFrame(Duration.seconds(0.20), new KeyValue(childPanel.translateYProperty(), -720),
							new KeyValue(childPanel.opacityProperty(), 0)),
					new KeyFrame(Duration.seconds(0.20), eh),
					new KeyFrame(Duration.seconds(0.40), new KeyValue(childPanel.translateYProperty(), 0),
							new KeyValue(childPanel.opacityProperty(), 1))).play();
		} else {
//			new Timeline(new KeyFrame(Duration.seconds(0.25), new KeyValue(childPanel.opacityProperty(), 0)),
//					new KeyFrame(Duration.seconds(0.45), eh),
//					new KeyFrame(Duration.seconds(0.6), new KeyValue(childPanel.opacityProperty(), 1))).play();
			new Timeline(
					new KeyFrame(Duration.seconds(0.5), new KeyValue(childPanel.translateXProperty(), -870)
							,new KeyValue(childPanel.opacityProperty(), 0)),
					new KeyFrame(Duration.seconds(0.5), eh),
					new KeyFrame(Duration.seconds(1.00),new KeyValue(childPanel.opacityProperty(), 1)
							,new KeyValue(childPanel.translateXProperty(), 0))
					).play();
		}
	}

	@Override
	public void removeChild() {

	}

	/**
	 * get back to lask function.
	 */
	@FXML
	protected void back() {
		if (functions.size() >= 2 && index >= 1) {
			index--;
			setChildren(functions.get(index).getRoot());
		}
		updateLocation(index);
	}

	/**
	 * to the panel just closed.
	 * 
	 * @param e
	 */
	@FXML
	protected void forward() {
		if (functions.size() > index + 1) {
			index++;
			setChildren(functions.get(index).getRoot());
		}
		updateLocation(index);
	}
	
	/**
	 * set navigation bar's location
	 */
	public void setCurrentLocation(String location){
		this.location.setText(location);
	}
	public void updateLocation(){
		String result = "";
		for(ScreenShot shot:functions) {
			FunctionPanel panel = (FunctionPanel) shot.getPanel();
			if(result.isEmpty()){
				result += panel.getLocationName();
			}else{
				result += "->" + panel.getLocationName();
			}
		}
		setCurrentLocation(result);
	}
	public void updateLocation(int index){
		String result = "";
		for(int i = 0; i <= index; i++) {
			ScreenShot shot = functions.get(i);
			FunctionPanel panel = (FunctionPanel) shot.getPanel();
			if(result.isEmpty()){
				result += panel.getLocationName();
			}else{
				result += "->" + panel.getLocationName();
			}
		}
		setCurrentLocation(result);
	}
	public String getLocation(){
		return location.getText();
	}

	/**
	 * add a function to list
	 * 
	 * @param shot
	 */
	public void appendFunction(ScreenShot shot) {
//		for(int i = index+1;  i < functions.size(); i++){
//			functions.remove(index);
//		}
		index = functions.size();
		functions.add(shot);
	}

	/**
	 * clear list
	 */
	public void clearFunction() {
		functions.clear();
		index = 0;
	}

	private void setListener() {
		nav_home.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				initPanel(null);
				initHome();
			}
		});
		nav_user.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (subview_user1 == null) {
					subview_user1 = new Button("UserInfo");
					subview_user1.setPrefSize(150, 40);
					subview_user1.getStyleClass().add("sub_button");
					subview_user1.setOnMouseReleased(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							initUser();
						}
					});
					subview_user2 = new Button("UserStatistic");
					subview_user2.setPrefSize(150, 40);
					subview_user2.getStyleClass().add("sub_button");
					leftbar.getChildren().remove(nav_repo);
					if (subview_repo1!=null) {
						leftbar.getChildren().remove(subview_repo1);
						leftbar.getChildren().remove(subview_repo2);
					}
					leftbar.getChildren().add(subview_user1);
					leftbar.getChildren().add(subview_user2);
					leftbar.getChildren().add(nav_repo);
					if (subview_repo1!=null) {
						leftbar.getChildren().add(subview_repo1);
						leftbar.getChildren().add(subview_repo2);
					}
				} else {
					leftbar.getChildren().remove(subview_user1);
					leftbar.getChildren().remove(subview_user2);
					subview_user1 = subview_user2 = null;
				}
			}
		});
		nav_repo.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (subview_repo1 == null) {
					subview_repo1 = new Button("RepoInfo");
					subview_repo1.setPrefSize(150, 40);
					subview_repo1.getStyleClass().add("sub_button");
					subview_repo1.setOnMouseReleased(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							initRepo();
						}
					});
					subview_repo2 = new Button("RepoStatistic");
					subview_repo2.setPrefSize(150, 40);
					subview_repo2.getStyleClass().add("sub_button");
					subview_repo2.setOnMouseReleased(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							initRepoStatistic();
						}
					});
					leftbar.getChildren().add(subview_repo1);
					leftbar.getChildren().add(subview_repo2);
				} else {
					leftbar.getChildren().remove(subview_repo1);
					leftbar.getChildren().remove(subview_repo2);
					subview_repo1 = subview_repo2 = null;
				}
			}
		});
	}

}
