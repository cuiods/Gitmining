package edu.nju.git.ui.utils;

import java.io.IOException;
import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UtilDialog {
	private static Stage stage;
	
	public static void ShowMessage(String message){
		FXMLLoader loader = new FXMLLoader();
		URL url = Main.class.getResource(StringReader.readPath("config")+"dialog_alert.fxml");
		loader.setLocation(url);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dialog dialog = loader.getController();
		dialog.initMessage(message);
		stage = new Stage(StageStyle.TRANSPARENT);
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void ShowConfirm(String message, Operation op) {
		FXMLLoader loader = new FXMLLoader();
		URL url = Main.class.getResource(StringReader.readPath("config")+"dialog_alert.fxml");
		loader.setLocation(url);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dialog dialog = loader.getController();
		dialog.initConfirm(message, op);
		stage = new Stage(StageStyle.TRANSPARENT);
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void ShowChoose(String message1, String message2, Operation opOk, Operation opCacel) {
		FXMLLoader loader = new FXMLLoader();
		URL url = Main.class.getResource(StringReader.readPath("config")+"dialog_alert.fxml");
		loader.setLocation(url);
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Dialog dialog = loader.getController();
		dialog.initChoose(message1, message2, opOk, opCacel);
		stage = new Stage(StageStyle.TRANSPARENT);
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public static void hideDialog(){
		if (stage != null) {
			stage.hide();
		}
	}
}
