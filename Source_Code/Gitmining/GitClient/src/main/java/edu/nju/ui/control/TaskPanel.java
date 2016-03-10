package edu.nju.ui.control;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ScreenShot;
import edu.nju.git.ui.config.StringReader;
import javafx.fxml.FXML;
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
	@FXML ImageView img_back;
	@FXML ImageView img_forward;
	private Stack<ScreenShot> functions = new Stack<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
