package edu.nju.git.ui.control.function;

import edu.nju.git.ui.control.FunctionPanel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
/*
 * UserDetial controller
 */
public class UserDetailFunction extends FunctionPanel{
	/*
	 * FXML components
	 */
	@FXML private ImageView heading;
	@FXML private Label create;
	@FXML private Label update;
	@FXML private Hyperlink blog;
	@FXML private Hyperlink email;
	@FXML private Label company;
	@FXML private Label location;
	@FXML private Label bio;
	@FXML private ScrollPane table;
	@FXML private Label pub_repos;
	@FXML private Label following;
	@FXML private Label followers;
	@FXML private Label gists;
	@FXML private AnchorPane radarPane;
	
	
}
