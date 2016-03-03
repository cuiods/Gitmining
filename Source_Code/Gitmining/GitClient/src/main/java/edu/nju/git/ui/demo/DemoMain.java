package edu.nju.git.ui.demo;

import java.awt.Label;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DemoMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX welcome");
		
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));
		
		Text text = new Text("Welcome");
		text.setFont(Font.font(20));
		pane.add(text, 0, 0, 2, 1);
		
		Label username = new Label("name");
		
		
		Scene scene = new Scene(pane, 1024, 720);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
