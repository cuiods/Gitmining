package edu.nju.git.main;

import edu.nju.ui.control.UIManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start application here.
 * @author cuihao
 * @date 2016-03-04 22:54:47
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Parent root = UIManager.instance().initialize(primaryStage);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("title");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		UIManager.instance().changeCSS("default");
	}
}
