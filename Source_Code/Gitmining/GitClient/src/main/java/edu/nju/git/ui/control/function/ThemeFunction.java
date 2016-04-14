package edu.nju.git.ui.control.function;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import edu.nju.git.ui.css.DefaultCSSFactory;
import edu.nju.git.ui.css.OldCSSFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ThemeFunction extends FunctionPanel{

	@FXML private ImageView one;
	@FXML private ImageView two;
	
	@Override
	public String getLocationName() {
		return "Theme";
	}
	
	@Override
	public void initPanel(Object[] bundle){
		Image oneImage = new Image(Main.class.getResource(StringReader.readPath("picture")+"theme/default.png").toString());
		one.setImage(oneImage);
		one.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeTheme(new OldCSSFactory());
				one.setCursor(Cursor.HAND);
			}
		});
		Image twoImage = new Image(Main.class.getResource(StringReader.readPath("picture")+"theme/black.png").toString());
		two.setImage(twoImage);
		two.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				UIManager.instance().changeTheme(new DefaultCSSFactory());
				two.setCursor(Cursor.HAND);
			}
		});
	}

}
