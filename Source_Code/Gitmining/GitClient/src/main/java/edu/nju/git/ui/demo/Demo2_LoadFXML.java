package edu.nju.git.ui.demo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Demo2_LoadFXML extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			/**
			 * can only load file from the java file's directory?
			 */
			Parent root = FXMLLoader.load(getClass().getResource("scene_welcome.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("FXML test");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
