package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.control.GitPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.image.Image;

public class HomeFunction extends GitPanel{
	private static final double WIDTH = 450, HEIGHT = 480;

    private Timeline animation;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCssFactory(UIManager.instance().getCssFactory());
		createContent();
		
	}
	
	public static Parent createContent(){
		Image[] images = new Image[10];

	    	images[0] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git1.png").toExternalForm(), false);

	        images[1] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git2.png").toExternalForm(), false);

	        images[2] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git3.png").toExternalForm(), false);

	        images[3] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git4.png").toExternalForm(), false);

	        images[4] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git5.png").toExternalForm(), false);

	        images[5] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git6.png").toExternalForm(), false);

	        images[6] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git7.png").toExternalForm(), false);

	        images[7] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git8.png").toExternalForm(), false);

	        images[8] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git9.png").toExternalForm(), false);

	        images[9] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git10.png").toExternalForm(), false);

	         
	        // create display shelf

	        DisplayShelf displayShelf = new DisplayShelf(images);

	        displayShelf.setPrefSize(WIDTH, HEIGHT);

	        String displayShelfCss = Main.class.getResource(StringReader.readPath("css")+"DisplayShelf.css").toExternalForm();

	        displayShelf.getStylesheets().add(displayShelfCss); 
	        
	        

	        return displayShelf;
	}

	@Override
	public void initPanel(Object[] bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setChildren(Parent region) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild() {
		// TODO Auto-generated method stub
		
	}

}
