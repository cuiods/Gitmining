package edu.nju.git.ui.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
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
 * A task can hold many function panels at a time, but only one will present to
 * <br>
 * users.
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
	ImageView img_back;
	@FXML
	ImageView img_forward;
	@FXML
	BorderPane childPanel;
	@FXML
	BorderPane topbar;
	@FXML
	ImageView nav_home;
	@FXML
	ImageView nav_user;
	@FXML
	ImageView nav_repo;
	@FXML
	VBox leftbar;

	private ImageView subview_user1;
	private ImageView subview_user2;
	private ImageView subview_repo1;
	private ImageView subview_repo2;
	private ArrayList<ScreenShot> functions = new ArrayList<>(20);
	private int index = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		//set listener T T no other way?
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
		Parent child = shot.getRoot();
		childPanel.getChildren().add(child);
	}

	private void initRepo() {
		ScreenShot shot = ConfigReader.readParentPanel("function_repoList");
		Parent child = shot.getRoot();
		childPanel.getChildren().add(child);
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
					new KeyFrame(Duration.seconds(0.15), new KeyValue(childPanel.translateXProperty(), -600),
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
		img_back.setOnMouseEntered(new ChangePictureHandler<>("back_1", img_back));
		img_back.setOnMouseExited(new ChangePictureHandler<>("back", img_back));
		img_forward.setOnMouseEntered(new ChangePictureHandler<>("front_1", img_forward));
		img_forward.setOnMouseExited(new ChangePictureHandler<>("front", img_forward));
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
					subview_user1 = new ImageView();
					subview_user1.setFitWidth(150);
					subview_user1.setFitHeight(50);
					URL url = Main.class.getResource(StringReader.readPath("picture") + "button/user_info.png");
					subview_user1.setImage(new Image(url.toString()));
					subview_user1.setOnMouseReleased(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							initUser();
						}
					});
					subview_user2 = new ImageView();
					subview_user2.setFitWidth(150);
					subview_user2.setFitHeight(50);
					URL url2 = Main.class.getResource(StringReader.readPath("picture") + "button/statistic.png");
					subview_user2.setImage(new Image(url2.toString()));
					leftbar.getChildren().remove(nav_repo);
					leftbar.getChildren().add(subview_user1);
					leftbar.getChildren().add(subview_user2);
					leftbar.getChildren().add(nav_repo);
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
					subview_repo1 = new ImageView();
					subview_repo1.setFitWidth(150);
					subview_repo1.setFitHeight(50);
					URL url = Main.class.getResource(StringReader.readPath("picture") + "button/repo_info.png");
					subview_repo1.setImage(new Image(url.toString()));
					subview_repo1.setOnMouseReleased(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							initRepo();
						}
					});
					subview_repo2 = new ImageView();
					subview_repo2.setFitWidth(150);
					subview_repo2.setFitHeight(50);
					URL url2 = Main.class.getResource(StringReader.readPath("picture") + "button/statistic.png");
					subview_repo2.setImage(new Image(url2.toString()));
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
