package edu.nju.git.ui.control.function;


 

import java.net.URL;
import java.util.ResourceBundle;

import edu.nju.git.main.Main;
import edu.nju.git.ui.config.StringReader;
import edu.nju.git.ui.control.FunctionPanel;
import edu.nju.git.ui.control.UIManager;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

 

/**

 * the home function 
 * @author yyy

 */

public class HomeFunction extends FunctionPanel {
	
	@FXML AnchorPane anchorPane;
    private static final double WIDTH = 820, HEIGHT = 450;

    private Timeline animation;

 

    public Parent createContent() {

         // load images

        Image[] images = new Image[10];
        for(int i=0;i<images.length;i++){
        	images[i] = new Image( Main.class.getResource(StringReader.readPath("picture")+"git"+(i+1)+".png").toString());
        }
       
        // create display shelf

        DisplayShelfNew displayShelf = new DisplayShelfNew(images);

        displayShelf.setPrefSize(WIDTH, HEIGHT);

        this.setCssFactory(UIManager.instance().getCssFactory());
//        System.out.println(getCssFactory().getDisplayShelf());
        String displayShelfCss = getCssFactory().getDisplayShelf();

        displayShelf.getStylesheets().add(displayShelfCss);       

        return displayShelf;

    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Parent display = createContent();
		anchorPane.getChildren().addAll(display);
		
		
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


	@Override
	public String getLocationName() {
		return "Home";
	}

}