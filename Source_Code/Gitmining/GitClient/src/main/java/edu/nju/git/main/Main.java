package edu.nju.git.main;

import edu.nju.git.ui.control.UIManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
