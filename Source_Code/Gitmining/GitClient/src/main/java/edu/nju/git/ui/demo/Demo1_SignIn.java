package edu.nju.git.ui.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Demo1_SignIn extends Application {

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
		pane.add(username, 0, 1);
		
		TextField field = new TextField();
		pane.add(field, 1, 1);
		
		Label pw = new Label("passworld");
		pane.add(pw, 0, 2);
		
		PasswordField passwordField = new PasswordField();
		pane.add(passwordField, 1,2);
		
		Button button = new Button("Sign In");
		HBox box = new HBox(10);
		box.setAlignment(Pos.BASELINE_RIGHT);
		box.getChildren().add(button);
		pane.add(box, 1, 4);
		
		final Text show = new Text();
		pane.add(show, 1, 6);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				show.setText("clicked!");
			}
		});
		
		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
