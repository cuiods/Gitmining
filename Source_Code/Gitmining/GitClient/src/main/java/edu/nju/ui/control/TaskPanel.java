package edu.nju.ui.control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

/**
 * <h1>Present a task user started.</h1>
 * <p>
 * User can start a task in index panel:{@link IndexPanel}, IF and <b>ONLY</b> IF a task<br>
 * is created, services can be used. A task panel can set function panel: {@link FunctionPanel}<br>
 * A task can hold many function panels at a time, but only one will present to<br>
 * users.
 * </p>
 * <p>
 * 	Function panels can be memorized and can go back.
 * </p>
 * @author cuihao
 * @date 2016-03-04 23:16:26
 *
 */
public class TaskPanel extends GitPanel{
	
	@FXML ImageView img_home;
	@FXML ImageView img_user;
	@FXML ImageView img_repo;
	private Stack<ScreenShot> functions = new Stack<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		img_home.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				img_home.setImage(getNewImage("home_main_1"));
			}
		});
		img_home.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				img_home.setImage(getNewImage("home_main"));
			}
		});
		img_user.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				img_user.setImage(getNewImage("user_main_1"));
			}
		});
		img_user.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				img_user.setImage(getNewImage("user_main"));
			}
		});
		img_repo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				img_repo.setImage(getNewImage("repository_main_1"));
			}
		});
		img_repo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				img_repo.setImage(getNewImage("repository_main"));
			}
		});
	}

	@Override
	public void initPanel(Object[] bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildren(Region region) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * get back to lask function.
	 */
	public void back(){
		
	}
	
	private Image getNewImage(String s){
		URL url = Main.class.getResource(StringReader.readPath("picture")+"button/"+s+".png");
		return new Image(url.toString());
	}

}
