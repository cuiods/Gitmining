package edu.nju.git.ui.handler;

import java.net.URL;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Change picture when needed.
 * @author cuihao
 *
 * @param <T>
 */
public class ChangePictureHandler<T extends Event> implements EventHandler<Event> {

	private String pic;
	private ImageView view;
	
	public ChangePictureHandler(String picName, ImageView view) {
		pic = picName;
		this.view = view;
	}
	
	@Override
	public void handle(Event event) {
		view.setImage(getNewImage(pic));
	}
	
	private Image getNewImage(String s){
		URL url = Main.class.getResource(StringReader.readPath("picture")+"button/"+s+".png");
		return new Image(url.toString());
	}

}
