package edu.nju.git.ui.control.function;

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.ConfigReader;
import edu.nju.git.ui.config.ScreenShot;
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
	
	public Parent createContent(){
		Image[] images = new Image[10];
	    
		//get the images
		for(int i=0;i<images.length;i++){
			images[i] = new Image(Main.class.getResource(StringReader.readPath("picture")+"git"+(i+1)+".png").toExternalForm(),false);
		}
	        // create display shelf
			ScreenShot pane = ConfigReader.readParentPanel("DisplayShelf");
			pane.getRoot().getStyleClass().add(getCssFactory().getDisplayShelf());
			DisplayShelf display = (DisplayShelf)pane.getPanel();
			display.initialize(images);

	        display.setPrefSize(WIDTH, HEIGHT);

	        String displayShelfCss = Main.class.getResource(StringReader.readPath("css")+"DisplayShelf.css").toExternalForm();

	        display.getStylesheets().add(displayShelfCss); 
	        
	        

	        return display;
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
