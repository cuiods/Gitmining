package edu.nju.ui.control;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.nju.git.ui.config.ScreenShot;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

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
	@FXML ImageView img_back;
	@FXML ImageView img_forward;
	@FXML BorderPane childPanel;
	private ArrayList<ScreenShot> functions = new ArrayList<>(20);
	private int index = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//set listener T T no other way?
		img_home.setOnMouseEntered(new ChangePictureHandler<MouseEvent>("home_main_1", img_home));
		img_home.setOnMouseExited(new ChangePictureHandler<>("home_main", img_home));
		img_home.setOnMousePressed(new ChangePictureHandler<>("home_main_2", img_home));
		img_user.setOnMouseEntered(new ChangePictureHandler<>("user_main_1", img_user));
		img_user.setOnMouseExited(new ChangePictureHandler<>("user_main", img_user));
		img_user.setOnMousePressed(new ChangePictureHandler<>("user_main_2", img_user));
		img_repo.setOnMouseEntered(new ChangePictureHandler<>("repository_main_1", img_repo));
		img_repo.setOnMouseExited(new ChangePictureHandler<>("repository_main", img_repo));
		img_repo.setOnMousePressed(new ChangePictureHandler<>("repository_main_2", img_repo));
		img_back.setOnMouseEntered(new ChangePictureHandler<>("back_1", img_back));
		img_back.setOnMouseExited(new ChangePictureHandler<>("back", img_back));
		img_forward.setOnMouseEntered(new ChangePictureHandler<>("front_1", img_forward));
		img_forward.setOnMouseExited(new ChangePictureHandler<>("front", img_forward));
	}

	@Override
	public void initPanel(Object[] bundle) {
		
	}

	@Override
	public void setChildren(Parent region) {
		
	}

	@Override
	public void removeChild() {
		
	}
	
	/**
	 * get back to lask function.
	 */
	public void back(ActionEvent e){
		if (functions.size() >= 2 && index >=1) {
			index--;
			setChildren(functions.get(index).getRoot());
		}
	}
	
	/**
	 * to the panel just closed.
	 * @param e
	 */
	public void forward(ActionEvent e){
		if (functions.size() > index + 1) {
			index ++;
			setChildren(functions.get(index).getRoot());
		}
	}
	

}
