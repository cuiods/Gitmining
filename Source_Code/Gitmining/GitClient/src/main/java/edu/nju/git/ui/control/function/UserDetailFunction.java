package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.VO.UserVO;
import edu.nju.git.bl.factory.impl.BlFactory;
import edu.nju.git.bl.service.UserBlService;
import edu.nju.git.ui.control.FunctionPanel;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/*
 * UserDetial controller
 */
public class UserDetailFunction extends FunctionPanel{
	/*
	 * FXML components
	 */
	@FXML private Label loginName;
	@FXML private ImageView heading;
	@FXML private Label create;
	@FXML private Label update;
	@FXML private Hyperlink blog;
	@FXML private Label email;
	@FXML private Label company;
	@FXML private Label location;
	@FXML private Label bio;
	@FXML private ScrollPane table;
	@FXML private Label pub_repos;
	@FXML private Label following;
	@FXML private Label followers;
	@FXML private Label gists;
	@FXML private AnchorPane radarPane;
	
	private UserBlService service;
	
	private UserVO user;
	
	public void initialize(URL location,ResourceBundle resource){
		service = BlFactory.instance().getUserBlService();
		
	}
	
	public void initPanel(Object bundle[]){
		user = service.getUserInfo((String)bundle[0]);
		Task<Void> task = new Task<Void>(){
			@Override
			protected Void call() throws Exception {
				initData(bundle);
				initLink();
				return null;
			}
			
		};
		Platform.runLater(task);

			
	}
	
	private void initLink(){
		blog.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				edu.nju.git.ui.control.UIManager.instance().changeFunction("function_webView",new Object[]{blog.toString()});
				
			}
			
		});
	}
	
	private void initData(Object[] bundle){
		if(user==null){
			return;
		}
		loginName.setText(user.getName());
		Image headingImage = new Image(user.getAvatar_url());
		heading.setImage(headingImage);
		create.setText(user.getCreate_at());
		update.setText(user.getUpdate_at());
		blog.setText(user.getBlog());
		email.setText(user.getEmail());
		company.setText(user.getCompany());
		location.setText(user.getLocation());
		bio.setText(user.getBio());
		pub_repos.setText(user.getPublic_repos()+"");
		following.setText(user.getFollowingNum()+"");
		followers.setText(user.getFollowNum()+"");
		gists.setText(user.getRadar_gist()+"");
		
		
	}
	@Override
	public String getLocationName() {
		// TODO Auto-generated method stub
		return user.getName();
	}
	
	
}
