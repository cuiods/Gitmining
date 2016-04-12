package edu.nju.git.ui.utils;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Dialog general controller.<br>
 * Methods:<br>
 * {@link #initMessage(String)}<br>
 * {@link #initConfirm(String, Operation)}<br>
 * {@link #initChoose(String, String, Operation, Operation)}
 * @author cuihao
 */
public class Dialog implements Initializable{
	
	@FXML private Label title;
	@FXML private ImageView icon;
	@FXML private Label message1; 
	@FXML private Label message2;
	@FXML private Button cancel;
	@FXML private Button ok;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	/**
	 * @param message
	 * 		Message to show.
	 */
	protected void initMessage(String message) {
		Image image = new Image(Main.class.getResource(StringReader.readPath("picture")+"message.png").toString());
		icon.setImage(image);
		message1.setText(message);
		ok.setVisible(false);
		cancel.setVisible(false);
	}
	
	/**
	 * @param message
	 * 		Message to show.
	 * @param operation
	 * 		ok button operation.
	 */
	protected void initConfirm(String message, Operation operation) {
		Image image = new Image(Main.class.getResource(StringReader.readPath("picture")+"confirm.png").toString());
		icon.setImage(image);
		message1.setText(message);
		ok.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				operation.execute();
			}
		});
		cancel.setVisible(false);
	}
	
	/**
	 * @param m1
	 * 		message one
	 * @param m2
	 * 		message two
	 * @param operation1
	 * 		ok button operation.
	 * @param operation2
	 * 		cancel button operation.
	 */
	protected void initChoose(String m1, String m2, Operation operation1, Operation operation2) {
		Image image = new Image(Main.class.getResource(StringReader.readPath("picture")+"choose.png").toString());
		icon.setImage(image);
		message1.setText(m1);
		message2.setText(m2);
		ok.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				operation1.execute();
			}
		});
		cancel.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				operation2.execute();
			}
		});
	}

}
