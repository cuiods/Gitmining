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
import javafx.scene.image.ImageView;
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

	@FXML
	private Button back;
	@FXML
	private Button forward;
	@FXML
	private BorderPane childPanel;
	@FXML
	private BorderPane topbar;
	@FXML
	private Button nav_home;
	@FXML
	private Button nav_user;
	@FXML
	private Button nav_repo;
	@FXML
	private VBox leftbar;

	private Button subview_user1;
	private Button subview_user2;
	private Button subview_repo1;
	private Button subview_repo2;
	private ArrayList<ScreenShot> functions = new ArrayList<>(20);
	private int index = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		setListener();
	}

	@Override
	public void initPanel(Object[] bundle) {
		ScreenShot shot = ConfigReader.readParentPanel("function_default");
		functions.add(shot);
		Parent child = shot.getRoot();
		childPanel.getChildren().add(child);
	}

	private void initUser() {
		ScreenShot shot = ConfigReader.readParentPanel("function_userList");
		shot.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoList());
		setChildren(shot.getRoot());
	}

	private void initRepo() {
		ScreenShot shot = ConfigReader.readParentPanel("function_repoList");
		shot.getRoot().getStylesheets().add(getCssFactory().getFunctionRepoList());
		setChildren(shot.getRoot());
	}

	@Override
	public void setChildren(Parent panel) {
		EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					childPanel.getChildren().clear();
					childPanel.getChildren().add(panel);
					topbar.toFront();
				} catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}
		};

		if (childPanel.getChildren().size() > 0) {
			if (childPanel.getChildren().get(0) == panel) {
				return;
			}
			new Timeline(
					new KeyFrame(Duration.seconds(0.15), new KeyValue(childPanel.translateXProperty(), -870),
							new KeyValue(childPanel.opacityProperty(), 0)),
					new KeyFrame(Duration.seconds(0.16), eh),
					new KeyFrame(Duration.seconds(0.3), new KeyValue(childPanel.translateXProperty(), 0),
							new KeyValue(childPanel.opacityProperty(), 1))).play();
		} else {
			new Timeline(new KeyFrame(Duration.seconds(0.45), new KeyValue(childPanel.opacityProperty(), 0)),
					new KeyFrame(Duration.seconds(0.46), eh),
					new KeyFrame(Duration.seconds(0.6), new KeyValue(childPanel.opacityProperty(), 1))).play();
		}
	}

	@Override
	public void removeChild() {

	}

	/**
	 * get back to lask function.
	 */
	public void back(ActionEvent e) {
		if (functions.size() >= 2 && index >= 1) {
			index--;
			setChildren(functions.get(index).getRoot());
		}
	}

	/**
	 * to the panel just closed.
	 * 
	 * @param e
	 */
	public void forward(ActionEvent e) {
		if (functions.size() > index + 1) {
			index++;
			setChildren(functions.get(index).getRoot());
		}
	}

	/**
	 * add a function to list
	 * 
	 * @param shot
	 */
	public void appendFunction(ScreenShot shot) {
		functions.add(shot);
	}

	/**
	 * clear list
	 */
	public void clearFunction() {
		functions.clear();
	}

	private void setListener() {
		nav_home.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				ScreenShot shot = functions.get(0);
				setChildren(shot.getRoot());
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
